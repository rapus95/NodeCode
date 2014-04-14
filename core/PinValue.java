package core;

public interface PinValue<Type> extends PinBase {
	public Class<Type> getType();
	public Type getValue();
}
