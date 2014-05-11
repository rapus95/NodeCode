package node;

import java.util.ArrayList;

import XML.XMLNode;
import core.Config;
import core.Node;
import core.PinBase;
import core.PinProgramIn;
import core.PinProgramOut;
import core.PinValueIn;
import core.PinValueOut;

public class NodeStart extends Node {

	@Override
	protected PinBase execute() {
		return getProgOut(0).getTarget();
	}

	@Override
	public void initInputs(ArrayList<PinProgramIn> progIn, ArrayList<PinValueIn<?>> valIn) {
		progIn.clear();
	}

	@Override
	public void initConfigs(ArrayList<Config<?>> configs) {}

	@Override
	public void initOutputs(ArrayList<PinProgramOut> progOut, ArrayList<PinValueOut<?>> valOut) {}

	@Override
	public String getDefaultName() {
		return "Entry";
	}

	@Override
	public IPOType getIPOType() {
		return IPOType.INPUT;
	}

	@Override
	protected void saveTo(XMLNode node) {}

	@Override
	protected void loadFrom(XMLNode node) {}

}
