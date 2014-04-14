package core;

public class PinValueIn<Type> extends PinBaseImp implements PinInput, PinValue<Type> {

	private final Class<Type> type;
	private PinOutput origin=null;
	
	public PinValueIn(Class<Type> type){
		this.type = type;
	}

	@Override
	public void setOriginUnchecked(PinOutput origin) {
		if(origin instanceof PinValue && this.getType()==((PinValue<?>)origin).getType()){
			this.origin = origin;
		}
	}

	@Override
	public PinOutput getOrigin() {
		return origin;
	}

	@Override
	public Type getValue() {
		return type.cast(((PinValue<?>)getOrigin()).getValue());
	}

	@Override
	public Class<Type> getType() {
		return type;
	}

}
