package core;

import main.InputNodeSeperator;
import main.OutputNodeSeperator;
import node.NodeBranch;
import node.NodeCountLoop;
import node.NodeItemCompareOutCount;
import node.NodeItemStackSeperate;
import node.NodeMaths;

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
}
