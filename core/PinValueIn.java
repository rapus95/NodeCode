package core;

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
	public COLOR getColor() {
		return data.getColor();
	}

}
