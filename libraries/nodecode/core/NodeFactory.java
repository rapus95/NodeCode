package libraries.nodecode.core;

import libraries.nodecode.XML.XMLNode;
import libraries.nodecode.main.InputNodeSeperator;
import libraries.nodecode.main.OutputNodeSeperator;
import libraries.nodecode.node.NodeBranch;
import libraries.nodecode.node.NodeCountLoop;
import libraries.nodecode.node.NodeItemCompareOutCount;
import libraries.nodecode.node.NodeItemStackSeperate;
import libraries.nodecode.node.NodeMaths;

public class NodeFactory {

	public static Node getNewNodeForName(String type){
		if(type.equalsIgnoreCase(NodeBranch.defaultName))
			return new NodeBranch();
		else if(type.equalsIgnoreCase(NodeCountLoop.defaultName))
			return new NodeCountLoop();
		else if(type.equalsIgnoreCase(NodeItemStackSeperate.defaultName))
			return new NodeItemStackSeperate();
		else if (type.equalsIgnoreCase(NodeItemCompareOutCount.defaultName))
			return new NodeItemCompareOutCount();
		else if (type.equalsIgnoreCase(NodeMaths.defaultName))
			return new NodeMaths();
		
		
		
		else if (type.equalsIgnoreCase(InputNodeSeperator.defaultName))
			return new InputNodeSeperator();
		else if (type.equalsIgnoreCase(OutputNodeSeperator.defaultName))
			return new OutputNodeSeperator();
		return null;
			
	}

	public static Node getNewNode(XMLNode child) {
		return getNewNodeForName(child.getString("type"));
	}
}
