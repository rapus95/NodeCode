package core;

import XML.XMLNode;
import core.ValueType.COLOR;


public class PinValueIn<Type> extends PinBaseImp implements PinInput, ValueHandler<Type> {

	private ValueType<Type> data;
	private PinOutput origin=null;
	private boolean allowDirectInput=true;
	
	public PinValueIn(Node parent, String name, ValueType<Type> var){
		this(parent, name, var, true);
	}
	
	public PinValueIn(Node parent, String name, ValueType<Type> var, boolean allowDirectInput){
		super(parent, name);
		data = var;
		this.allowDirectInput = allowDirectInput;
	}

	@Override
	public void setOriginUnchecked(PinOutput origin) {
		if(isValidFor(origin)){
			this.origin = origin;
			return;
		}
		throw new RuntimeException("WRONG PIN-TYPE!!");
	}

	@Override
	public void removeOriginUnchecked(PinOutput origin) {
		if(this.origin==origin)
			origin=null;
	}

	@Override
	public PinOutput getOrigin() {
		return origin;
	}
	
	@Override
	public ValueType<Type> getData(){
		return data;
	}

	@Override
	public Type getValue() {
		return getOrigin()==null?data.getValue():PinValueIn.<Type>getValue((ValueHandler<?>)getOrigin());
	}

	@Override
	public void setValueUnchecked(Object t) {
		if(!allowsDirectInput())
			throw new RuntimeException("Direct Input not allowed");
		data.setValueUnchecked(t);
	}

	@Override
	public Class<Type> getType() {
		return data.getType();
	}
	
	public boolean allowsDirectInput(){
		return allowDirectInput && data.canHaveDirectInput();
	}
	
	@SuppressWarnings("unchecked")
	public static <Type> Type getValue(ValueHandler<?> in){
		return (Type)in.getValue();
	}

	@Override
	public boolean isValidFor(PinOutput out) {
		return out instanceof PinValueOut && this.data.canConvert(((PinValueOut<?>)out).getType());
	}

	@Override
	public void init() {
		data.init();
	}

	@Override
	public void reset() {}

	@Override
	public COLOR getColor() {
		return data.getColor();
	}
	
	@Override
	public void saveTo(XMLNode node) {
		XMLNode n = new XMLNode(name);
		n.setProperty("type", "valIn");
		n.setProperty("originNode", ""+(origin==null?-1:origin.getNode().getUniqueID()));
		n.setProperty("originPin", ""+(origin==null?-1:origin.getName()));
		data.saveTo(n);
		node.addChild(n);
	}

	@Override
	public void loadFrom(XMLNode node) {
		XMLNode child;
		for(int i=0; i<node.getChildrenAmount(); i++){
			if(!((child=node.getChild(i)).getName().equalsIgnoreCase(name) && child.getProperty("type").equalsIgnoreCase("valIn")))
				continue;
			Node tmp = getNode().getGrid().getNodeByUniqueID(Integer.valueOf(child.getProperty("originNode")));
			origin = tmp==null?null:tmp.getPinValOutByName(child.getProperty("originPin"));
			data.loadFrom(child);
			return;
		}
	}

}
