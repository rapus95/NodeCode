package core;

public interface PinOutput extends PinBase {
	public void setTargetUnchecked(PinInput target);
	public void removeTargetUnchecked(PinInput target);
	public PinInput getTarget();
	public boolean isValidFor(PinInput in);
}
