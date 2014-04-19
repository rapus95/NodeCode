package core;

import XML.XMLNode;

public class Config<Type> implements NodeComponent, ValueHandler<Type>{
	private ValueType<Type> data;
	private final String name;
	private final Node parent;
	
	public Config(Node parent, String name, ValueType<Type> data){
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
	public void reset() {}

	@Override
	public void saveTo(XMLNode node) {
		XMLNode n = new XMLNode(name);
		n.setProperty("type", "config");
		data.saveTo(n);
		node.addChild(n);
	}

	@Override
	public void loadFrom(XMLNode node) {
		XMLNode child;
		for(int i=0; i<node.getChildrenAmount(); i++){
			if(!((child=node.getChild(i)).getName().equalsIgnoreCase(name) && child.getProperty("type").equalsIgnoreCase("config")))
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
