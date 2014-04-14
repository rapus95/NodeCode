package core;

public interface PinOutput extends PinBase {
	public void setTargetUnchecked(PinInput target);
	public PinInput getTarget();
	public void initialize();
}
