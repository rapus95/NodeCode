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

public class NodeEntry extends Node {

	public NodeEntry() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NodeEntry(boolean register) {
		super(register);
		// TODO Auto-generated constructor stub
	}

	public NodeEntry(Grid grid, boolean register) {
		super(grid, register);
		// TODO Auto-generated constructor stub
	}

	public NodeEntry(Grid grid) {
		super(grid);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PinBase execute() {
		return getProgOut(0).getTarget();
	}

	@Override
	public void initInputs(ArrayList<PinProgramIn> progIn, ArrayList<PinValueIn<?>> valIn) {
		progIn.clear();
	}

	@Override
	public void initConfigs(ArrayList<Config<?>> configs) {}

	@Override
	public void initOutputs(ArrayList<PinProgramOut> progOut, ArrayList<PinValueOut<?>> valOut) {}

	@Override
	public IPOType getIPOType() {
		return IPOType.INPUT;
	}

	@Override
	protected void saveTo(XMLNode node) {}

	@Override
	protected void loadFrom(XMLNode node) {}

	
	private static final INodeFactoryDescriptor desc = new INodeFactoryDescriptor() {
		
		@Override
		public int getUniqueTypeID() {
			return 1;
		}
		
		@Override
		public Node createNode() {
			return new NodeEntry();
		}

		@Override
		public String getDefaultName() {
			return "Entry";
		}
	};
	@Override
	public INodeFactoryDescriptor getNodeFactoryDescriptor() {
		return desc;
	}

}
