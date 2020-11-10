package practica;

import practica.utilidades.*;
import java.util.*;
import java.util.Scanner;
import practica.busqueda.Border;
import practica.busqueda.Node;
import practica.busqueda.Problem;
import practica.creacion.Labyrinth;
import practica.utilidades.Functions;
import practica.utilidades.ReadJson;
import practica.busqueda.*;

/**
 * Clase principal donde se pueden probar las funcionalidades del sistema
 * @author David González Bermúdez, Lucas Gutiérrez Durán, David Gutiérrez Mariblanca
 * Fecha: 16/10/2020
 */
public class Principal {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {

		int value = 0;
		boolean done = false;
		boolean seguir = false;

		System.out.println("|||PRACTICA DEL LABORATORIO DE INTELIGENTES|||");
		do {
			System.out.println("\nÂ¿Qué es lo que quieres hacer?" + "\n1. Leer laberinto desde archivo .Json"
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
					System.out.println("Error: No ha introducido un carácter numérico");
					System.out.print("Introduzca una de las opciones disponibles: ");
				}
				sc.nextLine();
			
			} while (!seguir);
			
			switch (value) {
			case 1:
			/*1Âº OPCION
 			* Se lee archivo JSON que contiene las propiedades de un laberinto. Tras la lectura
			* se generara el laberinto correspondiente, y se comprobara las inconsistencias del mismo
			*/
				Labyrinth lab;
				lab = ReadJson.readJson("");
				if (!(lab==null)) {
					Functions.saveLab(lab);
				}
				break;
			case 2:
			/*2Âº OPCION
 			* Se generá un laberinto eligiendo tamaño de columnas y tamaño de filas, posteriormente
			* se generara el laberinto correspondiente.
			*/	
				Functions.genLab();
				break;
			case 3:
			/*3Âº OPCION
 			* Se leera el archivo que contiene el problema, para una vez despues generar los nodos
			* correspondientes, y obtener asi los sucesores y la frontera. Tambien se comprobara la inconsistencia
			* del laberinto.
			*/
				// COMPROBACIONES
				Problem pro = ReadJson.readProblem();
				System.out.println("\t"+pro);
				double startTime1 = System.nanoTime();
				ArrayList<Node> solution = SearchAlgorithm.search(pro, 500, 1);
				double finalTime1 = System.nanoTime();
				System.out.println("El algoritmo de búsqueda tarda " + (finalTime1-startTime1) + " nanosegundos.");
				double startTime2 = System.nanoTime();
				ArrayList<Node> solution2 = SearchAlgorithm.search(pro, 500, 2);
				double finalTime2 = System.nanoTime();
				System.out.println("El algoritmo de búsqueda tarda " + (finalTime2-startTime2) + " nanosegundos.");
				double startTime3 = System.nanoTime();
				ArrayList<Node> solution3 = SearchAlgorithm.search(pro, 500, 3);
				double finalTime3 = System.nanoTime();
				System.out.println("El algoritmo de búsqueda tarda " + (finalTime3-startTime3) + " nanosegundos.");
				double startTime4 = System.nanoTime();
				ArrayList<Node> solution4 = SearchAlgorithm.search(pro, 500, 4);
				double finalTime4 = System.nanoTime();
				System.out.println("El algoritmo de búsqueda tarda " + (finalTime4-startTime4) + " nanosegundos.");
				double startTime5 = System.nanoTime();
				ArrayList<Node> solution5 = SearchAlgorithm.search(pro, 500, 5);
				double finalTime5 = System.nanoTime();
				System.out.println("El algoritmo de búsqueda tarda " + (finalTime5-startTime5) + " nanosegundos.");

				DrawSolution.saveImageSolution(pro, solution, 1);
				DrawSolution.saveImageSolution(pro, solution2, 2);
				DrawSolution.saveImageSolution(pro, solution3, 3);
				DrawSolution.saveImageSolution(pro, solution4, 4);
				DrawSolution.saveImageSolution(pro, solution5, 5);
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
