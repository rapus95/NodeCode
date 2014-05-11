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
import nodecode.type.NumberData;

public class NodeCountLoop extends Node {
	
	public NodeCountLoop() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NodeCountLoop(boolean register) {
		super(register);
		// TODO Auto-generated constructor stub
	}

	public NodeCountLoop(Grid grid, boolean register) {
		super(grid, register);
		// TODO Auto-generated constructor stub
	}

	public NodeCountLoop(Grid grid) {
		super(grid);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PinBase execute() {
		double start = Helper.<Number>getValue(getValIn(0)).doubleValue();
		double end = Helper.<Number>getValue(getValIn(1)).doubleValue();
		double stepSize = Helper.<Number>getValue(getValIn(2)).doubleValue();
		for(double d=start; d<=end; d+=stepSize){
			getValOut(0).setValueUnchecked(d);
			Grid.runProgram(getProgOut(1).getTarget().getNode());
		}
		return getProgOut(0).getTarget();
	}

	@Override
	public void initInputs(ArrayList<PinProgramIn> progIn, ArrayList<PinValueIn<?>> valIn) {
		valIn.add(new PinValueIn<Number>(this, "start", 0, new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "last", 1, new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "stepSize", 2, new NumberData()));
	}

	@Override
	public void initConfigs(ArrayList<Config<?>> configs) {
		
	}

	@Override
	public void initOutputs(ArrayList<PinProgramOut> progOut, ArrayList<PinValueOut<?>> valOut) {
		progOut.add(new PinProgramOut(this, "loop", 1));
		valOut.add(new PinValueOut<Number>(this, "counter", 0, new NumberData()));
	}

	@Override
	protected void saveTo(XMLNode node) {}

	@Override
	protected void loadFrom(XMLNode node) {}

	private static final INodeFactoryDescriptor desc = new INodeFactoryDescriptor() {
		
		@Override
		public int getUniqueTypeID() {
			return 3;
		}
		
		@Override
		public Node createNode() {
			return new NodeCountLoop();
		}

		@Override
		public String getDefaultName() {
			return "Loop";
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
