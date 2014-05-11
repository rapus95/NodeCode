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

public class NodeItemCompareOutCount extends Node {

	public NodeItemCompareOutCount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NodeItemCompareOutCount(boolean register) {
		super(register);
		// TODO Auto-generated constructor stub
	}

	public NodeItemCompareOutCount(Grid grid, boolean register) {
		super(grid, register);
		// TODO Auto-generated constructor stub
	}

	public NodeItemCompareOutCount(Grid grid) {
		super(grid);
		// TODO Auto-generated constructor stub
	}

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
	protected void saveTo(XMLNode node) {}

	@Override
	protected void loadFrom(XMLNode node) {}

	private static final INodeFactoryDescriptor desc = new INodeFactoryDescriptor() {
		
		@Override
		public int getUniqueTypeID() {
			return 6;
		}
		
		@Override
		public Node createNode() {
			return new NodeItemCompareOutCount();
		}

		@Override
		public String getDefaultName() {
			return "ItemAmountSelector";
		}

		@Override
		public IPOType getIPOType() {
			return IPOType.PROCESS;
		}
		
		@Override
		public SpecialType getSpecialType() {
			return SpecialType.CONVERT;
		}
	};
	@Override
	public INodeFactoryDescriptor getNodeFactoryDescriptor() {
		return desc;
	}

}
