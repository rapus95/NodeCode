package nodecode.node;

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
import nodecode.type.NumberData;
import nodecode.type.SelectionData;

public class NodeBranch extends Node{
	
	public NodeBranch() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NodeBranch(boolean register) {
		super(register);
		// TODO Auto-generated constructor stub
	}

	public NodeBranch(Grid grid, boolean register) {
		super(grid, register);
		// TODO Auto-generated constructor stub
	}

	public NodeBranch(Grid grid) {
		super(grid);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PinBase execute() {
		PinValueIn<?> input1 = getValIn(0);
		PinValueIn<?> input2 = getValIn(1);
		int mode = ((SelectionData)getConfig(0).getData()).getValue();
		if(input1.getValue().equals(input2.getValue())){
			return getProgOut(mode).getTarget();
		}
		return getProgOut(1-mode).getTarget();
	}

	@Override
	public void initInputs(ArrayList<PinProgramIn> progIn, ArrayList<PinValueIn<?>> valIn) {
		valIn.add(new PinValueIn<Number>(this, "in1", 0, new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "in2", 1, new NumberData()));
	}

	@Override
	public void initConfigs(ArrayList<Config<?>> configs) {
		configs.add(new Config<Integer>(this, "Mode", 0, new SelectionData(new String[]{"Equal", "Not Equal"})));
	}

	@Override
	public void initOutputs(ArrayList<PinProgramOut> progOut, ArrayList<PinValueOut<?>> valOut) {
		progOut.clear();
		progOut.add(new PinProgramOut(this, "equal", 0));
		progOut.add(new PinProgramOut(this, "unequal", 1));
	}

	@Override
	protected void saveTo(XMLNode node) {}

	@Override
	protected void loadFrom(XMLNode node) {}

	private static final INodeFactoryDescriptor desc = new INodeFactoryDescriptor() {
		
		@Override
		public int getUniqueTypeID() {
			return 2;
		}
		
		@Override
		public Node createNode() {
			return new NodeBranch();
		}

		@Override
		public String getDefaultName() {
			return "BranchNode";
		}

		@Override
		public IPOType getIPOType() {
			return IPOType.PROCESS;
		}
		
		@Override
		public SpecialType getSpecialType() {
			return SpecialType.FLOW;
		}
	};
	@Override
	public INodeFactoryDescriptor getNodeFactoryDescriptor() {
		return desc;
	}
	
	
	
	
}
