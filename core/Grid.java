package core;

import java.util.ArrayList;

public class Grid {
	public static Grid current;
	private ArrayList<Node> nodes = new ArrayList<Node>();
	
	public void registerNode(Node node) {
		if(!nodes.contains(node))
			nodes.add(node);
	}
	
	public void save(){
		
	}

}
