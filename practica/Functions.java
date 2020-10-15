package practica;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Functions {

	static Scanner sc = new Scanner(System.in);

	/**
	 * Pasando un laberinto devuelves sus celdas en forma de array
	 * @param map
	 * @return
	 */
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

	/**
	 * Genera un laberinto desde 0
	 */
	public static void genLab() {
		int[][] mov = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		String[] id_mov = { "N", "E", "S", "O" };
		LinkedHashMap<String, Cell> cells = new LinkedHashMap<String, Cell>();
		int row = 0;
		int col = 0;
		boolean seguir = false;
		
		do {
			try {
				System.out.println("Indique las filas: ");
				row = sc.nextInt();
				if (row <= 0)
					throw new NegativeIntegerException();
				System.out.println("Indique las columnas: ");
				col = sc.nextInt();
				if (col <= 0)
					throw new NegativeIntegerException();
				seguir = true;
			} catch (NegativeIntegerException e) {
				System.out.println(e.getMessage());
				System.out.println("Introduzca un número entero POSITIVO: ");
			} catch (InputMismatchException e) {
				System.out.println("Error: No ha introducido un carácter numérico");
			}
			sc.nextLine();
		} while (!seguir);

		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				boolean[] neighbours = { false, false, false, false };
				Cell cell = new Cell(0, neighbours, false);
				cells.put("(" + i + ", " + j + ")", cell);
			}
		}

		Labyrinth lab = new Labyrinth(row, col, 4, mov, id_mov, cells);

		WilsonAlgorithm.wilson(lab);
		saveLab(lab);
	}

	public static void saveLab(Labyrinth lab) {
		String name = null;
		char a = (char) 92; // character "
		char b = (char) 34; // character \
		boolean seguir = false;
		System.out.println("¿Con qué nombre quiere guardar los archivos?");		
		do {	
			name = sc.next();
			if (name.contains("<") || name.contains(">") || name.contains(":") || name.contains("*")
					|| name.contains("?") || name.contains("|") || name.contains("/") || name.contains(a + "")
					|| name.contains(b + "")) {
				System.out.println("Error: Caracter no valido\n"
						+ "Introduzca un nombre sin los caracteres < > : * / ? | " + a + " " + b);
			} else {
				seguir = true;
			}
		}while(!seguir);
		WriteJson.writeJson(lab, name);
		DrawLab.drawLab(lab, name);
		System.out.println("\nLos archivos se han guardado en su escritorio!");
	}

	

	public static void readLab() {
		Labyrinth lab;
		boolean checkGood;
		System.out.println("\nEscriba la ruta completa de su archivo .json:");
		lab = ReadJson.readJsons();
		checkGood = checkSemantic(lab);

		if (checkGood) {
			saveLab(lab);
		} else {
			System.out.println("\nEL ARCHIVO JSON ES INCONSISTENTE");
		}

	}
	
	public static boolean checkSemantic(Labyrinth lab) {
		Map<String, Cell> cells = lab.getCells();
		Cell cellCheck = null;
		Cell cellCheckCol = null;
		Cell cellCheckRow = null;
		boolean semanticGood = true;

		/*
		 * BUCLES FOR
		 * Los bucles for se encargan de recorrer las celdas de nuestro laberinto
		 * para comprobar sus vecinos y sus celdas adyacentes
		 */

		for (int i = 0; i < lab.getRows(); i++) {
			for (int j = 0; j < lab.getCols(); j++) {

				// Se obtienen las celdas y sus vecinos 
				cellCheck = cells.get("(" + i + ", " + j + ")");
				boolean[] neighbours = Arrays.copyOf(cellCheck.getNeighbors(), 4);

				/*				 * 
				 * COMPROBACIÓN DE LAS CELDAS
				 * Se comprueban las n-1 celdas y sus vecinos adyacentes (Sur y Este). No es necesario
				 * comprobar todos los vecinos (N,S,E,O) por cada celda.
				 * 
				 * El valor de semanticGood es siempre verdadero. En caso contrario, si encontramos inconsistencias
				 * entre dos celdas, el valor de semanticGood será falso.
				 * 
				 */

				if (j < (lab.getCols() - 1) && i < (lab.getRows() - 1)) {

					// Se obtienen las celdas y sus vecinos adyacentes

					cellCheckCol = cells.get("(" + i + ", " + (j + 1) + ")");
					boolean[] neighboursCol = Arrays.copyOf(cellCheckCol.getNeighbors(), 4);

					cellCheckRow = cells.get("(" + (i + 1) + ", " + j + ")");
					boolean[] neighboursRow = Arrays.copyOf(cellCheckRow.getNeighbors(), 4);

					// Se comprueba el vecino este y sur, en caso de encontrar una inconsistencia, se comunica

					if (!(neighbours[1] == neighboursCol[3] && neighbours[2] == neighboursRow[0])) {

						semanticGood = false;

						// VECINO ESTE
						if (!(neighbours[1] == neighboursCol[3])) {

							System.out.println("\nInconsistencia entre las celdas (" + i + "," + j + ") y (" + i
									+ ", " + (j + 1) + ")");
						}

						// VECINO SUR
						if (!(neighbours[2] == neighboursRow[0])) {

							System.out.println("\nInconsistencia entre las celdas (" + i + "," + j + ") y (" + (i + 1)
									+ ", " + j + ")");
						}

					}

				}

				// Se comprueba la ultima fila y la ultima columna, usandose otro procedimiento distinto al anterior

				if (!(j == (lab.getCols() - 1) && i == (lab.getRows() - 1))) {

					/*
					 * COMPROBACION DE LA ULTIMA COLUMNA
					 * En la comprobación de la ultma columna, solo es necesario comprobar
					 * el vecino sur
					 */

					if (j == (lab.getCols() - 1)) {
						cellCheckRow = cells.get("(" + (i + 1) + ", " + j + ")");
						boolean[] neighboursRow = Arrays.copyOf(cellCheckRow.getNeighbors(), 4);

						if (!(neighbours[2] == neighboursRow[0])) { // En caso de encontrar una inconsistencia, se comunica
							semanticGood = false;
							System.out.println("\nInconsistencia entre las celdas (" + i + "," + j + ") y (" + (i + 1)
									+ ", " + j + ")");
						}

					}

					/*
					 * COMPROBACION DE LA ULTIMA FILA
					 * En la comprobación de la ultma fila, solo es necesario comprobar
					 * el vecino este
					 */
					
					if (i == (lab.getRows() - 1)) {
						cellCheckCol = cells.get("(" + i + ", " + (j + 1) + ")");
						boolean[] neighboursCol = Arrays.copyOf(cellCheckCol.getNeighbors(), 4);

						if (!(neighbours[1] == neighboursCol[3])) {  // En caso de encontrar una inconsistencia, se comunica
							semanticGood = false;
							System.out.println("\nInconsistencia entre las celdas (" + i + "," + j + ") y (" + i
									+ ", " + (j + 1) + ")");
						}

					}
				}

			}

		}

		return semanticGood;
	}
}
