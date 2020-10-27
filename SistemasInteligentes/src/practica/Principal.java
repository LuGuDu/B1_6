package practica;

import java.util.*;
import java.util.Scanner;

class NegativeIntegerException extends Exception {
	public NegativeIntegerException(){
		super("Error: Número negativo o cero");
	}
}

class NoValidNumberException extends Exception {
	public NoValidNumberException(){
		super("Error: Opcion no valida");
	}
}
/**
 * Clase principal donde se pueden probar las funcionalidades del sistema
 * @author David González Bermúdez, Lucas Gutiérrez Durán, David Gutiérrez Mariblanca
 * Fecha: 16/10/2020
 */
public class Principal {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws NegativeIntegerException {

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
				Labyrinth lab;
				boolean checkGood;
				lab = ReadJson.readJson("");
				checkGood = Functions.checkSemantic(lab);

				if (checkGood) {
					Functions.saveLab(lab);
				} else {
					System.out.println("\nEL ARCHIVO JSON ES INCONSISTENTE");
				}
				break;
			case 2:
				Functions.genLab();
				break;
			case 3:
				// COMPROBACIONES
				Problem pro = ReadJson.readProblem();
				System.out.println(pro);
				Node nodo = new Node(0, 1, "(2, 4)", 6, "", 1, 1, 2);
				Node nodo1 = new Node(1, 1, "(1, 4)", 4, "", 1, 1, 2);
				Node nodo2 = new Node(2, 1, "(2, 3)", 3, "", 1, 1, 1);
				Node nodo3 = new Node(3, 1, "(0, 3)", 1, "", 1, 1, 1);
				Node nodo4 = new Node(4, 1, "(2, 4)", 1, "", 1, 1, 1);
				Node nodo5 = new Node(5, 1, "(2, 4)", 1, "", 1, 1, 1);
				Node nodo6 = new Node(6, 1, "(2, 7)", 1, "", 1, 1, 3);
				Node nodo7 = new Node(7, 1, "(2, 3)", 1, "", 1, 1, 1);
				Node nodo8 = new Node(8, 1, "(1, 4)", 3, "", 1, 1, 1);
				Node nodo9 = new Node(9, 1, "(0, 4)", 2, "", 1, 1, 5);
				Border fron = new Border();
				fron.push(nodo);
				fron.push(nodo1);
				fron.push(nodo2);
				fron.push(nodo3);
				fron.push(nodo4);
				fron.push(nodo5);
				fron.push(nodo6);
				fron.push(nodo7);
				fron.push(nodo8);
				fron.push(nodo9);
				while (!(fron.size() == 0)) {
					System.out.println(fron.pop());
				}
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
