package main;
import core.Grid;
import core.GridHelper;
import node.NodeMaths;
import XML.XMLNode;


public class Main {

	public static void main(String[] args) {
		NodeMaths nm = new NodeMaths();
		NodeMaths nm1 = new NodeMaths();
		NodeMaths nm2 = new NodeMaths();
		GridHelper.connectProg(nm1, 0, nm2);
		GridHelper.connectProg(nm2, 0, nm);
		GridHelper.connectVal(nm1, 0, nm, 0);
		GridHelper.connectVal(nm2, 0, nm, 1);
		XMLNode t = new XMLNode("test");
		Grid.getCurrent().saveTo(t);
		System.out.println(t);
		//String out = new PC_NodeToMiniscriptConverter().makeCode(t);
		//System.out.println(out);
		return;
	}

}
