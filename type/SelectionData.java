package type;

import core.ValueType;

public class SelectionData implements ValueType<Integer> {
	
	private int selected;
	private String[] options;
	
	public SelectionData(String[] options){
		this.options = options;
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
		return this.getType().cast(o);
	}

	@Override
	public void setValue(Integer dt) {
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
	public boolean canConvert(Class<?> other) {
		return this.getType().isAssignableFrom(other);
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

}