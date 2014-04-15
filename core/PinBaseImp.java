package core;


public abstract class PinBaseImp implements PinBase {
	protected final Node parent;
	protected String name;

	
	protected PinBaseImp(Node parent, String name){
		this.parent = parent;
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see core.PinBase#getNode()
	 */
	@Override
	public Node getNode(){
		return parent;
	}
	
	@Override
	public String getName(){
		return name;
	}
}
