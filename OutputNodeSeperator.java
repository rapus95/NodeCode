import core.Node;
import core.PinInput;
import core.PinProgramIn;
import core.PinValue;
import core.PinValueIn;


public class OutputNodeSeperator extends Node {

	int outputDirection[] = new int[4];
	int dir;
	
	@Override
	public Node run() {
		outputDirection[0] = PinValueIn.getValue(((PinValue<?>)in[1]));
		outputDirection[1] = PinValueIn.getValue(((PinValue<?>)in[2]));
		outputDirection[2] = PinValueIn.getValue(((PinValue<?>)in[3]));
		outputDirection[3] = PinValueIn.getValue(((PinValue<?>)in[4]));
		dir = PinValueIn.getValue(((PinValue<?>)in[5]));
		return null;
	}

	@Override
	public void initInputs() {
		in = new PinInput[5];
		in[0] = new PinProgramIn(this, "ProgIn");
		in[1] = new PinValueIn<Number>(this, "outFront", Number.class);
		in[2] = new PinValueIn<Number>(this, "outRight", Number.class);
		in[3] = new PinValueIn<Number>(this, "outBack", Number.class);
		in[4] = new PinValueIn<Number>(this, "outLeft", Number.class);
		in[5] = new PinValueIn<Number>(this, "dir", Number.class);
	}

	@Override
	public void initOutputs() {}

	@Override
	public String getName() {
		return "Return Node";
	}
	
	public int[] getOutputDirection(){
		return outputDirection;
	}
	
	public int getDirection(){
		return dir;
	}

}
