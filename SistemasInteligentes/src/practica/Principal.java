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
import practica.utilidades.WriteJson;

/**
 * Clase principal donde se pueden probar las funcionalidades del sistema
 * @author David González Bermúdez, Lucas Gutiérrez Durán, David Gutiérrez Mariblanca
 * Fecha: 16/10/2020
 * 
 */
public class Principal {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {

		int value = 0;
		boolean done = false;
		boolean seguir = false;

		System.out.println("|||PRACTICA DEL LABORATORIO DE INTELIGENTES|||");
		do {
			System.out.println("\n¿Qué es lo que quieres hacer?" + "\n1. Leer laberinto desde archivo .Json"
					+ "\n2. Generar laberinto aleatorio" + "\n3. Leer problema" + "\n4. Salir del programa");

			do {
				try {
					value = sc.nextInt();
					if(value <= 0) throw new NegativeIntegerException();
					if(value < 1 || value > 4) throw new NoValidNumberException();
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
			/*1º OPCION
 			* Se lee archivo JSON que contiene las propiedades de un laberinto. Tras la lectura
			* se generara el laberinto correspondiente, y se comprobara las inconsistencias del mismo
			*/
				Labyrinth lab;
				lab = ReadJson.readJson("");
				Functions.saveLab(lab);
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
			*/
				// COMPROBACIONES
				Problem pro = ReadJson.readProblem();
				System.out.println(pro);
				Node node = new Node(0, 1, "(2, 4)", null, "", 1, 1, 2);
				Node node1 = new Node(1, 1, "(1, 4)", node, "", 1, 1, 2);
				Node node2 = new Node(2, 1, "(2, 3)", node, "", 1, 1, 1);
				Node node3 = new Node(3, 1, "(0, 3)", node, "", 1, 1, 1);
				Node node4 = new Node(4, 1, "(2, 4)", node, "", 1, 1, 1);
				Node node5 = new Node(5, 1, "(2, 4)", node, "", 1, 1, 1);
				Node node6 = new Node(6, 1, "(2, 7)", node, "", 1, 1, 3);
				Node node7 = new Node(7, 1, "(2, 3)", node, "", 1, 1, 1);
				Node node8 = new Node(8, 1, "(1, 4)", node, "", 1, 1, 1);
				Node node9 = new Node(9, 1, "(0, 4)", node, "", 1, 1, 5);
				Border fron = new Border();
				fron.push(node);
				fron.push(node1);
				fron.push(node2);
				fron.push(node3);
				fron.push(node4);
				fron.push(node5);
				fron.push(node6);
				fron.push(node7);
				fron.push(node8);
				fron.push(node9);
				while (!(fron.size() == 0)) {
					System.out.println(fron.pop());
				}
				System.out.println(pro.getSucesors("(2, 3)"));
				
				WriteJson.writeJsonProblem(pro, "hola");
				break;
			case 4:
				System.out.println("Saliendo del programa");
				done = true;
				break;
			}
		} while (!done);
	}

}
