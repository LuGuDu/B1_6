package practica.utilidades;

/**
 * Esta clase contiene la excepcion a la hora de elegir una opcion que
 * no es valida debido a que no existe
 * 
 * @author David Gonz�lez Berm�dez, Lucas Guti�rrez Dur�n, David Guti�rrez
 *         Mariblanca Fecha: 15/11/2020
 */

public class NoValidNumberException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoValidNumberException(){
		super("Error: Opcion no valida");
	}
}
