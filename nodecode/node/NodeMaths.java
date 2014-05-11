package nodecode.node;

import java.util.ArrayList;

import nodecode.XML.XMLNode;
import nodecode.core.Config;
import nodecode.core.Helper;
import nodecode.core.Node;
import nodecode.core.PinBase;
import nodecode.core.PinProgramIn;
import nodecode.core.PinProgramOut;
import nodecode.core.PinValueIn;
import nodecode.core.PinValueOut;
import nodecode.type.NumberData;
import nodecode.type.SelectionData;

public class NodeMaths extends Node {

	@Override
	protected PinBase execute() {
		int mode = Helper.<Number>getValue(getConfig(0)).intValue();
		double result = Helper.getValue(getValIn(0));
		for(int i=1; i<getAmountOfValIn(); i++){
			if(mode==0){
				result+=Helper.<Number>getValue(getValIn(i)).doubleValue();
			}else{
				result*=Helper.<Number>getValue(getValIn(i)).doubleValue();
			}
		}
		getValOut(0).setValueUnchecked(result);
		return getProgOut(0).getTarget();
	}

	@Override
	public void initInputs(ArrayList<PinProgramIn> progIn, ArrayList<PinValueIn<?>> valIn) {
		valIn.add(new PinValueIn<Number>(this, "var1", 0, new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "var2", 1, new NumberData()));
	}

	@Override
	public void initConfigs(ArrayList<Config<?>> configs) {
		configs.add(new Config<Integer>(this, "operation", 0, new SelectionData(new String[]{"plus", "times"})));
	}

	@Override
	public void initOutputs(ArrayList<PinProgramOut> progOut, ArrayList<PinValueOut<?>> valOut) {
		valOut.add(new PinValueOut<Number>(this, "result", 0, new NumberData()));
	}

	@Override
	public String getDefaultName() {
		return "NodeMaths";
	}

	@Override
	protected void saveTo(XMLNode node) {}

	@Override
	protected void loadFrom(XMLNode node) {}

	@Override
	public IPOType getIPOType() {
		return IPOType.PROCESS;
	}
	
	

}
