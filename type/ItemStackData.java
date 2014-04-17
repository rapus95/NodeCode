package type;

import XML.XMLNode;
import core.ValueType;

public class ItemStackData implements ValueType<ItemStack> {

	ItemStack is;
	
	
	@Override
	public ItemStack getValue() {
		return is;
	}

	@Override
	public void setValue(ItemStack dt) {
		is=dt;
	}

	@Override
	public Class<ItemStack> getType() {
		return ItemStack.class;
	}

	@Override
	public boolean canHaveDirectInput() {
		return true;
	}

	@Override
	public void setValueUnchecked(Object o) {
		if(!canConvert(o.getClass()))
			return;
		setValue(convert(o));
	}

	@Override
	public ItemStack convert(Object o) {
		return this.getType().cast(o);
	}

	@Override
	public boolean canConvert(Class<?> other) {
		return this.getType().isAssignableFrom(other);
	}

	@Override
	public void init() {
		 is = new ItemStack(0, 0, 0);
	}

	@Override
	public COLOR getColor() {
		return COLOR.BLACK;
	}

	@Override
	public void saveTo(XMLNode node) {
		XMLNode own = new XMLNode("data");
		own.setProperty("id", ""+is.id);
		own.setProperty("meta", ""+is.meta);
		own.setProperty("stackSize", ""+is.stacksize);
		node.addChild(own);
	}

	@Override
	public void loadFrom(XMLNode node) {
		XMLNode own = node.getChildByName("data");
		is = new ItemStack(Integer.valueOf(own.getProperty("id")),
				Integer.valueOf(own.getProperty("meta")),
				Integer.valueOf(own.getProperty("stackSize")));
	}

}
