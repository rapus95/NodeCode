package node;

import XML.XMLNode;
import type.NumberData;
import core.GridHelper;
import core.Node;
import core.PinProgramOut;
import core.PinValueIn;
import core.PinValueOut;

public class NodeCountLoop extends Node {
	public static final String defaultName = "Loop";
	
	@Override
	protected Node execute() {
		double start = PinValueIn.<Number>getValue(getValIn(0)).doubleValue();
		double end = PinValueIn.<Number>getValue(getValIn(1)).doubleValue();
		double stepSize = PinValueIn.<Number>getValue(getValIn(2)).doubleValue();
		for(double d=start; d<=end; d+=stepSize){
			getValOut(0).setValueUnchecked(d);
			GridHelper.runProgram(getProgOut(1).getTarget().getNode());
		}
		return getProgOut(0).getTarget().getNode();
	}

	@Override
	public void initInputs() {
		valIn.add(new PinValueIn<Number>(this, "start", new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "last", new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "stepSize", new NumberData()));
	}

	@Override
	public void initConfigs() {
		
	}

	@Override
	public void initOutputs() {
		progOut.add(new PinProgramOut(this, "progOut"));
		progOut.add(new PinProgramOut(this, "loop"));
		valOut.add(new PinValueOut<Number>(this, "counter", new NumberData()));
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
