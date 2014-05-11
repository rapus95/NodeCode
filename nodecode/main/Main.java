package nodecode.main;
import java.util.concurrent.Callable;

import nodecode.XML.XMLNode;
import nodecode.core.GridHandler;
import nodecode.core.GridHelper;
import nodecode.core.Node;
import nodecode.node.NodeMaths;


public class Main {

	public static void main(String[] args) {
		GridHandler.registerNode(new InputNodeSeperator().getName(), new Callable<Node>() {
			
			@Override
			public Node call() throws Exception {
				return new InputNodeSeperator();
			}
		});
		GridHandler.registerNode(new OutputNodeSeperator().getName(), new Callable<Node>() {
			
			@Override
			public Node call() throws Exception {
				return new OutputNodeSeperator();
			}
		});
		NodeMaths nm = new NodeMaths();
		NodeMaths nm1 = new NodeMaths();
		NodeMaths nm2 = new NodeMaths();
		GridHelper.connectProg(nm1, 0, nm2);
		GridHelper.connectProg(nm2, 0, nm);
		GridHelper.connectVal(nm1, 0, nm, 0);
		GridHelper.connectVal(nm2, 0, nm, 1);
		XMLNode t = new XMLNode("test");
		GridHandler.getCurrent().saveTo(t);
		System.out.println(t);
		//String out = new PC_NodeToMiniscriptConverter().makeCode(t);
		//System.out.println(out);
		return;
	}

}
