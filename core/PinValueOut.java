package core;


public class PinValueOut<Type> extends PinBaseImp implements PinOutput, PinValue<Type> {

	private PinInput target;
	private ValueType<Type> data;
	
	public PinValueOut(Node parent, String name, ValueType<Type> var) {
		super(parent, name);
		data = var;
	}

	@Override
	public void setTargetUnchecked(PinInput target) {
		if(target instanceof PinValue && this.getType()==((PinValue<?>)target).getType()){
			this.target = target;
		}
	}

	@Override
	public void removeTargetUnchecked(PinInput target) {
		if(this.target==target)
			target=null;
	}

	@Override
	public PinInput getTarget() {
		return target;
	}

	@Override
	public void initialize() {}

	@Override
	public Type getValue() {
		return data.getValue();
	}

	@Override
	public Class<Type> getType() {
		return data.getType();
	}
}
