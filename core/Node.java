package core;

import java.util.ArrayList;

public abstract class Node {
	protected PinProgramIn progIn = new PinProgramIn(this, "ProgIn");
	protected ArrayList<PinProgramOut> progOut = new ArrayList<PinProgramOut>();
	protected ArrayList<ValueHandler<?>> configs = new ArrayList<ValueHandler<?>>();
	protected ArrayList<PinValueIn<?>> valIn = new ArrayList<PinValueIn<?>>();
	protected ArrayList<PinValueOut<?>> valOut = new ArrayList<PinValueOut<?>>();
	protected String name;
	public boolean calcOnRequest=false;
	public boolean isCalculated=false;
	
	protected Node(){
		initInputs();
		initConfigs();
		initOutputs();
		for(PinValueIn<?> pin:valIn){pin.init();}
		for(ValueHandler<?> conf:configs){conf.init();}
		for(PinValueOut<?> pin:valOut){pin.init();}
		name = getDefaultName();
	}
	
	public Node run(){
		prepare();
		isCalculated=true;
		return execute();
	}
	
	protected abstract Node execute();
	public abstract void initInputs();
	public abstract void initConfigs();
	public abstract void initOutputs();
	public abstract String getDefaultName();
	
	public PinProgramIn getProgIn(){
		return progIn;
	}
	
	public PinProgramOut getProgOut(int index){
		return progOut.get(index);
	}
	
	public PinValueIn<?> getValIn(int index){
		return valIn.get(index);
	}
	
	public ValueHandler<?> getConfig(int index){
		return configs.get(index);
	}
	
	public PinValueOut<?> getValOut(int index){
		return valOut.get(index);
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isCalculated(){
		return isCalculated;
	}
	
	public boolean canBeCalcedOnRequest(){
		return calcOnRequest;
	}
	
	public void prepare(){
		Node origin;
		PinOutput out;
		for(PinValueIn<?> pin:valIn){
			if((out=pin.getOrigin())==null)
				continue;
			origin = out.getNode();
			if((Grid.calculationMode || origin.canBeCalcedOnRequest()) && !origin.isCalculated()){
				origin.run();
			}
		}
	}
}
