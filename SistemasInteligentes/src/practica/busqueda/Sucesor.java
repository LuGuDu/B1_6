package practica.busqueda;

/**
 * Esta clase almacena el sucesor de nuestro nodo.
 * Tiene mov, el id del Estado y el cost.
 * @author David González Bermúdez, Lucas Gutiérrez Durán, David Gutiérrez Mariblanca
 * Fecha: 28/10/2020
 */

public class Sucesor {
	private String mov;
	private String idStatus;
	private int cost;
	
	public Sucesor () {
		
	}
	
	public Sucesor (String mov, String idStatus, int cost) {
		this.mov = mov;
		this.idStatus = idStatus;
		this.cost = cost;
	}

	public String getidStatus() {
		return idStatus;
	}

	public void setidStatus(String idStatus) {
		this.idStatus = idStatus;
	}

	public int getcost() {
		return cost;
	}

	public void setcost(int cost) {
		this.cost = cost;
	}

	public String getMov() {
		return mov;
	}

	public void setMov(String mov) {
		this.mov = mov;
	}

	@Override
	public String toString() {
		return "Sucesor [mov=" + mov + ", idStatus=" + idStatus + ", cost=" + cost + "]";
	}
	
	
}
