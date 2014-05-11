package nodecode.core;

import java.util.ArrayList;

import nodecode.XML.XMLNode;

public abstract class Node{
	private ArrayList<PinProgramIn> progIn = new ArrayList<PinProgramIn>();
	private ArrayList<PinProgramOut> progOut = new ArrayList<PinProgramOut>();
	private ArrayList<Config<?>> configs = new ArrayList<Config<?>>();
	private ArrayList<PinValueIn<?>> valIn = new ArrayList<PinValueIn<?>>();
	private ArrayList<PinValueOut<?>> valOut = new ArrayList<PinValueOut<?>>();
	protected String name;
	public boolean calcOnRequest=false;
	protected boolean isCalculated=false;
	protected float x, y;
	protected Grid grid;
	protected int uniqueID;
	
	public enum IPOType{
		INPUT, PROCESS, OUTPUT, STORAGE;
	}
	
	protected Node(){
		this(GridHandler.getCurrent(), true);
	}
	
	protected Node(boolean register){
		this(GridHandler.getCurrent(), register);
	}
	
	protected Node(Grid grid){
		this(grid, true);
	}
	
	protected Node(Grid grid, boolean register){
		progIn.add(new PinProgramIn(this, "ProgIn", 0));
		progOut.add(new PinProgramOut(this, "ProgOut", 0));
		this.x = 0;
		this.y = 0;
		if(register)
			uniqueID = grid.registerNode(this);
		else
			uniqueID = -1;
		initInputs(progIn, valIn);
		initConfigs(configs);
		initOutputs(progOut, valOut);
		for(PinValueIn<?> pin:valIn){pin.init();}
		for(Config<?> conf:configs){conf.init();}
		for(PinValueOut<?> pin:valOut){pin.init();}
		name = getDefaultName();
	}
	
	public int getUniqueID(){
		return uniqueID;
	}
	
	public final Node run(){
		isCalculated=true;
		prepare();
		PinBase pb = execute();
		return pb==null?null:pb.getNode();
	}
	
	public final void prepare(){
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
	
	public void reset(){
		isCalculated=false;
		for(NodeComponent pin:valOut){pin.reset();}
	}
	
	public void save(XMLNode node){
		XMLNode own = new XMLNode(getIPOType().name());
		own.setInt("id", uniqueID);
		own.setBoolean("calcOnRequest", calcOnRequest);
		own.setString("type", getDefaultName());
		own.setString("name", name);
		saveTo(own);
		for(NodeComponent conf:configs){conf.saveTo(own);}
		for(NodeComponent pin:valIn){pin.saveTo(own);}
		for(NodeComponent pin:progOut){pin.saveTo(own);}
		node.addChild(own);
	}
	
	public void load(XMLNode own){
		name = own.getString("name");
		uniqueID=Integer.valueOf(own.getInt("id"));
		calcOnRequest=Boolean.valueOf(own.getBoolean("calcOnRequest"));
		loadFrom(own);
	}
	
	public void load2(XMLNode own){
		for(NodeComponent conf:configs){conf.loadFrom(own);}
		for(NodeComponent pin:valIn){pin.loadFrom(own);}
		for(NodeComponent pin:progOut){pin.loadFrom(own);}
	}
	
	protected abstract PinBase execute();
	public abstract void initInputs(ArrayList<PinProgramIn> progIn, ArrayList<PinValueIn<?>> valIn);
	public abstract void initConfigs(ArrayList<Config<?>> configs);
	public abstract void initOutputs(ArrayList<PinProgramOut> progOut, ArrayList<PinValueOut<?>> valOut);
	public abstract IPOType getIPOType();
	protected abstract void saveTo(XMLNode node);
	protected abstract void loadFrom(XMLNode node);
	public abstract INodeFactoryDescriptor getNodeFactoryDescriptor();
	
	public String getDefaultName(){
		return getNodeFactoryDescriptor().getDefaultName();
	}
	
	public PinProgramIn getProgIn(int index){
		return progIn.get(index);
	}
	
	public int getAmountOfProgIn(){
		return progIn.size();
	}
	
	public PinProgramOut getProgOut(int index){
		return progOut.get(index);
	}
	
	public int getAmountOfProgOut(){
		return progOut.size();
	}
	
	public PinValueIn<?> getValIn(int index){
		return valIn.get(index);
	}
	
	public int getAmountOfValIn(){
		return valIn.size();
	}
	
	public ValueHandler<?> getConfig(int index){
		return configs.get(index);
	}
	
	public int getAmountOfConfigs(){
		return configs.size();
	}
	
	public PinValueOut<?> getValOut(int index){
		return valOut.get(index);
	}
	
	public int getAmountOfValOut(){
		return valOut.size();
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
	
	public void toggleCalcOnRequest(){
		calcOnRequest^=true;
	}

	public Grid getGrid() {
		return grid;
	}

	public PinProgramIn getPinProgInByName(String property) {
		for(PinProgramIn in:progIn){
			if(in.getName().equalsIgnoreCase(property))
				return in;
		}
		return null;
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
	
	public PinValueOut<?> getPinValOutByID(int id) {
		for(PinValueOut<?> out:valOut){
			if(out.getID()==id)
				return out;
		}
		return null;
	}
}
