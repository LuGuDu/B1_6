package practica.utilidades;

/**
 * Esta clase contiene la excepcion a la hora de pasarle por teclado un numero
 * negativo o cero
 * 
 * @author David Gonz�lez Berm�dez, Lucas Guti�rrez Dur�n, David Guti�rrez
 *         Mariblanca Fecha: 15/11/2020
 */

public class NegativeIntegerException extends Exception {

	private static final long serialVersionUID = 1L;

	public NegativeIntegerException(){
		super("Error: N�mero negativo o cero");
	}
}
