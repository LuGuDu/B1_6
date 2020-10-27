package practica;

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
