import java.util.Arrays;

import node.NodeBranch;
import node.NodeItemStackSeperate;
import type.ItemStack;
import type.SelectionData;
import core.GridHelper;
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
		Node last = GridHelper.runProgram(in);
		if(last instanceof OutputNodeSeperator){
			OutputNodeSeperator on = (OutputNodeSeperator) last;
			System.out.println("dir: " + on.getDirection());
			System.out.println("out: " + Arrays.toString(on.getOutputDirection()));
		}
		
	}

}
