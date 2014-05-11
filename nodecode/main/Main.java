package nodecode.main;
import nodecode.XML.XMLNode;
import nodecode.core.GridHandler;
import nodecode.core.GridHelper;
import nodecode.core.Node;
import nodecode.core.NodeFactory;
import nodecode.node.NodeMaths;


public class Main {

	public static void main(String[] args) {
		Node n;
		NodeFactory.registerNode((n=new InputNodeSeperator(false)).getDefaultName(),n.getNodeFactoryDescriptor());
		NodeFactory.registerNode((n=new OutputNodeSeperator(false)).getDefaultName(),n.getNodeFactoryDescriptor());
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
