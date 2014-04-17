package core;

import java.util.ArrayList;

import XML.XMLNode;

public class Grid {
	private static Grid current = new Grid();
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private int nextID=0;
	
	public int registerNode(Node node) {
		if(nodes.contains(node))
			return node.getUniqueID();
		nodes.add(node);
		return nextID++;
		
	}

	public static Grid getCurrent() {
		return current;
	}

	public Node getNodeByUniqueID(int t) {
		if(t<0) return null;
		for(Node n:nodes){
			if(n.getUniqueID()==t) return n;
		}
		return null;
	}
	
	public void saveTo(XMLNode node){
		node.setProperty("nextID", ""+nextID);
		for(Node n:nodes){
			n.save(node);
		}
	}
	
	public void loadFrom(XMLNode own){
		XMLNode child;
		Node n;
		for(int i=0; i<own.getChildrenAmount(); i++){
			child = own.getChild(i);
			n = NodeFactory.getNewNodeForName(child.getProperty("type"));
			n.load(child);
		}
		for(int i=0; i<nodes.size(); i++){
			nodes.get(i).load2(own.getChild(i));
		}
		nextID = Integer.valueOf(own.getProperty("nextID"));
	}

	public static void setCurrent(Grid grid) {
		current = grid;
	}

}
