package nodecode.XML;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;


public class XMLNode {

	private String name;
	
	private LinkedHashMap<String, Object> properties = new LinkedHashMap<String, Object>();
	
	private List<XMLNode> children = new ArrayList<XMLNode>();
	
	private String text = "";
	
	public XMLNode(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setString(String key, String value){
		this.properties.put(key, value);
	}
	
	public void setBoolean(String key, boolean value){
		this.properties.put(key, value);
	}
	
	public void setByte(String key, byte value){
		this.properties.put(key, value);
	}
	
	public void setShort(String key, short value){
		this.properties.put(key, value);
	}
	
	public void setInt(String key, int value){
		this.properties.put(key, value);
	}
	
	public void setFloat(String key, float value){
		this.properties.put(key, value);
	}
	
	public void setDouble(String key, double value){
		this.properties.put(key, value);
	}
	
	public String getString(String key){
		return this.properties.get(key).toString();
	}
	
	public boolean getBoolean(String key){
		Object o = this.properties.get(key);
		if(o instanceof Boolean)
			return (Boolean)o;
		else
			return Boolean.valueOf((String)o);
	}
	
	public byte getByte(String key){
		Object o = this.properties.get(key);
		if(o instanceof Number)
			return ((Number)o).byteValue();
		else
			return Byte.valueOf((String)o);
	}
	
	public short getShort(String key){
		Object o = this.properties.get(key);
		if(o instanceof Number)
			return ((Number)o).shortValue();
		else
			return Short.valueOf((String)o);
	}
	
	public int getInt(String key){
		Object o = this.properties.get(key);
		if(o instanceof Number)
			return ((Number)o).intValue();
		else
			return Integer.valueOf((String)o);
	}
	
	public float getFloat(String key){
		Object o = this.properties.get(key);
		if(o instanceof Number)
			return ((Number)o).floatValue();
		else
			return Float.valueOf((String)o);
	}
	
	public double getDouble(String key){
		Object o = this.properties.get(key);
		if(o instanceof Number)
			return ((Number)o).doubleValue();
		else
			return Double.valueOf((String)o);
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return this.text;
	}
	
	public void addChild(XMLNode child){
		if(!this.children.contains(child))
			this.children.add(child);
	}
	
	public int getChildrenAmount(){
		return this.children.size();
	}
	
	public XMLNode getChild(int i){
		return this.children.get(i);
	}
	
	public XMLNode[] getChildByName(String searchName){
		ArrayList<XMLNode> list = new ArrayList<XMLNode>();
		for(XMLNode child:children){
			if(child.getName().equalsIgnoreCase(searchName))
				list.add(child);
		}
		return list.toArray(new XMLNode[0]);
	}
	
	public String save(String ls){
		String out = ls + "<"+this.name;
		for(Entry<String, Object> e:this.properties.entrySet()){
			out += " "+e.getKey()+" = \""+e.getValue().toString()+"\"";
		}
		if(this.children.isEmpty() && this.text.trim().isEmpty()){
			return out + "/>";
		}
		out += ">\n";
		String ls2 = ls+"\t";
		for(XMLNode child:this.children){
			out += child.save(ls2)+"\n";
		}
		if(!(this.text==null || this.text.trim().isEmpty())){
			String[] s = this.text.split("\n");
			for(String ss:s){
				out += ls2+ss+"\n";
			}
		}
		return out + ls+"</"+this.name+">";
	}

	public String save() {
		return save("");
	}

	@Override
	public String toString() {
		return save();
	}
	
	
}
