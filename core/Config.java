package core;

import XML.XMLNode;

public class Config<Type> implements NodeComponent, ValueHandler<Type>{
	public static final String XMLTypeName = "config";
	private ValueType<Type> data;
	private final String name;
	private final Node parent;
	private final int id;
	
	public Config(Node parent, String name, int id, ValueType<Type> data){
		this.name = name;
		this.parent = parent;
		this.id = id;
		this.data = data;
	}
	
	public int getID(){
		return id;
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
	public void reset() {}

	@Override
	public void saveTo(XMLNode node) {
		XMLNode n = new XMLNode(XMLTypeName);
		n.setString("name", name);
		data.saveTo(n);
		node.addChild(n);
	}

	@Override
	public void loadFrom(XMLNode node) {
		XMLNode[] children = node.getChildByName(XMLTypeName);
		for(XMLNode child:children){
			if(child.getString("name").equalsIgnoreCase(name))
				continue;
			data.loadFrom(child);
			return;
		}
	}

	@Override
	public void init() {
		data.init();
	}
}
