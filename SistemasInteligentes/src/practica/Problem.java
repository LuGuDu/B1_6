package practica;

/**
 * Esta clase define el problema en el que queremos ir desde una celda inicial a una objetivo.
 * Tiene como atributos el inicio, el final, y el laberinto a usar
 * @author David González Bermúdez, Lucas Gutiérrez Durán, David Gutiérrez Mariblanca
 * Fecha: 28/10/2020
 */

public class Problem {
	private String initial;
	private String objective;
	private String maze;
	
	public Problem () {
		
	}
	
	public Problem (String initial, String objective, String maze) {
		this.initial = initial;
		this.objective = objective;
		this.maze = maze;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public String getMaze() {
		return maze;
	}

	public void setMaze(String maze) {
		this.maze = maze;
	}

	@Override
	public String toString() {
		return "Problema [initial=" + initial + ", objective=" + objective + ", maze=" + maze + "]";
	}
	
}
