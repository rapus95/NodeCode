package core;


public interface PinInput extends PinBase{
	
	
	public void setOriginUnchecked(PinOutput origin);
	public void removeOriginUnchecked(PinOutput origin);
	public PinOutput getOrigin();
	public boolean isValidFor(PinOutput out);
	
}
