package practica;

import java.util.Arrays;

/**
 * Esta clase es una abstacci�n de cada casilla del laberinto.
 * Tiene valor, vecinos, es decir, celdas con las que est� conectada y un
 * atributo que dice si est� visitado o no.
 * @author David Gonz�lez Berm�dez, Lucas Guti�rrez Dur�n, David Guti�rrez Mariblanca
 * Fecha: 16/10/2020
 */
public class Cell {
	private int value;
	private boolean[] neighbors;
	private boolean visited;
	
	public boolean getVisited() {
		return visited;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public Cell(int value, boolean[] neighbors, boolean visited) {
		this.value = value;
		this.neighbors = neighbors;
		this.visited = visited;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean[] getNeighbors() {
		return neighbors;
	}
	
	public void setNeighbors(boolean[] neighbors) {
		if (neighbors == null) {
			this.neighbors = neighbors;
		} else {
			for (int i = 0; i < neighbors.length - 1; i++) {
				this.neighbors[i] = neighbors[i];
			}
		}

	}

	@Override
	public String toString() {
		return "Cell [value=" + value + ", neighbors=" + Arrays.toString(neighbors) + ", visited=" + visited + "]";
	}

}