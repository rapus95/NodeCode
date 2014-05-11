package core;

import XML.XMLNode;

public class Helper {

	@SuppressWarnings("unchecked")
	public static <Type> Type getValue(ValueHandler<?> in){
		return (Type)in.getValue();
	}

	public static boolean isNodeType(XMLNode n, String name){
		return n.getString("type").equalsIgnoreCase(name);
	}
}
