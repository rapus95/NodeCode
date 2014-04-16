import type.NumberData;
import core.Node;
import core.PinValueIn;


public class OutputNodeSeperator extends Node {

	int outputDirection[] = new int[4];
	int dir;
	
	@Override
	protected Node execute() {
		isCalculated=true;
		outputDirection[0] = PinValueIn.<Number>getValue(getValIn(0)).intValue();
		outputDirection[1] = PinValueIn.<Number>getValue(getValIn(1)).intValue();
		outputDirection[2] = PinValueIn.<Number>getValue(getValIn(2)).intValue();
		outputDirection[3] = PinValueIn.<Number>getValue(getValIn(3)).intValue();
		dir = PinValueIn.<Number>getValue(getValIn(4)).intValue();
		return null;
	}

	@Override
	public void initInputs() {
		valIn.add(new PinValueIn<Number>(this, "outFront", new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "outRight", new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "outBack", new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "outLeft", new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "dir", new NumberData()));
	}

	@Override
	public void initConfigs() {}

	@Override
	public void initOutputs() {}

	@Override
	public String getDefaultName() {
		return "Return Node";
	}
	
	public int[] getOutputDirection(){
		return outputDirection;
	}
	
	public int getDirection(){
		return dir;
	}

}
