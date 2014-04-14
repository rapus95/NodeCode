package core;


public class PinProgramIn extends PinBaseImp implements PinInput, PinProgram {

	private PinProgramOut origin=null;

	@Override
	public void setOriginUnchecked(PinOutput origin) {
		if(origin instanceof PinProgram){
			setOrigin((PinProgramOut) origin);
		}
	}
	
	public void setOrigin(PinProgramOut origin) {
		this.origin = origin;
	}

	public PinProgramOut getOrigin() {
		return origin;
	}

}
