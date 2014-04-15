package core;

public abstract class Node {
	protected PinInput in[] = new PinInput[0];
	protected PinOutput out[] = new PinOutput[0];
	
	protected Node(){
		initInputs();
		initOutputs();
		for(PinOutput pin:out){pin.initialize();}
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
