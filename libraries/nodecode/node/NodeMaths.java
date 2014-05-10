package libraries.nodecode.node;

import java.util.ArrayList;

import libraries.nodecode.XML.XMLNode;
import libraries.nodecode.core.Config;
import libraries.nodecode.core.Helper;
import libraries.nodecode.core.Node;
import libraries.nodecode.core.PinBase;
import libraries.nodecode.core.PinProgramIn;
import libraries.nodecode.core.PinProgramOut;
import libraries.nodecode.core.PinValueIn;
import libraries.nodecode.core.PinValueOut;
import libraries.nodecode.type.NumberData;
import libraries.nodecode.type.SelectionData;

public class NodeMaths extends Node {
	public static final String defaultName = "NodeMaths";

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
	public void initInputs(PinProgramIn progIn, ArrayList<PinValueIn<?>> valIn) {
		valIn.add(new PinValueIn<Number>(this, "var1", 0, new NumberData()));
		valIn.add(new PinValueIn<Number>(this, "var2", 1, new NumberData()));
	}

	@Override
	public void initConfigs(ArrayList<Config<?>> configs) {
		configs.add(new Config<Integer>(this, "operation", 0, new SelectionData(new String[]{"plus", "times"})));
	}

	@Override
	public void initOutputs(ArrayList<PinProgramOut> progOut, ArrayList<PinValueOut<?>> valOut) {
		progOut.add(new PinProgramOut(this, "progOut", 0));
		valOut.add(new PinValueOut<Number>(this, "result", 0, new NumberData()));
	}

	@Override
	public String getDefaultName() {
		return defaultName;
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
