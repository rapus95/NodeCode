package nodecode.core;


public interface ValueHandler<Type> {
	public Class<Type> getType();
	public ValueType<Type> getData();
	public Type getValue();
	public void setValueUnchecked(Object t);
}
