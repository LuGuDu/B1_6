package practica;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

/**
 * Esta clase tiene funciones auxiliares
 * @author David González Bermúdez, Lucas Gutiérrez Durán, David Gutiérrez Mariblanca
 * Fecha: 16/10/2020
 */
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

	/**
	 * Se encarga de guardar el laberinto en forma de imagen y documento de texto
	 * @param lab
	 */
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
		WriteJson.writeJsonLab(lab, name);
		DrawLab.drawLab(lab, name);
		System.out.println("\nLos archivos se han guardado en su escritorio!");
	}

	/**
	 * Método que a partir de un laberinto verifica si su semántica es correcta o no
	 * @param lab
	 * @return
	 */
	public static boolean checkSemantic(Labyrinth lab) {
		Map<String, Cell> cells = lab.getCells();
		Cell cellCheck = null;
		Cell cellCheckCol = null;
		Cell cellCheckRow = null;
		boolean semanticGood = true;
		// boolean[][] semanticGood = new boolean[lab.getCols()][lab.getRows()];

		for (int i = 0; i < lab.getRows(); i++) {
			for (int j = 0; j < lab.getCols(); j++) {

				cellCheck = cells.get("(" + i + ", " + j + ")");
				boolean[] neighbours = Arrays.copyOf(cellCheck.getNeighbors(), 4);

				if (j < (lab.getCols() - 1) && i < (lab.getRows() - 1)) {
					cellCheckCol = cells.get("(" + i + ", " + (j + 1) + ")");
					boolean[] neighboursCol = Arrays.copyOf(cellCheckCol.getNeighbors(), 4);
					cellCheckRow = cells.get("(" + (i + 1) + ", " + j + ")");
					boolean[] neighboursRow = Arrays.copyOf(cellCheckRow.getNeighbors(), 4);

					// En cada celda, va a ir comprobando el vecino este y sur, comprobar los 4 a la
					// vez son innecesarios
					if (!(neighbours[1] == neighboursCol[3] && neighbours[2] == neighboursRow[0])) {
						// semanticGood[i][j] = true;
						semanticGood = false;

						if (!(neighbours[1] == neighboursCol[3])) {

							System.out.println("\nFallo de inconsistencia entre las celdas (" + i + "," + j + ") y ("
									+ i + ", " + (j + 1) + ")");
						}

						if (!(neighbours[2] == neighboursRow[0])) {

							System.out.println("\nFallo de inconsistencia entre las celdas (" + i + "," + j + ") y ("
									+ (i + 1) + ", " + j + ")");
						}

					}

				}

				if (!(j == (lab.getCols() - 1) && i == (lab.getRows() - 1))) {
					// semanticGood[i][j]=true;
					// Esta celda cuando sea llegada no va a necesitar comprobacion debido a que ha
					// sido comprobada

					// Si llega a la ultima columna, que compruebe su vecino del sur
					if (j == (lab.getCols() - 1)) {
						cellCheckRow = cells.get("(" + (i + 1) + ", " + j + ")");
						boolean[] neighboursRow = Arrays.copyOf(cellCheckRow.getNeighbors(), 4);

						if (!(neighbours[2] == neighboursRow[0])) {
							// semanticGood[i][j] = true;
							semanticGood = false;
							System.out.println("\nFallo de inconsistencia entre las celdas (" + i + "," + j + ") y ("
									+ (i + 1) + ", " + j + ")");
						}

					}
					// Si llega a la ultima fila, que compruebe su vecino del este
					if (i == (lab.getRows() - 1)) {
						cellCheckCol = cells.get("(" + i + ", " + (j + 1) + ")");
						boolean[] neighboursCol = Arrays.copyOf(cellCheckCol.getNeighbors(), 4);

						if (!(neighbours[1] == neighboursCol[3])) {
							// semanticGood[i][j] = true;
							semanticGood = false;
							System.out.println("\nFallo de inconsistencia entre las celdas (" + i + "," + j + ") y ("
									+ i + ", " + (j + 1) + ")");
						}

					}
				}

			}

		}

		return semanticGood;
	}
}