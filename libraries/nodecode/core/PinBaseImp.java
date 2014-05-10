package core;


public abstract class PinBaseImp implements PinBase {
	protected final Node parent;
	protected final String name;
	protected final int id;

	
	protected PinBaseImp(Node parent, String name, int id){
		this.parent = parent;
		this.name = name;
		this.id = id;
	}
	
	public int getID(){
		return id;
	}

	@Override
	public Node getNode(){
		return parent;
	}
	
	@Override
	public String getName(){
		return name;
	}
	
	public void onClick(){}
}
