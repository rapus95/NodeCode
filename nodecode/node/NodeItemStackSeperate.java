package nodecode.node;

import java.util.ArrayList;

import nodecode.XML.XMLNode;
import nodecode.core.Config;
import nodecode.core.Grid;
import nodecode.core.Helper;
import nodecode.core.INodeFactoryDescriptor;
import nodecode.core.Node;
import nodecode.core.PinBase;
import nodecode.core.PinProgramIn;
import nodecode.core.PinProgramOut;
import nodecode.core.PinValueIn;
import nodecode.core.PinValueOut;
import nodecode.type.ItemStack;
import nodecode.type.ItemStackData;
import nodecode.type.NumberData;

public class NodeItemStackSeperate extends Node {

	public NodeItemStackSeperate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NodeItemStackSeperate(boolean register) {
		super(register);
		// TODO Auto-generated constructor stub
	}

	public NodeItemStackSeperate(Grid grid, boolean register) {
		super(grid, register);
		// TODO Auto-generated constructor stub
	}

	public NodeItemStackSeperate(Grid grid) {
		super(grid);
		// TODO Auto-generated constructor stub
	}

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
	public void initInputs(ArrayList<PinProgramIn> progIn, ArrayList<PinValueIn<?>> valIn) {
		valIn.add(new PinValueIn<ItemStack>(this, "itemStack", 0, new ItemStackData())); 
	}

	@Override
	public void initConfigs(ArrayList<Config<?>> configs) {}

	@Override
	public void initOutputs(ArrayList<PinProgramOut> progOut, ArrayList<PinValueOut<?>> valOut) {
		valOut.add(new PinValueOut<ItemStack>(this, "itemRef", 0, new ItemStackData()));
		valOut.add(new PinValueOut<Number>(this, "itemID", 1, new NumberData()));
		valOut.add(new PinValueOut<Number>(this, "meta", 2, new NumberData()));
		valOut.add(new PinValueOut<Number>(this, "stackSize", 3, new NumberData()));
	}

	@Override
	protected void saveTo(XMLNode node) {}

	@Override
	protected void loadFrom(XMLNode node) {}

	private static final INodeFactoryDescriptor desc = new INodeFactoryDescriptor() {
		
		@Override
		public int getUniqueTypeID() {
			return 5;
		}
		
		@Override
		public Node createNode() {
			return new NodeItemStackSeperate();
		}

		@Override
		public String getDefaultName() {
			return "ItemStackSeperator";
		}

		@Override
		public IPOType getIPOType() {
			return IPOType.PROCESS;
		}
		
		@Override
		public SpecialType getSpecialType() {
			return SpecialType.SPLIT;
		}
	};
	@Override
	public INodeFactoryDescriptor getNodeFactoryDescriptor() {
		return desc;
	}

}
