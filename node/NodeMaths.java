package node;

import java.util.ArrayList;

import type.NumberData;
import type.SelectionData;
import XML.XMLNode;
import core.Config;
import core.Node;
import core.PinBase;
import core.PinProgramIn;
import core.PinProgramOut;
import core.PinValueIn;
import core.PinValueOut;

public class NodeMaths extends Node {
	public static final String defaultName = "NodeMaths";

	@Override
	protected PinBase execute() {
		int mode = PinValueIn.<Number>getValue(getConfig(0)).intValue();
		double result = PinValueIn.getValue(getValIn(0));
		for(int i=1; i<getAmountOfValIn(); i++){
			if(mode==0){
				result+=PinValueIn.<Number>getValue(getValIn(i)).doubleValue();
			}else{
				result*=PinValueIn.<Number>getValue(getValIn(i)).doubleValue();
			}
		}
		getValOut(0).setValueUnchecked(result);
		return getProgOut(0).getTarget();
	}

	@Override
	public void initInputs(PinProgramIn progIn, ArrayList<PinValueIn<?>> valIn) {
		valIn.add(new PinValueIn<Number>(this, "var1", new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "var2", new NumberData()));
	}

	@Override
	public void initConfigs(ArrayList<Config<?>> configs) {
		configs.add(new Config<Integer>(this, "operation", new SelectionData(new String[]{"plus", "times"})));
	}

	@Override
	public void initOutputs(ArrayList<PinProgramOut> progOut, ArrayList<PinValueOut<?>> valOut) {
		progOut.add(new PinProgramOut(this, "progOut"));
		valOut.add(new PinValueOut<Number>(this, "result", new NumberData()));
	}

	@Override
	public String getDefaultName() {
		return defaultName;
	}

	@Override
	protected void saveTo(XMLNode node) {}

	@Override
	protected void loadFrom(XMLNode node) {}
	
	

}
