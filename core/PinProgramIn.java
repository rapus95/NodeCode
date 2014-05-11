package core;

import XML.XMLNode;
import core.ValueType.COLOR;



public class PinProgramIn extends PinBaseImp implements PinInput, PinProgram {

	public PinProgramIn(Node parent, String name, int id) {
		super(parent, name, id);
	}

	@Override
	public void setOriginUnchecked(PinOutput origin) {}

	@Override
	public void removeOriginUnchecked(PinOutput origin) {}

	public PinOutput getOrigin() {
		return null;
	}

	@Override
	public boolean isValidFor(PinOutput out) {
		return out instanceof PinProgramOut;
	}

	@Override
	public COLOR getColor() {
		return COLOR.RED;
	}

	@Override
	public void saveTo(XMLNode node) {}

	@Override
	public void loadFrom(XMLNode node) {}

	@Override
	public void init() {}

	@Override
	public void reset() {}

}
