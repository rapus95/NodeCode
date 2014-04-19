package main;
import java.util.ArrayList;

import XML.XMLNode;
import type.NumberData;
import type.SelectionData;
import core.Config;
import core.Node;
import core.PinBase;
import core.PinProgramIn;
import core.PinProgramOut;
import core.PinValueIn;
import core.PinValueOut;


public class OutputNodeSeperator extends Node {
	public static final String defaultName = "ReturnNode";

	int outputDirection[] = new int[4];
	int dir;
	
	@Override
	protected PinBase execute() {
		isCalculated=true;
		outputDirection[0] = PinValueIn.<Number>getValue(getValIn(0)).intValue();
		outputDirection[1] = PinValueIn.<Number>getValue(getValIn(1)).intValue();
		outputDirection[2] = PinValueIn.<Number>getValue(getValIn(2)).intValue();
		outputDirection[3] = PinValueIn.<Number>getValue(getValIn(3)).intValue();
		dir = PinValueIn.getValue(getValIn(4));
		return null;
	}

	@Override
	public void initInputs(PinProgramIn progIn, ArrayList<PinValueIn<?>> valIn) {
		valIn.add(new PinValueIn<Number>(this, "outFront", new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "outRight", new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "outBack", new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "outLeft", new NumberData()));
		valIn.add(new PinValueIn<Integer>(this, "dir", new SelectionData(new String[]{"north", "east", "south", "west"})));
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

}
