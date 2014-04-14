package core;

public abstract class PinBaseImp implements PinBase {
	protected Node parent;
	
	/* (non-Javadoc)
	 * @see core.PinBase#setParent(core.Node)
	 */
	@Override
	public void setParent(Node n){
		this.parent = n;
	}
	
	/* (non-Javadoc)
	 * @see core.PinBase#getNode()
	 */
	@Override
	public Node getNode(){
		return parent;
	}
}
