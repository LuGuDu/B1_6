package practica;

import java.util.*;
import java.util.Scanner;

class NegativeIntegerException extends Exception {
	public NegativeIntegerException(){
		super("Error: N�mero negativo o cero");
	}
}

class NoValidNumberException extends Exception {
	public NoValidNumberException(){
		super("Error: Opcion no valida");
	}
}
/**
 * Clase principal donde se pueden probar las funcionalidades del sistema
 * @author David Gonz�lez Berm�dez, Lucas Guti�rrez Dur�n, David Guti�rrez Mariblanca
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
			System.out.println("\n�Qu� es lo que quieres hacer?" + "\n1. Leer laberinto desde archivo .Json"
					+ "\n2. Generar laberinto aleatorio" + "\n3. Salir del programa");

			do {
				try {
					value = sc.nextInt();
					if(value <= 0) throw new NegativeIntegerException();
					if(value < 1 || value > 3) throw new NoValidNumberException();
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
					System.out.println("Error: No ha introducido un car�cter num�rico");
					System.out.print("Introduzca una de las opciones disponibles: ");
				}
				sc.nextLine();
			
			} while (!seguir);
			
			switch (value) {
			case 1:
				Labyrinth lab;
				boolean checkGood;
				lab = ReadJson.readJsons();
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
				System.out.println("Saliendo del programa");
				done = true;
			}
		} while (!done);
	}

}
