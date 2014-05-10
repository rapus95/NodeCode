package type;

import XML.XMLNode;
import core.ValueType;

public class SelectionData implements ValueType<Integer> {
	
	private int selected;
	private String[] options;
	
	public SelectionData(String... options){
		this.options = options;
	}
	
	public SelectionData(XMLNode node){
		options = node.getProperty("values").split(",");
		selected = Integer.valueOf(node.getProperty("selected"));
	}

	@Override
	public Integer getValue() {
		return selected;
	}

	@Override
	public void setValueUnchecked(Object o) {
		if(canConvert(o.getClass()))
			setValue(convert(o));
	}

	@Override
	public Integer convert(Object o) {
		if(o instanceof String){
			return getIndexForString((String)o);
		}
		return ((Number)o).intValue();
	}

	@Override
	public boolean canConvert(Class<?> other) {
		return Number.class.isAssignableFrom(other) || other==String.class;
	}

	@Override
	public void setValue(Integer dt) {
		if(dt<0 || dt>options.length-1)
			return;
		selected = dt;
	}

	@Override
	public Class<Integer> getType() {
		return Integer.class;
	}

	@Override
	public boolean canHaveDirectInput() {
		return true;
	}

	@Override
	public void init() {
		selected = 0;
	}
	
	public String getSelectedString(){
		return options[getValue()];
	}
	
	public String getOptionString(int index){
		return options[index];
	}
	
	public int getIndexForString(String text){
		for(int i=0; i<options.length; i++){
			if(options[i].equalsIgnoreCase(text))
				return i;
		}
		return -1;
	}

	@Override
	public COLOR getColor() {
		return COLOR.BLUE;
	}

	@Override
	public void saveTo(XMLNode node) {
		XMLNode own = new XMLNode(XMLTypeName);
		own.setProperty("type", "Selection");
		own.setProperty("values", optionText());
		own.setProperty("selected", ""+selected);
		node.addChild(own);
	}

	@Override
	public void loadFrom(XMLNode node) {
		XMLNode[] own = node.getChildByName(XMLTypeName);
		selected = Integer.valueOf(own[0].getProperty("selected"));
	}
	
	private String optionText(){
		if(options==null || options.length==0)
			return "";
		String text = options[0];
		for(int i=1; i<options.length; i++){
			text += ","+options[i];
		}
		return text;
	}

}
