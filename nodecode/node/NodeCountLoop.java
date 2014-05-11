package nodecode.node;

import java.util.ArrayList;

import nodecode.XML.XMLNode;
import nodecode.core.Config;
import nodecode.core.GridHelper;
import nodecode.core.Helper;
import nodecode.core.Node;
import nodecode.core.PinBase;
import nodecode.core.PinProgramIn;
import nodecode.core.PinProgramOut;
import nodecode.core.PinValueIn;
import nodecode.core.PinValueOut;
import nodecode.type.NumberData;

public class NodeCountLoop extends Node {
	
	@Override
	protected PinBase execute() {
		double start = Helper.<Number>getValue(getValIn(0)).doubleValue();
		double end = Helper.<Number>getValue(getValIn(1)).doubleValue();
		double stepSize = Helper.<Number>getValue(getValIn(2)).doubleValue();
		for(double d=start; d<=end; d+=stepSize){
			getValOut(0).setValueUnchecked(d);
			GridHelper.runProgram(getProgOut(1).getTarget().getNode());
		}
		return getProgOut(0).getTarget();
	}

	@Override
	public void initInputs(ArrayList<PinProgramIn> progIn, ArrayList<PinValueIn<?>> valIn) {
		valIn.add(new PinValueIn<Number>(this, "start", 0, new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "last", 1, new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "stepSize", 2, new NumberData()));
	}

	@Override
	public void initConfigs(ArrayList<Config<?>> configs) {
		
	}

	@Override
	public void initOutputs(ArrayList<PinProgramOut> progOut, ArrayList<PinValueOut<?>> valOut) {
		progOut.add(new PinProgramOut(this, "loop", 1));
		valOut.add(new PinValueOut<Number>(this, "counter", 0, new NumberData()));
	}

	@Override
	public String getDefaultName() {
		return "Loop";
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
