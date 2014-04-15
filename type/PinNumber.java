package type;

import core.Node;
import core.PinBaseImp;
import core.PinInput;
import core.PinOutput;
import core.PinValue;
import core.PinValueIn;

public class PinNumber extends PinBaseImp implements PinOutput, PinValue<Number> {

	public PinNumber(Node parent, String name) {
		super(parent, name);
	}

	private PinValueIn<PinNumber> target;
	private int i=0;

	@SuppressWarnings("unchecked")
	@Override
	public void setTargetUnchecked(PinInput target) {
		if(target instanceof PinValue && this.getType()==((PinValue<?>)target).getType()){
			setTarget((PinValueIn<PinNumber>) target);
		}
	}
	
	public void setTarget(PinValueIn<PinNumber> target) {
		this.target = target;
	}

	@Override
	public void removeTargetUnchecked(PinInput target) {
		if(this.target==target)
			target=null;
	}

	@Override
	public PinValueIn<PinNumber> getTarget() {
		return target;
	}

	@Override
	public void initialize() {}

	@Override
	public Number getValue() {
		return i;
	}

	@Override
	public Class<Number> getType() {
		return Number.class;
	}
}
