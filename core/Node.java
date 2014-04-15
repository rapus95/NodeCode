package core;

public abstract class Node {
	protected PinInput in[];
	protected PinOutput out[];
	
	protected Node(){
		initInputs();
		initOutputs();
	}
	
	public abstract Node run();
	public abstract void initInputs();
	public abstract void initOutputs();
	public abstract String getName();
	
	public PinInput getInputPin(int index){
		return in[index];
	}
	
	public PinOutput getOutputPin(int index){
		return out[index];
	}
}
