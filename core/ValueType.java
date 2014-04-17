package core;

import XML.XMLNode;

public interface ValueType<DataType> {
	public DataType getValue();
	public void setValueUnchecked(Object o);
	public DataType convert(Object o);
	public void setValue(DataType dt);
	public Class<DataType> getType();
	public void init();
	public boolean canHaveDirectInput();
	public boolean canConvert(Class<?> other);
	public COLOR getColor();
	public abstract void saveTo(XMLNode node);
	public abstract void loadFrom(XMLNode node);
	
	public static enum COLOR{
		WHITE(255, 255, 255),
		RED(255, 0, 0),
		GREEN(0, 255, 0),
		BLUE(0, 0, 255),
		BLACK(0, 0, 0),
		;
		
		public final int rgb;
		
		private COLOR(int r, int g, int b){
			rgb = (r&0xFF)<<16 | (g&0xFF)<<8 | (b&0xFF);
		}
	}
}
