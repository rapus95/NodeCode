package core;

import XML.XMLNode;
import core.ValueType.COLOR;




public class PinProgramOut extends PinBaseImp implements PinOutput, PinProgram {

	public PinProgramOut(Node parent, String name) {
		super(parent, name);
	}

	private PinProgramIn target = null;

	@Override
	public void setTargetUnchecked(PinInput target) {
		if(isValidFor(target)){
			setTarget((PinProgramIn) target);
			return;
		}
		throw new RuntimeException("WRONG PIN-TYPE!!");
	}
	
	public void setTarget(PinProgramIn target) {
		this.target = target;
	}

	@Override
	public void removeTargetUnchecked(PinInput target) {
		if(this.target==target)
			target=null;
	}

	@Override
	public PinProgramIn getTarget() {
		return target;
	}
	
	@Override
	public boolean isValidFor(PinInput in) {
		return in instanceof PinProgramIn;
	}

	@Override
	public COLOR getColor() {
		return COLOR.RED;
	}

	@Override
	public void init() {}

	@Override
	public void reset() {}
	
	@Override
	public void saveTo(XMLNode node) {
		XMLNode n = new XMLNode(name);
		n.setProperty("type", "progOut");
		n.setProperty("targetNode", ""+(target==null?-1:target.getNode().getUniqueID()));
		n.setProperty("targetPin", ""+(target==null?"":target.getName()));
		node.addChild(n);
	}

	@Override
	public void loadFrom(XMLNode node) {
		XMLNode child;
		for(int i=0; i<node.getChildrenAmount(); i++){
			if(!((child=node.getChild(i)).getName().equalsIgnoreCase(name) && child.getProperty("type").equalsIgnoreCase("progOut")))
				continue;
			Node tmp = getNode().getGrid().getNodeByUniqueID(Integer.valueOf(child.getProperty("targetNode")));
			target = tmp==null?null:tmp.getPinProgInByName(child.getProperty("targetPin"));
			return;
		}
	}

}
