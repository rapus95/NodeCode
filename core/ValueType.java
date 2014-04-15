package core;

public interface ValueType<DataType> {
	public DataType getValue();
	public void setValue(DataType dt);
	public Class<DataType> getType();
}
