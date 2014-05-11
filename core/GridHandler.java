package libraries.nodecode.core;

import java.util.concurrent.Callable;

import libraries.nodecode.node.NodeBranch;
import libraries.nodecode.node.NodeCountLoop;
import libraries.nodecode.node.NodeItemCompareOutCount;
import libraries.nodecode.node.NodeItemStackSeperate;
import libraries.nodecode.node.NodeMaths;

public class GridHandler {

	static{
		init();
	}
	
	private static boolean loaded = false;
	private static Grid current = new Grid();

	public static Grid newGrid() {
		return new Grid();
	}

	public static void setCurrent(Grid grid) {
		current = grid;
	}

	public static Grid getCurrent() {
		return current;
	}
	
	public static void registerNode(String name, Callable<Node> n){
		NodeFactory.registerNode(name, n);
	}

	private static void init() {
		if(loaded) return;
		registerNode(new NodeBranch().getDefaultName(), new Callable<Node>() {

			@Override
			public Node call() throws Exception {
				return new NodeBranch();
			}
		});
		registerNode(new NodeCountLoop().getDefaultName(), new Callable<Node>() {

			@Override
			public Node call() throws Exception {
				return new NodeCountLoop();
			}
		});
		registerNode(new NodeItemCompareOutCount().getDefaultName(), new Callable<Node>() {

			@Override
			public Node call() throws Exception {
				return new NodeItemCompareOutCount();
			}
		});
		registerNode(new NodeItemStackSeperate().getDefaultName(), new Callable<Node>() {

			@Override
			public Node call() throws Exception {
				return new NodeItemStackSeperate();
			}
		});
		registerNode(new NodeMaths().getDefaultName(), new Callable<Node>() {

			@Override
			public Node call() throws Exception {
				return new NodeMaths();
			}
		});
		loaded = true;
	}
}
