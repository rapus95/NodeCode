package core;


public class PinProgramOut extends PinBaseImp implements PinOutput, PinProgram {

	private PinProgramIn target = null;

	@Override
	public void setTargetUnchecked(PinInput target) {
		if(target instanceof PinProgram){
			setTarget((PinProgramIn) target);
		}
	}
	
	public void setTarget(PinProgramIn target) {
		this.target = target;
	}

	@Override
	public PinProgramIn getTarget() {
		return target;
	}

	@Override
	public void initialize() {}

}
