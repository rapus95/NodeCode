import java.util.Arrays;

import node.NodeBranch;
import node.NodeItemStackSeperate;
import type.ItemStack;
import type.SelectionData;
import core.Grid;
import core.Node;


public class Main {

	public static void main(String[] args) {
		InputNodeSeperator in = new InputNodeSeperator();
		NodeItemStackSeperate sep = new NodeItemStackSeperate();
		NodeBranch branch = new NodeBranch();
		SelectionData sd = (SelectionData)branch.getConfig(0).getData();
		sd.setValueUnchecked(1);
		OutputNodeSeperator out0 = new OutputNodeSeperator();
		OutputNodeSeperator out1 = new OutputNodeSeperator();
		in.setItemStack(new ItemStack(12, 13, 53));
		in.setDirection(2);
		Grid.connectProg(in, 0, sep);
		Grid.connectVal(in, 0, sep, 0);
		Grid.connectProg(sep, 0, branch);
		Grid.connectVal(sep, 1, branch, 0);
		Grid.connectVal(sep, 2, branch, 1);
		Grid.connectProg(branch, 0, out0);
		Grid.connectProg(branch, 1, out1);
		Grid.setValue(branch, 0, 12);
		Grid.setValue(branch, 1, 15);
		Grid.setValue(out0, 1, 12);
		Grid.setValue(out1, 3, 12);
		Grid.connectVal(in, 1, out0, 4);
		Grid.connectVal(in, 1, out1, 4);
		Node last = Grid.runProgram(in);
		if(last instanceof OutputNodeSeperator){
			OutputNodeSeperator on = (OutputNodeSeperator) last;
			System.out.println("dir: " + on.getDirection());
			System.out.println("out: " + Arrays.toString(on.getOutputDirection()));
		}
		
	}

}
