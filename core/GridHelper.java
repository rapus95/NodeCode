package core;

public class GridHelper {

	public static boolean calculationFlow = true;

	public static boolean connectProg(Node source, int indexOut, Node target) {
		return connect(true, source, indexOut, target, 0);
	}

	public static boolean connectVal(Node source, int indexOut, Node target, int indexIn) {
		return connect(false, source, indexOut, target, indexIn);
	}

	public static boolean connect(boolean prog, Node source, int indexOut, Node target, int indexIn) {
		if(prog)
			return connect(source.getProgOut(indexOut), target.getProgIn(0));
		return connect(source.getValOut(indexOut), target.getValIn(indexIn));
	}

	public static boolean connect(PinOutput out, PinInput in) {
		if(out instanceof PinProgram && in instanceof PinProgram || out instanceof ValueHandler && in instanceof ValueHandler && ((ValueHandler<?>) out).getType() == ((ValueHandler<?>) in).getType()) {
			out.setTargetUnchecked(in);
			in.setOriginUnchecked(out);
			return true;
		}
		return false;
	}

	public static boolean splitProg(Node n, int indexOut) {
		return split(n.getProgOut(indexOut), null);
	}

	public static boolean splitVal(Node n, int indexIn) {
		return split(null, n.getValIn(indexIn));

	}

	public static boolean split(PinOutput out, PinInput in) {
		if(out == null && in == null)
			return true;
		if(out == null)
			out = in.getOrigin();
		if(in == null)
			in = out.getTarget();

		if(out instanceof PinProgram && in instanceof PinProgram || out instanceof ValueHandler && in instanceof ValueHandler && ((ValueHandler<?>) out).getType() == ((ValueHandler<?>) in).getType()) {
			out.removeTargetUnchecked(in);
			in.removeOriginUnchecked(out);
			return true;
		}
		return false;
	}

	public static void setValue(Node n, int index, Object o) {
		PinValueIn<?> pin = n.getValIn(index);
		if(pin.getType().isAssignableFrom(o.getClass())) {
			pin.setValueUnchecked(o);
			return;
		}
		throw new RuntimeException("WRONG TYPE!!");
	}

	public static void setNodeName(Node n, String name) {
		n.setName(name);
	}

	public static Node runProgram(Node start) {
		Node next = start;
		Node tmp;
		while((tmp = next.run()) != null) {
			next = tmp;
		};
		return next;
	}
}
