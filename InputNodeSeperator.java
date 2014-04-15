import type.NumberData;
import core.Node;
import core.PinOutput;
import core.PinProgramOut;
import core.PinValueOut;


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
		out[1] = new PinValueOut<Number>(this, "itemID", new NumberData());
		out[2] = new PinValueOut<Number>(this, "itemCount", new NumberData());
	}

	@Override
	public String getName() {
		return "Input Node";
	}

}
