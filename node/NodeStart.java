package libraries.nodecode.node;

import java.util.ArrayList;

import libraries.nodecode.XML.XMLNode;
import libraries.nodecode.core.Config;
import libraries.nodecode.core.Node;
import libraries.nodecode.core.PinBase;
import libraries.nodecode.core.PinProgramIn;
import libraries.nodecode.core.PinProgramOut;
import libraries.nodecode.core.PinValueIn;
import libraries.nodecode.core.PinValueOut;

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
