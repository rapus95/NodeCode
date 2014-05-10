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
		String out = new PC_NodeToMiniscriptConverter().makeCode(t);
		System.out.println(out);
		return;
		/*InputNodeSeperator in = new InputNodeSeperator();
		NodeItemStackSeperate sep = new NodeItemStackSeperate();
		NodeBranch branch = new NodeBranch();
		SelectionData sd = (SelectionData)branch.getConfig(0).getData();
		sd.setValueUnchecked(1);
		OutputNodeSeperator out0 = new OutputNodeSeperator();
		OutputNodeSeperator out1 = new OutputNodeSeperator();
		in.setItemStack(new ItemStack(12, 13, 53));
		in.setDirection(2);
		GridHelper.connectProg(in, 0, sep);
		GridHelper.connectVal(in, 0, sep, 0);
		GridHelper.connectProg(sep, 0, branch);
		GridHelper.connectVal(sep, 1, branch, 0);
		GridHelper.connectVal(sep, 2, branch, 1);
		GridHelper.connectProg(branch, 0, out0);
		GridHelper.connectProg(branch, 1, out1);
		GridHelper.setValue(branch, 0, 12);
		GridHelper.setValue(branch, 1, 15);
		GridHelper.setValue(out0, 1, 12);
		GridHelper.setValue(out1, 3, 12);
		GridHelper.connectVal(in, 1, out0, 4);
		GridHelper.connectVal(in, 1, out1, 4);
		long l = System.nanoTime();
		System.out.println(l);
		Node last = GridHelper.runProgram(in);
		System.out.println((-l+(l=System.nanoTime()))/1000000D);
		Grid.getCurrent().reset();
		l = System.nanoTime();
		last = GridHelper.runProgram(in);
		System.out.println((-l+(l=System.nanoTime()))/1000000D);
		Grid.getCurrent().reset();
		l = System.nanoTime();
		last = GridHelper.runProgram(in);
		System.out.println((-l+(l=System.nanoTime()))/1000000D);
		Grid.getCurrent().reset();
		l = System.nanoTime();
		last = GridHelper.runProgram(in);
		System.out.println((-l+(l=System.nanoTime()))/1000000D);
		if(last instanceof OutputNodeSeperator){
			OutputNodeSeperator on = (OutputNodeSeperator) last;
			System.out.println("dir: " + on.getDirection());
			System.out.println("out: " + Arrays.toString(on.getOutputDirection()));
		}*/
	}

}
