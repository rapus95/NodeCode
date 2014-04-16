import type.ItemStack;
import type.ItemStackData;
import type.NumberData;
import core.Node;
import core.PinProgramOut;
import core.PinValueOut;


public class InputNodeSeperator extends Node {

	ItemStack itemStack = new ItemStack(0, 0, 0);
	int dir;
	
	@Override
	protected Node execute() {
		isCalculated=true;
		getValOut(0).setValueUnchecked(itemStack);
		getValOut(1).setValueUnchecked(dir);
		return getProgOut(0).getTarget().getNode();
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
		return "Input Node";
	}
	
	public void setItemStack(ItemStack is){
		this.itemStack = is;
	}
	
	public void setDirection(int i){
		dir = i;
	}

}
