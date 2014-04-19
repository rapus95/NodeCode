package node;

import XML.XMLNode;
import type.ItemStack;
import type.ItemStackData;
import type.NumberData;
import core.Node;
import core.PinBase;
import core.PinProgramOut;
import core.PinValueIn;
import core.PinValueOut;

public class NodeItemStackSeperate extends Node {
	public static final String defaultName = "ItemStackSeperator";

	@Override
	protected PinBase execute() {
		ItemStack itemStack = PinValueIn.getValue(getValIn(0));
		getValOut(0).setValueUnchecked(itemStack);
		getValOut(1).setValueUnchecked(itemStack.id);
		getValOut(2).setValueUnchecked(itemStack.meta);
		getValOut(3).setValueUnchecked(itemStack.stacksize);
		return getProgOut(0).getTarget();
	}

	@Override
	public void initInputs() {
		valIn.add(new PinValueIn<ItemStack>(this, "itemStack", new ItemStackData())); 
	}

	@Override
	public void initConfigs() {}

	@Override
	public void initOutputs() {
		progOut.add(new PinProgramOut(this, "ProgOut"));
		valOut.add(new PinValueOut<ItemStack>(this, "itemRef", new ItemStackData()));
		valOut.add(new PinValueOut<Number>(this, "itemID", new NumberData()));
		valOut.add(new PinValueOut<Number>(this, "meta", new NumberData()));
		valOut.add(new PinValueOut<Number>(this, "stackSize", new NumberData()));
	}

	@Override
	public String getDefaultName() {
		return defaultName;
	}

	@Override
	protected void saveTo(XMLNode node) {}

	@Override
	protected void loadFrom(XMLNode node) {}

}
