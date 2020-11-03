package practica.busqueda;

/**
 * Esta clase almacena el sucesor de nuestro nodo.
 * Tiene mov, el id del Estado y el cost.
 * @author David González Bermúdez, Lucas Gutiérrez Durán, David Gutiérrez Mariblanca
 * Fecha: 28/10/2020
 */

public class Sucesor {
	private String mov;
	private String idState;
	private int cost;
	
	public Sucesor () {
		
	}
	
	public Sucesor (String mov, String idState, int cost) {
		this.mov = mov;
		this.idState = idState;
		this.cost = cost;
	}

	public String getIdState() {
		return idState;
	}

	public void setIdState(String idState) {
		this.idState = idState;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
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
		return "Sucesor [mov=" + mov + ", idState=" + idState + ", cost=" + cost + "]";
	}
	
	
}
