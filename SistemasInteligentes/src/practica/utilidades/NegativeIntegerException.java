package practica.utilidades;

public class NegativeIntegerException extends Exception {
	public NegativeIntegerException(){
		super("Error: Número negativo o cero");
	}
}
