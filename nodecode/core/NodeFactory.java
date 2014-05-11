package nodecode.core;

import java.util.HashMap;
import java.util.concurrent.Callable;

import nodecode.XML.XMLNode;

public class NodeFactory {
	
	private static HashMap<String, Callable<? extends Node>> nodes = new HashMap<String, Callable<? extends Node>>();

	public static void registerNode(String name, Callable<Node> n){
		if(!nodes.containsKey(name))
			nodes.put(name, n);
	}
	
	public static Node getNewNodeForName(String type){
		Callable<? extends Node> factory = nodes.get(type);
		try {
			return factory.call();
		} catch (Exception e) {}
		return null;
	}

	public static Node getNewNode(XMLNode child) {
		return getNewNodeForName(child.getString("type"));
	}
	
}
