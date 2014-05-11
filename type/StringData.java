package libraries.nodecode.type;

import libraries.nodecode.XML.XMLNode;
import libraries.nodecode.core.ValueType;
import libraries.nodecode.core.ValueType.COLOR;

public class StringData implements ValueType<String> {

	String text;
	
	@Override
	public String getValue() {
		return text;
	}

	@Override
	public void setValueUnchecked(Object o) {
		if(canConvert(o.getClass()))
			setValue(convert(o));
	}

	@Override
	public String convert(Object o) {
		return this.getType().cast(o);
	}

	@Override
	public void setValue(String dt) {
		text = dt;
	}

	@Override
	public Class<String> getType() {
		return String.class;
	}

	@Override
	public void init() {
		text = "";
	}

	@Override
	public boolean canHaveDirectInput() {
		return true;
	}

	@Override
	public boolean canConvert(Class<?> other) {
		return this.getType().isAssignableFrom(other);
	}

	@Override
	public COLOR getColor() {
		return COLOR.GREEN;
	}

	@Override
	public void saveTo(XMLNode node) {
		XMLNode own = new XMLNode(XMLTypeName);
		own.setString("type", "String");
		own.setString("value", text);
		node.addChild(own);
	}

	@Override
	public void loadFrom(XMLNode node) {
		XMLNode[] own = node.getChildByName(XMLTypeName);
		text = own[0].getString("value");
	}

}
