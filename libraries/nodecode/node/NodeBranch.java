package libraries.nodecode.node;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import libraries.nodecode.XML.XMLNode;
import libraries.nodecode.core.Config;
import libraries.nodecode.core.Node;
import libraries.nodecode.core.NodeFactory;
import libraries.nodecode.core.PinBase;
import libraries.nodecode.core.PinProgramIn;
import libraries.nodecode.core.PinProgramOut;
import libraries.nodecode.core.PinValueIn;
import libraries.nodecode.core.PinValueOut;
import libraries.nodecode.type.NumberData;
import libraries.nodecode.type.SelectionData;

public class NodeBranch extends Node{
	
	@Override
	protected PinBase execute() {
		PinValueIn<?> input1 = getValIn(0);
		PinValueIn<?> input2 = getValIn(1);
		int mode = ((SelectionData)getConfig(0).getData()).getValue();
		if(input1.getValue().equals(input2.getValue())){
			return getProgOut(mode).getTarget();
		}
		return getProgOut(1-mode).getTarget();
	}

	@Override
	public void initInputs(ArrayList<PinProgramIn> progIn, ArrayList<PinValueIn<?>> valIn) {
		valIn.add(new PinValueIn<Number>(this, "in1", 0, new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "in2", 1, new NumberData()));
	}

	@Override
	public void initConfigs(ArrayList<Config<?>> configs) {
		configs.add(new Config<Integer>(this, "Mode", 0, new SelectionData(new String[]{"Equal", "Not Equal"})));
	}

	@Override
	public void initOutputs(ArrayList<PinProgramOut> progOut, ArrayList<PinValueOut<?>> valOut) {
		progOut.clear();
		progOut.add(new PinProgramOut(this, "equal", 0));
		progOut.add(new PinProgramOut(this, "unequal", 1));
	}

	@Override
	public String getDefaultName() {
		return "BranchNode";
	}

	@Override
	protected void saveTo(XMLNode node) {}

	@Override
	protected void loadFrom(XMLNode node) {}

	@Override
	public IPOType getIPOType() {
		return IPOType.PROCESS;
	}
	
	
	
	
}
