package practica;

import practica.utilidades.*;
import java.util.*;

import practica.busqueda.Border;
import practica.busqueda.Node;
import practica.busqueda.Problem;
import practica.creacion.Labyrinth;
import practica.utilidades.Functions;
import practica.utilidades.ReadJson;
import practica.busqueda.*;

/**
 * Clase principal donde se pueden probar las funcionalidades del sistema
 * @author David González Bermúdez, Lucas Guti�rrez Durán, David Guti�rrez Mariblanca
 * Fecha: 16/10/2020
 * 
 */
public class Principal {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {

		int value = 0;
		boolean done = false;
		boolean seguir = false;

		System.out.println("|||PRACTICA DEL LABORATORIO DE INTELIGENTES|||");
		do {

			System.out.println("\n�Qu� es lo que quieres hacer?" + "\n1. Leer laberinto desde archivo .Json"
					+ "\n2. Generar laberinto aleatorio" + "\n3. Leer problema" + "\n4. Generar problema" + "\n5. Salir del programa");

			do {
				try {
					value = sc.nextInt();
					if(value <= 0) throw new NegativeIntegerException();
					if(value < 1 || value > 5) throw new NoValidNumberException();
					seguir = true;
				}
				catch(NoValidNumberException e) {
					System.out.println(e.getMessage());
					System.out.print("Introduzca una de las opciones disponibles: ");
				}
				catch(NegativeIntegerException e) {
					System.out.println(e.getMessage());
					System.out.print("Introduzca una de las opciones disponibles: ");
				}
				catch(InputMismatchException e) {
					System.out.println("Error: No ha introducido un carácter num�rico");
					System.out.print("Introduzca una de las opciones disponibles: ");
				}
				sc.nextLine();
			
			} while (!seguir);
			
			switch (value) {
			case 1:
			/*1º OPCION
 			* Se lee archivo JSON que contiene las propiedades de un laberinto. Tras la lectura
			* se generara el laberinto correspondiente, y se comprobara las inconsistencias del mismo
			*/
				Labyrinth lab;
				lab = ReadJson.readJson("");
				if (!(lab==null)) {
					Functions.saveLab(lab, false);
				}
				break;
			case 2:
			/*2º OPCION
 			* Se generá un laberinto eligiendo tamaño de columnas y tamaño de filas, posteriormente
			* se generara el laberinto correspondiente.
			*/	
				Functions.genLab();
				break;
			case 3:
			/*3º OPCION
 			* Se leera el archivo que contiene el problema, para una vez despues generar los nodes
			* correspondientes, y obtener asi los sucesores y la frontera. Tambien se comprobara la inconsistencia
			* del laberinto.
			* 
			* Generara el problema usando cada uno de los algoritmos de busqueda correspondientes, y calculara el tiempo
			* de ejecuccion de cada uno
			* 
			*/
				// COMPROBACIONES
				ArrayList<String> visited = new ArrayList<String>();
				Border border = new Border();
				
				Problem pro = ReadJson.readProblem();

				System.out.println("\t"+pro);
				
				for(int i = 1; i<6; i++) {				
					double startTime = System.nanoTime();
					ArrayList<Node> solution = SearchAlgorithm.search(pro, 500000, i, visited, border);
					double finalTime = System.nanoTime();
					System.out.println("El algoritmo de búsqueda tarda " + (finalTime-startTime) + " nanosegundos.");
					PrintSolution.printSolution(solution, i);
					DrawSolution.saveImageSolution(pro, solution, i, visited, border);
					visited.clear();
				}

				
				break;
				
			case 4:
			/*4Âº OPCION
	 		* Se generará un problema a gusto del usuario permitiendole elegir el nodo inicial, el nodo objetivo
	 		* y el problema donde se realizará la busqueda de la solución.
			*/
				Functions.genProblem();
				break;
				
			case 5:
				System.out.println("Saliendo del programa");
				done = true;
				break;
			}
		} while (!done);
	}

}
