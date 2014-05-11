package core;

import java.util.ArrayList;

import XML.XMLNode;

public class Grid {
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private int nextID=0;
	
	public int registerNode(Node node) {
		if(nodes.contains(node))
			return node.getUniqueID();
		nodes.add(node);
		return nextID++;
		
	}

	public Node getNodeByUniqueID(int t) {
		if(t<0) return null;
		for(Node n:nodes){
			if(n.getUniqueID()==t) return n;
		}
		return null;
	}
	
	public void saveTo(XMLNode node){
		node.setInt("nextID", nextID);
		for(Node n:nodes){
			n.save(node);
		}
	}
	
	public void loadFrom(XMLNode own){
		XMLNode child;
		Node n;
		for(int i=0; i<own.getChildrenAmount(); i++){
			child = own.getChild(i);
			n = NodeFactory.getNewNode(child);
			n.load(child);
		}
		for(int i=0; i<nodes.size(); i++){
			nodes.get(i).load2(own.getChild(i));
		}
		nextID = Integer.valueOf(own.getInt("nextID"));
	}
	
	public void reset(){
		for(int i=0; i<nodes.size(); i++){
			nodes.get(i).reset();
		}
	}
	
	public static Node runProgram(Node start){
		Node next=start;
		Node tmp;
		while((tmp=next.run())!=null){next=tmp;};
		return next;
	}

}
