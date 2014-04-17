package core;

import java.util.ArrayList;

import XML.XMLNode;

public abstract class Node {
	protected PinProgramIn progIn = new PinProgramIn(this, "ProgIn");
	protected ArrayList<PinProgramOut> progOut = new ArrayList<PinProgramOut>();
	protected ArrayList<Config<?>> configs = new ArrayList<Config<?>>();
	protected ArrayList<PinValueIn<?>> valIn = new ArrayList<PinValueIn<?>>();
	protected ArrayList<PinValueOut<?>> valOut = new ArrayList<PinValueOut<?>>();
	protected String name;
	public boolean calcOnRequest=false;
	protected boolean isCalculated=false;
	protected float x, y;
	protected Grid grid;
	protected int uniqueID;
	
	
	protected Node(){
		this.x = 0;
		this.y = 0;
		grid = Grid.getCurrent();
		uniqueID = grid.registerNode(this);
		initInputs();
		initConfigs();
		initOutputs();
		for(PinValueIn<?> pin:valIn){pin.init();}
		for(Config<?> conf:configs){conf.init();}
		for(PinValueOut<?> pin:valOut){pin.init();}
		name = getDefaultName();
	}
	
	public int getUniqueID(){
		return uniqueID;
	}
	
	public Node run(){
		prepare();
		isCalculated=true;
		return execute();
	}
	
	public void reset(){
		isCalculated=false;
		for(NodeComponent pin:valOut){pin.reset();}
	}
	
	public void save(XMLNode node){
		XMLNode own = new XMLNode(name);
		own.setProperty("id", ""+uniqueID);
		own.setProperty("type", getDefaultName());
		own.setProperty("calcOnRequest", ""+calcOnRequest);
		saveTo(own);
		for(NodeComponent pin:valIn){pin.saveTo(own);}
		for(NodeComponent conf:configs){conf.saveTo(own);}
		for(NodeComponent pin:valOut){pin.saveTo(own);}
		node.addChild(own);
	}
	
	public void load(XMLNode own){
		name = own.getName();
		uniqueID=Integer.valueOf(own.getProperty("id"));
		calcOnRequest=Boolean.valueOf(own.getProperty("calcOnRequest"));
		loadFrom(own);
	}
	
	public void load2(XMLNode own){
		for(NodeComponent pin:valIn){pin.loadFrom(own);}
		for(NodeComponent conf:configs){conf.loadFrom(own);}
		for(NodeComponent pin:valOut){pin.loadFrom(own);}
	}
	
	protected abstract Node execute();
	public abstract void initInputs();
	public abstract void initConfigs();
	public abstract void initOutputs();
	public abstract String getDefaultName();
	protected abstract void saveTo(XMLNode node);
	protected abstract void loadFrom(XMLNode node);
	
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
			if((GridHelper.calculationFlow || origin.canBeCalcedOnRequest()) && !origin.isCalculated()){
				origin.run();
			}
		}
	}
	
	public void toggleCalcOnRequest(){
		calcOnRequest^=true;
	}

	public Grid getGrid() {
		return grid;
	}

	public PinProgramIn getPinProgInByName(String property) {
		return progIn;
	}

	public PinProgramOut getPinProgOutByName(String property) {
		for(PinProgramOut out:progOut){
			if(out.getName().equalsIgnoreCase(property))
				return out;
		}
		return null;
	}

	public PinValueIn<?> getPinValInByName(String property) {
		for(PinValueIn<?> in:valIn){
			if(in.getName().equalsIgnoreCase(property))
				return in;
		}
		return null;
	}

	public PinValueOut<?> getPinValOutByName(String property) {
		for(PinValueOut<?> out:valOut){
			if(out.getName().equalsIgnoreCase(property))
				return out;
		}
		return null;
	}
}
