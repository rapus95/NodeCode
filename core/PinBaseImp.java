package core;

public abstract class PinBaseImp implements PinBase {
	protected final Node parent;
	protected String name;
	protected COLOR color = COLOR.WHITE;
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
	
	protected PinBaseImp(Node parent, String name){
		this.parent = parent;
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see core.PinBase#getNode()
	 */
	@Override
	public Node getNode(){
		return parent;
	}
	
	@Override
	public String getName(){
		return name;
	}
	
	@Override
	public COLOR getColor(){
		return color;
	}
}
