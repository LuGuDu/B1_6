package practica.utilidades;

public class NoValidNumberException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoValidNumberException(){
		super("Error: Opcion no valida");
	}
}
