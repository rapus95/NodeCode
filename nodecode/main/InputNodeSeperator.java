package nodecode.main;
import java.util.ArrayList;

import nodecode.XML.XMLNode;
import nodecode.core.Config;
import nodecode.core.Grid;
import nodecode.core.INodeFactoryDescriptor;
import nodecode.core.Node;
import nodecode.core.PinBase;
import nodecode.core.PinProgramIn;
import nodecode.core.PinProgramOut;
import nodecode.core.PinValueIn;
import nodecode.core.PinValueOut;
import nodecode.node.NodeBranch;
import nodecode.type.ItemStack;
import nodecode.type.ItemStackData;
import nodecode.type.NumberData;


public class InputNodeSeperator extends Node {

	public InputNodeSeperator() {
		super();
	}

	public InputNodeSeperator(boolean register) {
		super(register);
	}

	public InputNodeSeperator(Grid grid, boolean register) {
		super(grid, register);
	}

	public InputNodeSeperator(Grid grid) {
		super(grid);
	}

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

	private static final INodeFactoryDescriptor desc = new INodeFactoryDescriptor() {
		
		@Override
		public int getUniqueTypeID() {
			return 40;
		}
		
		@Override
		public Node createNode() {
			return new InputNodeSeperator();
		}

		@Override
		public String getDefaultName() {
			return "InputNode";
		}
	};
	@Override
	public INodeFactoryDescriptor getNodeFactoryDescriptor() {
		return desc;
	}

}
