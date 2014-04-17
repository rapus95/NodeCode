package core;

import XML.XMLNode;

public interface NodeComponent {

	public abstract Node getNode();

	public abstract String getName();
	
	public abstract void init();
	public abstract void reset();
	
	public void saveTo(XMLNode node);
	public void loadFrom(XMLNode node);

}