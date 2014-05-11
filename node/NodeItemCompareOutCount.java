package node;

import java.util.ArrayList;

import XML.XMLNode;
import type.ItemStack;
import type.ItemStackData;
import type.NumberData;
import core.Config;
import core.Helper;
import core.Node;
import core.PinBase;
import core.PinProgramIn;
import core.PinProgramOut;
import core.PinValueIn;
import core.PinValueOut;

public class NodeItemCompareOutCount extends Node {

	@Override
	protected PinBase execute() {
		ItemStack is = Helper.getValue(getValIn(0));
		ItemStack tmp;
		for(int i=0; i<getAmountOfConfigs(); i++){
			tmp = Helper.getValue(getConfig(i));
			if(tmp==null)
				continue;
			if(is.id==tmp.id && is.meta==tmp.meta)
				getValOut(0).setValueUnchecked(tmp.stacksize);
		}
		return getProgOut(0).getTarget();
	}

	@Override
	public void initInputs(ArrayList<PinProgramIn> progIn, ArrayList<PinValueIn<?>> valIn) {
		valIn.add(new PinValueIn<ItemStack>(this, "itemStack", 0, new ItemStackData()));
	}

	@Override
	public void initConfigs(ArrayList<Config<?>> configs) {
		for(int i=0; i<9; i++){
			configs.add(new Config<ItemStack>(this, "item"+(i+1), 0, new ItemStackData()));
		}
	}

	@Override
	public void initOutputs(ArrayList<PinProgramOut> progOut, ArrayList<PinValueOut<?>> valOut) {
		valOut.add(new PinValueOut<Number>(this, "amount", 0, new NumberData()));
	}

	@Override
	public String getDefaultName() {
		return "ItemAmountSelector";
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
