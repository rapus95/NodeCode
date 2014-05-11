package nodecode.core;

import java.util.HashMap;

import nodecode.XML.XMLNode;
import nodecode.node.NodeBranch;
import nodecode.node.NodeCountLoop;
import nodecode.node.NodeEntry;
import nodecode.node.NodeItemCompareOutCount;
import nodecode.node.NodeItemStackSeperate;
import nodecode.node.NodeMaths;

public class NodeFactory {
	
	private static boolean loaded = false;
	
	static{
		init();
	}

	private static HashMap<String, INodeFactoryDescriptor> nodes = new HashMap<String, INodeFactoryDescriptor>();

	public static void registerNode(String name, INodeFactoryDescriptor n) {
		if( !nodes.containsKey(name))
			nodes.put(name, n);
	}

	public static Node getNewNodeForName(String type) {
		INodeFactoryDescriptor factory = nodes.get(type);
		if(factory == null)
			return null;
		return factory.createNode();
	}

	public static Node getNewNode(XMLNode child) {
		return getNewNodeForName(child.getString("type"));
	}

	public static Node getNewNodeForTypeID(int id){
		for(INodeFactoryDescriptor factory:nodes.values()){
			if(factory.getUniqueTypeID()==id)
				return factory.createNode();
		}
		return null;
	}

	private static void init() {
		if(loaded) return;
		Node n;
		registerNode((n=new NodeEntry(false)).getDefaultName(),n.getNodeFactoryDescriptor());
		registerNode((n=new NodeBranch(false)).getDefaultName(),n.getNodeFactoryDescriptor());
		registerNode((n=new NodeCountLoop(false)).getDefaultName(),n.getNodeFactoryDescriptor());
		registerNode((n=new NodeItemCompareOutCount(false)).getDefaultName(),n.getNodeFactoryDescriptor());
		registerNode((n=new NodeItemStackSeperate(false)).getDefaultName(),n.getNodeFactoryDescriptor());
		registerNode((n=new NodeMaths(false)).getDefaultName(),n.getNodeFactoryDescriptor());
		loaded = true;
	}
}
