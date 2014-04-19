package node;

import java.util.ArrayList;

import type.ItemStack;
import type.ItemStackData;
import type.NumberData;
import XML.XMLNode;
import core.Config;
import core.Node;
import core.PinBase;
import core.PinProgramIn;
import core.PinProgramOut;
import core.PinValueIn;
import core.PinValueOut;

public class NodeItemCompareOutCount extends Node {
	public static final String defaultName = "ItemAmountSelector";

	@Override
	protected PinBase execute() {
		ItemStack is = PinValueIn.getValue(getValIn(0));
		ItemStack tmp;
		for(int i=0; i<getAmountOfConfigs(); i++){
			tmp = PinValueIn.getValue(getConfig(i));
			if(tmp==null)
				continue;
			if(is.id==tmp.id && is.meta==tmp.meta)
				getValOut(0).setValueUnchecked(tmp.stacksize);
		}
		return getProgOut(0).getTarget();
	}

	@Override
	public void initInputs(PinProgramIn progIn, ArrayList<PinValueIn<?>> valIn) {
		valIn.add(new PinValueIn<ItemStack>(this, "itemStack", new ItemStackData()));
	}

	@Override
	public void initConfigs(ArrayList<Config<?>> configs) {
		for(int i=0; i<9; i++){
			configs.add(new Config<ItemStack>(this, "item"+(i+1), new ItemStackData()));
		}
	}

	@Override
	public void initOutputs(ArrayList<PinProgramOut> progOut, ArrayList<PinValueOut<?>> valOut) {
		progOut.add(new PinProgramOut(this, "progOut"));
		valOut.add(new PinValueOut<Number>(this, "amount", new NumberData()));
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
