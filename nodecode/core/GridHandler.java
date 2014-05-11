package nodecode.core;


public class GridHandler {
	private static Grid current = newGrid();

	public static Grid newGrid() {
		return new Grid();
	}

	public static void setCurrent(Grid grid) {
		current = grid;
	}

	public static Grid getCurrent() {
		return current;
	}
}
