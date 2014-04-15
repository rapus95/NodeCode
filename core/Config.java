package core;

public class Config<Type> implements NodeComponent, ValueHandler<Type>{
	private ValueType<Type> data;
	private String name;
	private final Node parent;
	
	public Config(String name, Node parent, ValueType<Type> data){
		this.name = name;
		this.parent = parent;
		this.data = data;
	}
	
	@Override
	public ValueType<Type> getData(){
		return data;
	}

	@Override
	public Node getNode() {
		return parent;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Class<Type> getType() {
		return data.getType();
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
	public void init() {
		data.init();
	}
}
