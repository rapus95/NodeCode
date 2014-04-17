package node;

import XML.XMLNode;
import type.NumberData;
import type.SelectionData;
import core.Config;
import core.Node;
import core.PinProgramOut;
import core.PinValueIn;

public class NodeBranch extends Node {
	public static final String defaultName = "BranchNode";
	
	@Override
	protected Node execute() {
		PinValueIn<?> input1 = getValIn(0);
		PinValueIn<?> input2 = getValIn(1);
		int mode = ((SelectionData)getConfig(0).getData()).getValue();
		if(input1.getValue().equals(input2.getValue())){
			return getProgOut(mode).getTarget().getNode();
		}
		return getProgOut(1-mode).getTarget().getNode();
	}

	@Override
	public void initInputs() {
		valIn.add(new PinValueIn<Number>(this, "in1", new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "in2", new NumberData()));
	}

	@Override
	public void initConfigs() {
		configs.add(new Config<Integer>("Mode", this, new SelectionData(new String[]{"Equal", "Not Equal"})));
	}

	@Override
	public void initOutputs() {
		progOut.add(new PinProgramOut(this, "equal"));
		progOut.add(new PinProgramOut(this, "unequal"));
	}

	@Override
	public String getDefaultName() {
		return defaultName;
	}

	@Override
	protected void saveTo(XMLNode node) {}

	@Override
	protected void loadFrom(XMLNode node) {}
	
	
	
	
}
