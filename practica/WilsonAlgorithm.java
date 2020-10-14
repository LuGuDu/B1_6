package practica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

public class WilsonAlgorithm {

	/**
	 * Algoritmo de Wilson para generar laberintos.
	 * A partir de un objeto tipo laberinto en el que ninguna celda está conectada
	 * con ninguna otra genera caminos hasta tener un laberinto sin bucles en el que 
	 * se puede llegar a cualquier celda desde cualquier otra.
	 * @param lab
	 */
	public static void wilson(Labyrinth lab) {
		Stack<String> stackKeys = new Stack<String>();
		Stack<Cell> stackCells = new Stack<Cell>(); 
		Map<String, Cell> cells = lab.getCells();
		LinkedHashMap<String, Cell> cellsVisited = new LinkedHashMap<String, Cell>();
		ArrayList<String> noVisitedKeys = new ArrayList<String>();
		rellenarnoVisitedKeys(noVisitedKeys, lab);
		
		int position2 = (int) (Math.random() * (noVisitedKeys.size()));
		String key2 = noVisitedKeys.get(position2);

		String aux = "";
		Cell auxC = null;
		
		lab.getCells().get(key2).setVisited(true);
		
		while (cellsVisited.size() < cells.size()) {
			int position = (int) (Math.random() * (noVisitedKeys.size()));
			String key1 = noVisitedKeys.get(position);
			int x = key1.indexOf(",");

			int ranRow = Integer.parseInt(key1.substring(1, x));
			int ranCol = Integer.parseInt(key1.substring(x + 2, key1.length() - 1));

			oneRoad(ranRow, ranCol, lab, stackKeys, stackCells);
			while (!stackKeys.empty()) {
				aux = stackKeys.pop();
				auxC = stackCells.pop();
				auxC.setVisited(true);
				noVisitedKeys.remove(aux);
				if (cells.containsKey(aux)) {
					cells.get(aux).setVisited(true);
					cells.put(aux, auxC);
					cellsVisited.put(aux, auxC);
				}
			}
		}
	}
	
	/**
	 * Método al que se le pasa un laberinto y a partir de él devuelve dos pilas con los
	 * nuevos valores de las celdas que forman un camino en el laberinto.
	 * @param ranRow
	 * @param ranCol
	 * @param lab
	 * @param stackKeys
	 * @param stackCells
	 */
	public static void oneRoad(int ranRow, int ranCol, Labyrinth lab, 
			Stack<String> stackKeys, Stack<Cell> stackCells) {
		Map<String, Cell> cells = lab.getCells();
		Cell cell = null;
		String bucle = "";
		boolean done = false;
		int ranNei = 0;
		String last = "";
		int col = lab.getCols();
		int row = lab.getRows();
		
		while (!cells.get("(" + ranRow + ", " + ranCol + ")").getVisited()) {
			cell = cells.get("(" + ranRow + ", " + ranCol + ")");

			boolean[] neighbours = Arrays.copyOf(cell.getNeighbors(), 4);
			if (!stackKeys.contains("(" + ranRow + ", " + ranCol + ")")) {

				if (!stackKeys.empty()) {
					neighbours[0] = false;
					neighbours[1] = false;
					neighbours[2] = false;
					neighbours[3] = false;
					switch (ranNei) {
					case 0:
						neighbours[2] = true;
						break;
					case 1:
						neighbours[3] = true;
						break;
					case 2:
						neighbours[0] = true;
						break;
					case 3:
						neighbours[1] = true;
						break;
					}
				}

				// Order - N,E,S,W
				do {
					ranNei = (int) (Math.random() * 4);
					if (ranNei == 0 && neighbours[0] == false) {
						if (ranRow > 0) {
							neighbours[0] = true;
							stackKeys.push("(" + ranRow + ", " + ranCol + ")");
							stackCells.push(new Cell(0, neighbours, false));
							ranRow = ranRow - 1;
							done = true;
						}
					}
					if (ranNei == 1 && neighbours[1] == false) {
						if (ranCol < col - 1) {
							neighbours[1] = true;
							stackKeys.push("(" + ranRow + ", " + ranCol + ")");
							stackCells.push(new Cell(0, neighbours, false));
							ranCol = ranCol + 1;
							done = true;
						}
					}
					if (ranNei == 2 && neighbours[2] == false) {
						if (ranRow < row - 1) {
							neighbours[2] = true;
							stackKeys.push("(" + ranRow + ", " + ranCol + ")");
							stackCells.push(new Cell(0, neighbours, false));
							ranRow = ranRow + 1;
							done = true;
						}
					}
					if (ranNei == 3 && neighbours[3] == false) {
						if (ranCol > 0) {
							neighbours[3] = true;
							stackKeys.push("(" + ranRow + ", " + ranCol + ")");
							stackCells.push(new Cell(0, neighbours, false));
							ranCol = ranCol - 1;
							done = true;
						}
					}
				} while (!done);
				done = false;
			} 
			// Si se produce un bucle
			else {
				bucle = "(" + ranRow + ", " + ranCol + ")";
				while (stackKeys.contains(bucle)) {
					last = stackKeys.pop();
					stackCells.pop();
				}
				if (!stackKeys.empty()) {
					String key1 = stackKeys.peek();
					int x = key1.indexOf(",");

					int ranRow3 = Integer.parseInt(key1.substring(1, x));
					int ranCol3 = Integer.parseInt(key1.substring(x + 2, key1.length() - 1));

					int y = last.indexOf(",");

					int ranRow4 = Integer.parseInt(last.substring(1, y));
					int ranCol4 = Integer.parseInt(last.substring(y + 2, last.length() - 1));

					int r = ranRow3 - ranRow4;
					int c = ranCol3 - ranCol4;

					if (c == -1) {
						ranNei = 1;
					}
					if (c == 1) {
						ranNei = 3;
					}
					if (r == -1) {
						ranNei = 2;
					}
					if (r == 1) {
						ranNei = 0;
					}
				}
				bucle = "";

			}
		}
		cell = cells.get("(" + ranRow + ", " + ranCol + ")");

		boolean[] neighbours = Arrays.copyOf(cell.getNeighbors(), 4);
		if (!stackKeys.empty()) {
			switch (ranNei) {
			case 0:
				neighbours[2] = true;
				pushCell(ranRow, ranCol, neighbours, stackKeys, stackCells);
				break;
			case 1:
				neighbours[3] = true;
				pushCell(ranRow, ranCol, neighbours, stackKeys, stackCells);
				break;
			case 2:
				neighbours[0] = true;
				pushCell(ranRow, ranCol, neighbours, stackKeys, stackCells);
				break;
			case 3:
				neighbours[1] = true;
				pushCell(ranRow, ranCol, neighbours, stackKeys, stackCells);
				break;
			}
		}
	}
	
	/**
	 * Método que rellena la lista noVisitedKeys con todas las key de un laberinto
	 * @param noVisitedKeys
	 * @param lab
	 */
	public static void rellenarnoVisitedKeys(ArrayList<String> noVisitedKeys, Labyrinth lab) {
		int row = lab.getRows();
		int col = lab.getCols();
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				noVisitedKeys.add("(" + i + ", " + j + ")");
			}
		}
	}
	
	/**
	 * Metodo que añade un String a la pila de String y un Cell a la pila de Cell
	 * @param ranRow
	 * @param ranCol
	 * @param neighbours
	 * @param stackKeys
	 * @param stackCells
	 */
	public static void pushCell(int ranRow, int ranCol, boolean[] neighbours, 
			Stack<String> stackKeys, Stack<Cell> stackCells) {
		stackKeys.push("(" + ranRow + ", " + ranCol + ")");
		stackCells.push(new Cell(0, neighbours, false));
	}
}
