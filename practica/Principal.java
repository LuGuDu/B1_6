import java.util.Scanner;

public class Principal {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws Exception {

		int value;
		boolean done = false;

		System.out.println("|||PRACTICA DEL LABORATORIO DE INTELIGENTES|||");
		do {
			System.out.println("\n¿Qué es lo que quieres hacer?" + "\n1. Leer laberinto desde archivo .Json"
					+ "\n2. Generar laberinto aleatorio" + "\n3. Salir del programa");

			do {
				value = Functions.keyboardInt();
			} while (value < 1 || value > 3);

			switch (value) {
			case 1:
				Functions.readLab();
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
