package nodecode.core;


public interface INodeFactoryDescriptor {
	public Node createNode();
	public int getUniqueTypeID();
	public String getDefaultName();
}
