package main;
import XML.XMLNode;
import type.ItemStack;
import type.ItemStackData;
import type.NumberData;
import core.Node;
import core.PinBase;
import core.PinProgramOut;
import core.PinValueOut;


public class InputNodeSeperator extends Node {
	public static final String defaultName = "InputNode";

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
	public void initInputs() {}

	@Override
	public void initConfigs() {}

	@Override
	public void initOutputs() {
		progOut.add(new PinProgramOut(this, "ProgOut"));
		valOut.add(new PinValueOut<ItemStack>(this, "itemStack", new ItemStackData()));
		valOut.add(new PinValueOut<Number>(this, "dir", new NumberData()));
	}

	@Override
	public String getDefaultName() {
		return defaultName;
	}
	
	public void setItemStack(ItemStack is){
		this.itemStack = is;
	}
	
	public void setDirection(int i){
		dir = i;
	}

	@Override
	protected void saveTo(XMLNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void loadFrom(XMLNode node) {
		// TODO Auto-generated method stub
		
	}

}
