package practica;

import java.util.Arrays;

public class Cell {
	private int value;
	private boolean[] neighbors;
	
	public Cell () {
		
	}

	@Override
	public String toString() {
		return "Cell [value=" + value + ", neighbors=" + Arrays.toString(neighbors) + "]";
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean[] getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(boolean[] neighbors) {
		this.neighbors = neighbors;
	}
}
