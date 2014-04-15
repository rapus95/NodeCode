package core;

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

}
