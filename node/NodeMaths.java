package node;

import type.NumberData;
import type.SelectionData;
import XML.XMLNode;
import core.Config;
import core.Node;
import core.PinBase;
import core.PinProgramOut;
import core.PinValueIn;
import core.PinValueOut;

public class NodeMaths extends Node {
	public static final String defaultName = "NodeMaths";

	@Override
	protected PinBase execute() {
		int mode = PinValueIn.<Number>getValue(configs.get(0)).intValue();
		double result = PinValueIn.getValue(valIn.get(0));
		for(int i=1; i<valIn.size(); i++){
			if(mode==0){
				result+=PinValueIn.<Number>getValue(valIn.get(i)).doubleValue();
			}else{
				result*=PinValueIn.<Number>getValue(valIn.get(i)).doubleValue();
			}
		}
		valOut.get(0).setValueUnchecked(result);
		return progOut.get(0).getTarget();
	}

	@Override
	public void initInputs() {
		valIn.add(new PinValueIn<Number>(this, "var1", new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "var2", new NumberData()));
	}

	@Override
	public void initConfigs() {
		configs.add(new Config<Integer>(this, "operation", new SelectionData(new String[]{"plus", "times"})));
	}

	@Override
	public void initOutputs() {
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
