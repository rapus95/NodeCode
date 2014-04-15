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

	@Override
	public boolean canHaveDirectInput() {
		return true;
	}

	@Override
	public void setValueUnchecked(Object o) {
		if(canConvert(o.getClass()))
			setValue(convert(o));
	}

	@Override
	public Number convert(Object o) {
		return this.getType().cast(o);
	}

	@Override
	public boolean canConvert(Class<?> other) {
		return this.getType().isAssignableFrom(other);
	}

	@Override
	public void init() {
		i = 0;
	}

	@Override
	public COLOR getColor() {
		return COLOR.BLUE;
	}
}