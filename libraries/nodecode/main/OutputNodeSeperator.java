package libraries.nodecode.main;
import java.util.ArrayList;

import libraries.nodecode.XML.XMLNode;
import libraries.nodecode.core.Config;
import libraries.nodecode.core.Helper;
import libraries.nodecode.core.Node;
import libraries.nodecode.core.PinBase;
import libraries.nodecode.core.PinProgramIn;
import libraries.nodecode.core.PinProgramOut;
import libraries.nodecode.core.PinValueIn;
import libraries.nodecode.core.PinValueOut;
import libraries.nodecode.type.NumberData;
import libraries.nodecode.type.SelectionData;


public class OutputNodeSeperator extends Node {
	public static final String defaultName = "ReturnNode";

	int outputDirection[] = new int[4];
	int dir;
	
	@Override
	protected PinBase execute() {
		isCalculated=true;
		outputDirection[0] = Helper.<Number>getValue(getValIn(0)).intValue();
		outputDirection[1] = Helper.<Number>getValue(getValIn(1)).intValue();
		outputDirection[2] = Helper.<Number>getValue(getValIn(2)).intValue();
		outputDirection[3] = Helper.<Number>getValue(getValIn(3)).intValue();
		dir = Helper.getValue(getValIn(4));
		return null;
	}

	@Override
	public void initInputs(PinProgramIn progIn, ArrayList<PinValueIn<?>> valIn) {
		valIn.add(new PinValueIn<Number>(this, "outFront", 0, new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "outRight", 1, new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "outBack", 2, new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "outLeft", 3, new NumberData()));
		valIn.add(new PinValueIn<Integer>(this, "dir", 4, new SelectionData(new String[]{"north", "east", "south", "west"})));
	}

	@Override
	public void initConfigs(ArrayList<Config<?>> configs) {}

	@Override
	public void initOutputs(ArrayList<PinProgramOut> progOut, ArrayList<PinValueOut<?>> valOut) {}

	@Override
	public String getDefaultName() {
		return defaultName;
	}
	
	public int[] getOutputDirection(){
		return outputDirection;
	}
	
	public int getDirection(){
		return dir;
	}

	@Override
	protected void saveTo(XMLNode node) {}

	@Override
	protected void loadFrom(XMLNode node) {}

	@Override
	public IPOType getIPOType() {
		return IPOType.OUTPUT;
	}

}
