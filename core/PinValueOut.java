package core;

import core.ValueType.COLOR;


public class PinValueOut<Type> extends PinBaseImp implements PinOutput, ValueHandler<Type> {
	
	private ValueType<Type> data;
	
	public PinValueOut(Node parent, String name, ValueType<Type> var) {
		super(parent, name);
		data = var;
	}

	@Override
	public void setTargetUnchecked(PinInput target) {}

	@Override
	public void removeTargetUnchecked(PinInput target) {}

	@Override
	public PinInput getTarget() {
		return null;
	}
	
	@Override
	public ValueType<Type> getData(){
		return data;
	}

	@Override
	public Type getValue() {
		return data.getValue();
	}
	
	@Override
	public void setValueUnchecked(Object t) {
		if(getType().isInstance(t)){
			data.setValue(getType().cast(t));
			return;
		}
		throw new RuntimeException("WRONG TYPE!!");
	}

	@Override
	public Class<Type> getType() {
		return data.getType();
	}

	@Override
	public boolean isValidFor(PinInput in) {
		return in instanceof PinValueIn && this.getType()==((PinValueIn<?>)in).getType();
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
