package nodecode.type;

import nodecode.XML.XMLNode;
import nodecode.core.ValueType;

public class NumberData implements ValueType<Number>{
	
	private double value;

	public Number getValue() {
		return value;
	}

	@Override
	public void setValue(Number dt) {
		this.value = dt.doubleValue();
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
		value = 0;
	}

	@Override
	public COLOR getColor() {
		return COLOR.BLUE;
	}

	@Override
	public void saveTo(XMLNode node) {
		XMLNode own = new XMLNode(XMLTypeName);
		own.setString("type", "Number");
		own.setDouble("value", value);
		node.addChild(own);
	}

	@Override
	public void loadFrom(XMLNode node) {
		XMLNode[] own = node.getChildByName(XMLTypeName);
		value = own[0].getDouble("value");
	}
}