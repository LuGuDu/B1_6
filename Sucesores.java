package practica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Sucesores {
	private ArrayList<Sucesor> sucesores;
	private Labyrinth lab = null;

	public Sucesores(Sucesor sucesor, Labyrinth lab) {
		conseguirSucesorN(sucesor, lab);
	}

	public void conseguirSucesorN(Sucesor sucesor, Labyrinth lab) {
		Map<String, Cell> cells = lab.getCells();
		Cell cellCheck = null;
		int row = sucesor.getRow();
		int col = sucesor.getCol();
		int rowN = row - 1;
		int colN = col;
		int rowE = row;
		int colE = col + 1;
		int rowS = row + 1;
		int colS = col;
		int rowO = row;
		int colO = col - 1;

		cellCheck = cells.get("(" + row + ", " + col + ")");
		boolean[] neighbours = Arrays.copyOf(cellCheck.getNeighbors(), 4);

		if (neighbours[0]) {
			sucesores.add(new Sucesor("N", "(" + rowN + ", " + colN + ")", 1));
		} else if (neighbours[1]) {
			sucesores.add(new Sucesor("E", "(" + rowE + ", " + colE + ")", 1));
		} else if (neighbours[2]) {
			sucesores.add(new Sucesor("S", "(" + rowS + ", " + colS + ")", 1));
		} else if (neighbours[3]) {
			sucesores.add(new Sucesor("O", "(" + rowO + ", " + colO + ")", 1));
		}

	}
}
