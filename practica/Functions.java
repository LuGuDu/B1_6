import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.Map.Entry;

public class Functions {

	static Scanner sc = new Scanner(System.in);

	public static Cell[] getCellsFromMap(Map<String, Cell> map) {
		int counter = 0;
		Cell[] cellsArray = new Cell[map.size()];

		for (Iterator<Entry<String, Cell>> iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, Cell> pairEntry = iterator.next();
			cellsArray[counter] = (Cell) pairEntry.getValue();
			counter++;
		}

		return cellsArray;
	}

	public static void genLab() {
		
		System.out.println("Indique las filas: ");
		int row = keyboardInt();
		System.out.println("Indique las columnas: ");
		int col = keyboardInt();

		int[][] mov = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		String[] id_mov = { "N", "E", "S", "O" };

		Stack<String> stackAuxVisited = new Stack<String>();
		Stack<Cell> stackAuxVisitedC = new Stack<Cell>();
		String aux = "";
		Cell auxC = null;
		ArrayList<String> noVisited = new ArrayList<String>();
		LinkedHashMap<String, Cell> cells = new LinkedHashMap<String, Cell>();
		LinkedHashMap<String, Cell> cellVisited = new LinkedHashMap<String, Cell>();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				boolean[] neighbours = { false, false, false, false };
				Cell cell = new Cell(0, neighbours, false);
				cells.put("(" + i + "," + j + ")", cell);
				noVisited.add("(" + i + "," + j + ")");
			}
		}

		Labyrinth lab = new Labyrinth(row, col, 4, mov, id_mov, cells);

		int position2 = (int) (Math.random() * (noVisited.size()));
		String key2 = noVisited.get(position2);

		lab.getCells().get(key2).setVisited(true);

		while (cellVisited.size() < cells.size()) {
			int position = (int) (Math.random() * (noVisited.size()));
			String key1 = noVisited.get(position);
			int x = key1.indexOf(",");

			int ranRow = Integer.parseInt(key1.substring(1, x));
			int ranCol = Integer.parseInt(key1.substring(x + 1, key1.length() - 1));

			Functions.wilsonAlg(col, row, ranRow, ranCol, lab, stackAuxVisited, stackAuxVisitedC);
			while (!stackAuxVisited.empty()) {
				aux = stackAuxVisited.pop();
				auxC = stackAuxVisitedC.pop();
				auxC.setVisited(true);
				noVisited.remove(aux);
				if (cells.containsKey(aux)) {
					cells.get(aux).setVisited(true);
					cells.put(aux, auxC);
					cellVisited.put(aux, auxC);
				}
			}
		}
		saveLab(lab);
	}

	public static void saveLab(Labyrinth lab) {
		String path;
		System.out.println("\n�Donde quiere guardar los resultados?");
		path = sc.next();
		WriteJson.writeJson(lab, path);
		DrawLab.drawLab(lab, path);
		System.out.println("\nHecho!");
	}

	public static void wilsonAlg(int col, int row, int ranRow, int ranCol, Labyrinth lab, Stack<String> stackAuxVisited,
			Stack<Cell> stackAuxVisitedC) {
		Map<String, Cell> cells = lab.getCells();
		Cell cell = null;
		String bucle = "";
		boolean done = false;
		int ranNei = 0;
		String last = "";
		while (!cells.get("(" + ranRow + "," + ranCol + ")").isVisited()) {
			cell = cells.get("(" + ranRow + "," + ranCol + ")");

			boolean[] neighbours = Arrays.copyOf(cell.getNeighbors(), 4);
			if (!stackAuxVisited.contains("(" + ranRow + "," + ranCol + ")")) {

				if (!stackAuxVisited.empty()) {
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
							stackAuxVisited.push("(" + ranRow + "," + ranCol + ")");
							stackAuxVisitedC.push(new Cell(0, neighbours, false));
							ranRow = ranRow - 1;
							done = true;
						}
					}
					if (ranNei == 1 && neighbours[1] == false) {
						if (ranCol < col - 1) {
							neighbours[1] = true;
							stackAuxVisited.push("(" + ranRow + "," + ranCol + ")");
							stackAuxVisitedC.push(new Cell(0, neighbours, false));
							ranCol = ranCol + 1;
							done = true;
						}
					}
					if (ranNei == 2 && neighbours[2] == false) {
						if (ranRow < row - 1) {
							neighbours[2] = true;
							stackAuxVisited.push("(" + ranRow + "," + ranCol + ")");
							stackAuxVisitedC.push(new Cell(0, neighbours, false));
							ranRow = ranRow + 1;
							done = true;
						}
					}
					if (ranNei == 3 && neighbours[3] == false) {
						if (ranCol > 0) {
							neighbours[3] = true;
							stackAuxVisited.push("(" + ranRow + "," + ranCol + ")");
							stackAuxVisitedC.push(new Cell(0, neighbours, false));
							ranCol = ranCol - 1;
							done = true;
						}
					}
				} while (!done);
				done = false;
			} else {
				bucle = "(" + ranRow + "," + ranCol + ")";
				while (stackAuxVisited.contains(bucle)) {
					last = stackAuxVisited.pop();
					stackAuxVisitedC.pop();
				}
				if (!stackAuxVisited.empty()) {
					String key1 = stackAuxVisited.peek();
					int x = key1.indexOf(",");

					int ranRow3 = Integer.parseInt(key1.substring(1, x));
					int ranCol3 = Integer.parseInt(key1.substring(x + 1, key1.length() - 1));

					int y = last.indexOf(",");

					int ranRow4 = Integer.parseInt(last.substring(1, y));
					int ranCol4 = Integer.parseInt(last.substring(y + 1, last.length() - 1));

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
		cell = cells.get("(" + ranRow + "," + ranCol + ")");

		boolean[] neighbours = Arrays.copyOf(cell.getNeighbors(), 4);
		if (!stackAuxVisited.empty()) {
			switch (ranNei) {
			case 0:
				neighbours[2] = true;
				stackAuxVisited.push("(" + ranRow + "," + ranCol + ")");
				stackAuxVisitedC.push(new Cell(0, neighbours, false));
				break;
			case 1:
				neighbours[3] = true;
				stackAuxVisited.push("(" + ranRow + "," + ranCol + ")");
				stackAuxVisitedC.push(new Cell(0, neighbours, false));
				break;
			case 2:
				neighbours[0] = true;
				stackAuxVisited.push("(" + ranRow + "," + ranCol + ")");
				stackAuxVisitedC.push(new Cell(0, neighbours, false));
				break;
			case 3:
				neighbours[1] = true;
				stackAuxVisited.push("(" + ranRow + "," + ranCol + ")");
				stackAuxVisitedC.push(new Cell(0, neighbours, false));
				break;
			}
		}
	}

	public static int keyboardInt() {
		int value = sc.nextInt();
		return value;
	}

	public static void readLab() {
		String path;
		Labyrinth lab;
		System.out.println("\nEscriba la ruta completa de su archivo .json:");
		path = sc.next();
		lab = ReadJson.readJsons(path);
		Functions.saveLab(lab);
		
	}
}
