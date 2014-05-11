package nodecode.core;

import nodecode.core.Node.IPOType;
import nodecode.core.Node.SpecialType;


public interface INodeFactoryDescriptor {
	public Node createNode();
	public int getUniqueTypeID();
	public String getDefaultName();
	public IPOType getIPOType();
	public SpecialType getSpecialType();
}
