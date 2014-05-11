package main;
import java.util.ArrayList;

import XML.XMLNode;
import type.ItemStack;
import type.ItemStackData;
import type.NumberData;
import core.Config;
import core.Node;
import core.PinBase;
import core.PinProgramIn;
import core.PinProgramOut;
import core.PinValueIn;
import core.PinValueOut;


public class InputNodeSeperator extends Node {

	ItemStack itemStack = new ItemStack(0, 0, 0);
	int dir;
	
	@Override
	protected PinBase execute() {
		isCalculated=true;
		getValOut(0).setValueUnchecked(itemStack);
		getValOut(1).setValueUnchecked(dir);
		return getProgOut(0).getTarget();
	}

	@Override
	public void initInputs(ArrayList<PinProgramIn> progIn, ArrayList<PinValueIn<?>> valIn) {}

	@Override
	public void initConfigs(ArrayList<Config<?>> configs) {}

	@Override
	public void initOutputs(ArrayList<PinProgramOut> progOut, ArrayList<PinValueOut<?>> valOut) {
		valOut.add(new PinValueOut<ItemStack>(this, "itemStack", 0, new ItemStackData()));
		valOut.add(new PinValueOut<Number>(this, "dir", 1, new NumberData()));
	}

	@Override
	public String getDefaultName() {
		return "InputNode";
	}
	
	public void setItemStack(ItemStack is){
		this.itemStack = is;
	}
	
	public void setDirection(int i){
		dir = i;
	}

	@Override
	protected void saveTo(XMLNode node) {}

	@Override
	protected void loadFrom(XMLNode node) {}

	@Override
	public IPOType getIPOType() {
		return IPOType.INPUT;
	}

}
