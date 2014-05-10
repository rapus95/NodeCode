package libraries.nodecode.node;

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
import libraries.nodecode.type.ItemStack;
import libraries.nodecode.type.ItemStackData;
import libraries.nodecode.type.NumberData;

public class NodeItemStackSeperate extends Node {
	public static final String defaultName = "ItemStackSeperator";

	@Override
	protected PinBase execute() {
		ItemStack itemStack = Helper.getValue(getValIn(0));
		getValOut(0).setValueUnchecked(itemStack);
		getValOut(1).setValueUnchecked(itemStack.id);
		getValOut(2).setValueUnchecked(itemStack.meta);
		getValOut(3).setValueUnchecked(itemStack.stacksize);
		return getProgOut(0).getTarget();
	}

	@Override
	public void initInputs(PinProgramIn progIn, ArrayList<PinValueIn<?>> valIn) {
		valIn.add(new PinValueIn<ItemStack>(this, "itemStack", 0, new ItemStackData())); 
	}

	@Override
	public void initConfigs(ArrayList<Config<?>> configs) {}

	@Override
	public void initOutputs(ArrayList<PinProgramOut> progOut, ArrayList<PinValueOut<?>> valOut) {
		progOut.add(new PinProgramOut(this, "ProgOut", 0));
		valOut.add(new PinValueOut<ItemStack>(this, "itemRef", 0, new ItemStackData()));
		valOut.add(new PinValueOut<Number>(this, "itemID", 1, new NumberData()));
		valOut.add(new PinValueOut<Number>(this, "meta", 2, new NumberData()));
		valOut.add(new PinValueOut<Number>(this, "stackSize", 3, new NumberData()));
	}

	@Override
	public String getDefaultName() {
		return defaultName;
	}

	@Override
	protected void saveTo(XMLNode node) {}

	@Override
	protected void loadFrom(XMLNode node) {}

	@Override
	public IPOType getIPOType() {
		return IPOType.PROCESS;
	}

}
