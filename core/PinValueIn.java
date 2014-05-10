package core;

import XML.XMLNode;
import core.ValueType.COLOR;


public class PinValueIn<Type> extends PinBaseImp implements PinInput, ValueHandler<Type> {

	public static final String XMLTypeName = "valIn";
	private ValueType<Type> data;
	private PinOutput origin=null;
	private boolean allowDirectInput=true;
	
	public PinValueIn(Node parent, String name, int id, ValueType<Type> var){
		this(parent, name, id, var, true);
	}
	
	public PinValueIn(Node parent, String name, int id, ValueType<Type> var, boolean allowDirectInput){
		super(parent, name, id);
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
		return getOrigin()==null?data.getValue():Helper.<Type>getValue((ValueHandler<?>)getOrigin());
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
		XMLNode n = new XMLNode(XMLTypeName);
		n.setProperty("name", name);
		n.setProperty("originNode", ""+(origin==null?-1:origin.getNode().getUniqueID()));
		n.setProperty("originPinName", ""+(origin==null?-1:origin.getName()));
		n.setProperty("originPin", ""+(origin==null?-1:origin.getID()));
		data.saveTo(n);
		node.addChild(n);
	}

	@Override
	public void loadFrom(XMLNode node) {
		XMLNode[] children = node.getChildByName(XMLTypeName);
		for(XMLNode child:children){
			if(!child.getProperty("name").equalsIgnoreCase(name))
				continue;
			Node tmp = getNode().getGrid().getNodeByUniqueID(Integer.valueOf(child.getProperty("originNode")));
			origin = tmp==null?null:tmp.getPinValOutByName(child.getProperty("originPinName"));
			data.loadFrom(child);
			return;
		}
	}

}
