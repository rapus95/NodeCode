package node;

import core.Node;
import core.PinInput;
import core.PinOutput;
import core.PinProgramIn;
import core.PinProgramOut;
import core.PinValue;
import core.PinValueIn;

public class NodeBranch extends Node {
	
	@Override
	public Node run() {
		PinValue<?> input1 = (PinValue<?>) in[1];
		PinValue<?> input2 = (PinValue<?>) in[2];
		
		if(input1.getValue().equals(input2.getValue()))
			return out[0].getTarget().getNode();
		return out[1].getTarget().getNode();
	}

	@Override
	public void initInputs() {
		in = new PinInput[3];
		in[0] = new PinProgramIn(this, "ProgIn");
		in[1] = new PinValueIn<Number>(this, "in1", Number.class);
		in[2] = new PinValueIn<Number>(this, "in2", Number.class);
	}

	@Override
	public void initOutputs() {
		out = new PinOutput[2];
		out[0] = new PinProgramOut(this, "equal");
		out[1] = new PinProgramOut(this, "unequal");
	}

	@Override
	public String getName() {
		return "Branch Node";
	}
	
	
	
	
}
