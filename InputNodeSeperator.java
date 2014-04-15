import type.PinNumber;
import core.Node;
import core.PinOutput;
import core.PinProgramOut;


public class InputNodeSeperator extends Node {

	@Override
	public Node run() {
		return out[0].getTarget().getNode();
	}

	@Override
	public void initInputs() {}

	@Override
	public void initOutputs() {
		out = new PinOutput[3];
		out[0] = new PinProgramOut(this, "ProgOut");
		out[1] = new PinNumber(this, "itemID");
		out[2] = new PinNumber(this, "itemCount");
	}

	@Override
	public String getName() {
		return "Input Node";
	}

}
