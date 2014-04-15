package core;



public class PinProgramIn extends PinBaseImp implements PinInput, PinProgram {

	public PinProgramIn(Node parent, String name) {
		super(parent, name);
		color = COLOR.RED;
	}

	@Override
	public void setOriginUnchecked(PinOutput origin) {}

	@Override
	public void removeOriginUnchecked(PinOutput origin) {}

	public PinOutput getOrigin() {
		return null;
	}

}
