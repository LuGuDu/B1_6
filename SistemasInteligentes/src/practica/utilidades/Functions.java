package practica.utilidades;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.swing.JFileChooser;

import practica.creacion.Cell;
import practica.creacion.Labyrinth;
import practica.creacion.WilsonAlgorithm;

/**
 * Esta clase tiene funciones auxiliares
 * 
 * @author David Gonz�lez Berm�dez, Lucas Guti�rrez Dur�n, David Guti�rrez
 *         Mariblanca Fecha: 16/10/2020
 */
public class Functions {

	static Scanner sc = new Scanner(System.in);

	/**
	 * Pasando un laberinto devuelves sus celdas en forma de array
	 * 
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
				System.out.println("Introduzca un n�mero entero POSITIVO: ");
			} catch (InputMismatchException e) {
				System.out.println("Error: No ha introducido un car�cter num�rico");
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
	 * 
	 * @param lab
	 */
	public static void saveLab(Labyrinth lab) {
		String name = null;
		char a = (char) 92; // character "
		char b = (char) 34; // character \
		boolean seguir = false;
		JFileChooser fc = new JFileChooser();
		int valorDevuelto = fc.showSaveDialog(null);
		File fileToSave;

		if (valorDevuelto == JFileChooser.APPROVE_OPTION) {
			fileToSave = fc.getSelectedFile();
			name = fileToSave.getName();
			if (name.contains("<") || name.contains(">") || name.contains(":") || name.contains("*")
					|| name.contains("?") || name.contains("|") || name.contains("/") || name.contains(a + "")
					|| name.contains(b + "")) {
				System.out.println("Error: Caracter no valido\n"
						+ "Introduzca un nombre sin los caracteres < > : * / ? | " + a + " " + b);
			} else {
				WriteJson.writeJsonLab(lab, fileToSave);
				DrawLab.drawLab(lab, fileToSave.getName());
				System.out.println("\nLos archivos se han guardado correctamente!");
			}
		} else {
			System.out.println("El usuario ha cancelado el guardado");
		}
	}

	public static void genProblem() {
		
	}
	
	public static void saveProblem() {
		
	}
	
	public static int getRow(String idState) {
		int x = idState.indexOf(",");
		int row = Integer.parseInt(idState.substring(1, x));
		return row;
	}

	public static int getCol(String idState) {
		int x = idState.indexOf(",");
		int col = Integer.parseInt(idState.substring(x + 2, idState.length() - 1));
		return col;
	}
}