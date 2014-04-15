package type;

import core.ValueType;

public class NumberData implements ValueType<Number>{
	
	private double i;

	public Number getValue() {
		return i;
	}

	@Override
	public void setValue(Number dt) {
		this.i = dt.doubleValue();
	}

	@Override
	public Class<Number> getType() {
		return Number.class;
	}
}