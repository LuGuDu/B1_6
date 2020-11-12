package practica.utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFileChooser;

import com.google.gson.Gson;

import practica.busqueda.Problem;
import practica.creacion.Cell;
import practica.creacion.Labyrinth;

class InvalidFileException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidFileException() {
		super("Error: Archivo Json no valido");
	}
}

/**
 * Esta clase permite leer archivos .json. Pedirá que introduzcas la ruta
 * completa del archivo
 * 
 * @author David González Bermúdez, Lucas Gutiérrez Durán, David Gutiérrez
 *         Mariblanca Fecha: 16/10/2020
 */
public class ReadJson {

	static Scanner sc = new Scanner(System.in);

	public static Labyrinth readJson(String path){

		String json = "";

		boolean labCorrect = false;
		boolean seguir = false;
		boolean cancel = false;
		Labyrinth lab = null;

		if (path.equals("")) {
			System.out.println("\nSeleccione la ruta de su archivo .json:");
		} else {
			//path = System.getProperty("user.home") + "/desktop/" + path;
		}

		do {
			do {
				try {
					if (path.equals("")) {
						JFileChooser fcOpen = new JFileChooser();
						fcOpen.setFileFilter(new ImageFilter());
						int valorDevuelto = fcOpen.showOpenDialog(null);

						if (valorDevuelto == JFileChooser.APPROVE_OPTION) {
							File file = fcOpen.getSelectedFile();
							path = file.getAbsolutePath();
						} else {
							System.out.println("Operación cancelada");
							cancel = true;
							break;
						}
					}
					System.out.println(path);
					String fileExtension = path.substring(path.lastIndexOf(".") + 1);
					if (!fileExtension.equals("json"))
						throw new InvalidFileException();
					BufferedReader br = new BufferedReader(new FileReader(path));
					seguir = true;
					String line = "";
					while ((line = br.readLine()) != null) {
						json += line;
					}

					br.close();

				} catch (InvalidFileException e) {
					System.out.println(e.getMessage());
					System.out.println("Vuelva a intentarlo con otro archivo:");
				} catch (IOException IOE) {
					System.out.println(IOE.toString());
				} finally {
					path = "";
				}

			} while (!seguir);
			if (cancel) break;
			Gson gson = new Gson();
			try {
				lab = gson.fromJson(json, Labyrinth.class);
				labCorrect = true;
			} catch (com.google.gson.JsonSyntaxException JSE) {

				System.out.println(JSE.toString());
				System.out.println("Error sintáctico en el archivo.");
				break;
			}
			labCorrect = checkSemantic(lab);
		} while (!labCorrect);

		return lab;
	}

	/**
	 * Esta clase permite leer archivos .json para asi poder generar el problem. Se
	 * pedirá que introduzcas la ruta completa del archivo
	 * 
	 * @author David González Bermúdez, Lucas Gutiérrez Durán, David Gutiérrez
	 *         Mariblanca Fecha: 28/10/2020
	 */
	public static Problem readProblem() {
		String json = "";
		String path=null;
		boolean problemCorrect = false;
		boolean follow = false;
		boolean cancel = false;
		Problem problem = new Problem();
		System.out.println("\nSeleccione la ruta completa de su archivo .json:");

		do {
			do {
				try {
					JFileChooser fcOpen = new JFileChooser();
					fcOpen.setFileFilter(new ImageFilter());
					int valorDevuelto = fcOpen.showOpenDialog(null);

					if (valorDevuelto == JFileChooser.APPROVE_OPTION) {
						File file = fcOpen.getSelectedFile();
						path = file.getAbsolutePath();
					} else {
						System.out.println("Operación cancelada");
						cancel = true;
						break;
					}
					String fileExtension = path.substring(path.lastIndexOf(".") + 1);
					if (!fileExtension.equals("json"))
						throw new InvalidFileException();
					BufferedReader br = new BufferedReader(new FileReader(path));
					follow = true;
					String line = "";
					while ((line = br.readLine()) != null) {
						json += line;
					}

					br.close();

				} catch (InvalidFileException e) {
					System.out.println(e.getMessage());
					System.out.println("Vuelva a intentarlo con otro archivo:");
				} catch (FileNotFoundException FNFE) {
					System.out.println("Error: archivo no encontrado");
					System.out.println("Vuelva a intentar introducir la ruta: ");
				} catch (IOException IOE) {
					System.out.println(IOE.toString());
				}
			} while (!follow);

			if (cancel) break;
			Gson gson = new Gson();
			try {
				problem = gson.fromJson(json, Problem.class);
				problemCorrect = true;
			} catch (com.google.gson.JsonSyntaxException JSE) {

				System.out.println(JSE.toString());
				System.out.println("Error sintáctico en el archivo.");
				System.out.println("Vuelva a intentar introducir la ruta: ");
			}
		} while (!problemCorrect);
		Path prueba = Paths.get(path);
		
		String photopath = (prueba.getParent()).toString() + "/" + problem.getMaze();
		problem.setLab(readJson(photopath));
		problem.setPath(path);
		
		return problem;
	}

	/**
	 * Método que a partir de un laberinto verifica si su semántica es correcta o no
	 * 
	 * @param lab
	 * @return
	 */
	public static boolean checkSemantic(Labyrinth lab) {
		Map<String, Cell> cells = lab.getCells();
		Cell cellCheck = null;
		Cell cellCheckCol = null;
		Cell cellCheckRow = null;
		boolean semanticGood = true;

		/*
		 * BUCLES FOR Los bucles for se encargan de recorrer las celdas de nuestro
		 * laberinto para comprobar sus vecinos y sus celdas adyacentes
		 */

		for (int i = 0; i < lab.getRows(); i++) {
			for (int j = 0; j < lab.getCols(); j++) {

				// Se obtienen las celdas y sus vecinos
				cellCheck = cells.get("(" + i + ", " + j + ")");
				boolean[] neighbours = Arrays.copyOf(cellCheck.getNeighbors(), 4);

				/*
				 * * COMPROBACIÓN DE LAS CELDAS Se comprueban las n-1 celdas y sus vecinos
				 * adyacentes (Sur y Este). No es necesario comprobar todos los vecinos
				 * (N,S,E,O) por cada celda.
				 * 
				 * El valor de semanticGood es siempre verdadero. En caso contrario, si
				 * encontramos inconsistencias entre dos celdas, el valor de semanticGood será
				 * falso.
				 * 
				 */

				if (j < (lab.getCols() - 1) && i < (lab.getRows() - 1)) {

					// Se obtienen las celdas y sus vecinos adyacentes

					cellCheckCol = cells.get("(" + i + ", " + (j + 1) + ")");
					boolean[] neighboursCol = Arrays.copyOf(cellCheckCol.getNeighbors(), 4);

					cellCheckRow = cells.get("(" + (i + 1) + ", " + j + ")");
					boolean[] neighboursRow = Arrays.copyOf(cellCheckRow.getNeighbors(), 4);

					// Se comprueba el vecino este y sur, en caso de encontrar una inconsistencia,
					// se comunica

					if (!(neighbours[1] == neighboursCol[3] && neighbours[2] == neighboursRow[0])) {

						semanticGood = false;

						// VECINO ESTE
						if (!(neighbours[1] == neighboursCol[3])) {

							System.out.println("\nInconsistencia entre las celdas (" + i + "," + j + ") y (" + i + ", "
									+ (j + 1) + ")");
						}

						// VECINO SUR
						if (!(neighbours[2] == neighboursRow[0])) {

							System.out.println("\nInconsistencia entre las celdas (" + i + "," + j + ") y (" + (i + 1)
									+ ", " + j + ")");
						}

					}

				}

				// Se comprueba la ultima fila y la ultima columna, usandose otro procedimiento
				// distinto al anterior

				if (!(j == (lab.getCols() - 1) && i == (lab.getRows() - 1))) {

					/*
					 * COMPROBACION DE LA ULTIMA COLUMNA En la comprobación de la ultma columna,
					 * solo es necesario comprobar el vecino sur
					 */

					if (j == (lab.getCols() - 1)) {
						cellCheckRow = cells.get("(" + (i + 1) + ", " + j + ")");
						boolean[] neighboursRow = Arrays.copyOf(cellCheckRow.getNeighbors(), 4);

						if (!(neighbours[2] == neighboursRow[0])) { // En caso de encontrar una inconsistencia, se
																	// comunica
							semanticGood = false;
							System.out.println("\nInconsistencia entre las celdas (" + i + "," + j + ") y (" + (i + 1)
									+ ", " + j + ")");
						}

					}

					/*
					 * COMPROBACION DE LA ULTIMA FILA En la comprobación de la ultma fila, solo es
					 * necesario comprobar el vecino este
					 */

					if (i == (lab.getRows() - 1)) {
						cellCheckCol = cells.get("(" + i + ", " + (j + 1) + ")");
						boolean[] neighboursCol = Arrays.copyOf(cellCheckCol.getNeighbors(), 4);

						if (!(neighbours[1] == neighboursCol[3])) { // En caso de encontrar una inconsistencia, se
																	// comunica
							semanticGood = false;
							System.out.println("\nInconsistencia entre las celdas (" + i + "," + j + ") y (" + i + ", "
									+ (j + 1) + ")");
						}
					}
				}
			}
		}

		return semanticGood;
	}
}
