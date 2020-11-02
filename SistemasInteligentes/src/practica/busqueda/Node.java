package practica.busqueda;

/**
 * Esta clase es un nodo que representa la celda que se va recorriendo y sus atributos en un arbol.
 * Tiene id Nodo, coste, id estado, id del padre (es decir del nodo primero), accion, profundidad del nodo,
 * heuristica, y el valor final
 * @author David González Bermúdez, Lucas Gutiérrez Durán, David Gutiérrez Mariblanca
 * Fecha: 28/10/2020
 */

public class Node {
	private int id;
	private double cost;
	private String idState;
	private int idFather;
	private String action;
	private int depth;
	private double heuristic;
	private double value;
	
	public Node() {
		
	}
	
	public Node (int id, double cost, String idState, int idFather, String action, 
			int depth, double heuristic, double value) {
		this.id = id;
		this.cost = cost;
		this.idState = idState;
		this.idFather = idFather;
		this.action = action;
		this.depth = depth;
		this.heuristic = heuristic;
		this.value = value;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getIdState() {
		return idState;
	}

	public void setIdState(String idState) {
		this.idState = idState;
	}

	public int getIdFather() {
		return idFather;
	}

	public void setIdFather(int idFather) {
		this.idFather = idFather;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public double getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(double heuristic) {
		this.heuristic = heuristic;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getRow() {
		int x = idState.indexOf(",");
		int row = Integer.parseInt(idState.substring(1, x));
		return row;
	}

	public int getCol() {
		int x = idState.indexOf(",");
		int col = Integer.parseInt(idState.substring(x + 2, idState.length() - 1));
		return col;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", cost=" + cost + ", idState=" + idState + ", idFather=" + idFather + ", action="
				+ action + ", depth=" + depth + ", heuristic=" + heuristic + ", value=" + value + "]";
	}
	
}
