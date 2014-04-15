package core;

public class Grid {
	
	public static boolean connect(Node source, int indexOut, Node target, int indexIn){
		return connect(source.getOutputPin(indexOut), target.getInputPin(indexIn));
	}
	
	public static boolean connect(PinOutput out, PinInput in){
		if(out instanceof PinProgram && in instanceof PinProgram
		|| out instanceof PinValue && in instanceof PinValue && ((PinValue<?>) out).getType()==((PinValue<?>) in).getType()
		){
			out.setTargetUnchecked(in);
			in.setOriginUnchecked(out);
			return true;
		}
		return false;
	}
	
	public static boolean split(PinOutput out, PinInput in){
		if(out instanceof PinProgram && in instanceof PinProgram
		|| out instanceof PinValue && in instanceof PinValue && ((PinValue<?>) out).getType()==((PinValue<?>) in).getType()
		){
			out.removeTargetUnchecked(in);
			in.removeOriginUnchecked(out);
			return true;
		}
		return false;
	}
	
	public void runProgram(Node start){
		Node next=start;
		while((next=next.run())!=null);
	}
}
