package practica.utilidades;

public class NegativeIntegerException extends Exception {

	private static final long serialVersionUID = 1L;

	public NegativeIntegerException(){
		super("Error: Número negativo o cero");
	}
}
