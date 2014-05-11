package nodecode.core;

import nodecode.XML.XMLNode;
import nodecode.core.ValueType.COLOR;




public class PinProgramOut extends PinBaseImp implements PinOutput, PinProgram {

	public static final String XMLTypeName = "progOut";

	public PinProgramOut(Node parent, String name, int id) {
		super(parent, name, id);
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
		XMLNode n = new XMLNode(XMLTypeName);
		n.setString("name", name);
		n.setInt("targetNode", (target==null?-1:target.getNode().getUniqueID()));
		n.setString("targetPin", ""+(target==null?"":target.getName()));
		node.addChild(n);
	}

	@Override
	public void loadFrom(XMLNode node) {
		XMLNode[] children = node.getChildByName(XMLTypeName);
		for(XMLNode child:children){
			if(child.getString("name").equalsIgnoreCase(name))
				continue;
			Node tmp = getNode().getGrid().getNodeByUniqueID(Integer.valueOf(child.getInt("targetNode")));
			if(tmp==null)
				target = null;
			else{
				target = tmp.getPinProgInByName(child.getString("targetPin"));
			}
			return;
		}
	}

}
