package practica.busqueda;

/**
 * Esta clase almacena el sucesor de nuestro nodo.
 * Tiene mov, el id del Estado y el costo.
 * @author David González Bermúdez, Lucas Gutiérrez Durán, David Gutiérrez Mariblanca
 * Fecha: 28/10/2020
 */

public class Sucesor {
	private String mov;
	private String idEstado;
	private int costo;
	
	public Sucesor () {
		
	}
	
	public Sucesor (String mov, String idEstado, int costo) {
		this.mov = mov;
		this.idEstado = idEstado;
		this.costo = costo;
	}

	public String getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public String getMov() {
		return mov;
	}

	public void setMov(String mov) {
		this.mov = mov;
	}
	
	public int getRow() {
		int x = idEstado.indexOf(",");
		int row = Integer.parseInt(idEstado.substring(1, x));
		return row;
	}

	public int getCol() {
		int x = idEstado.indexOf(",");
		int col = Integer.parseInt(idEstado.substring(x + 2, idEstado.length() - 1));
		return col;
	}

	@Override
	public String toString() {
		return "Sucesor [mov=" + mov + ", idEstado=" + idEstado + ", costo=" + costo + "]";
	}
	
	
}
