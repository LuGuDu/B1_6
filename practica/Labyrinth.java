package practica;

import java.util.Arrays;
import java.util.Map;

/**
 * Esta clase Labyrinth tiene un número de filas y de columnas que puede ser distinto
 * un máximo de vecinos que en principio siempre será 4, los movimientos y los id de 
 * movimientos para llegar a celdas subyacentes y por último un Map con las llaves y
 * celdas del laberinto.
 * @author David González Bermúdez, Lucas Gutiérrez Durán, David Gutiérrez Mariblanca
 * Fecha: 16/10/2020
 */
public class Labyrinth {
	private int rows;
	private int cols;
	private int max_n;
	private int[][] mov;
	private String[] id_mov;
	private Map<String, Cell> cells;

	public Labyrinth() {

	}

	public Labyrinth(int rows, int cols, int max_n, int[][] mov, String[] id_mov, Map<String, Cell> cells) {
		this.rows = rows;
		this.cols = cols;
		this.max_n = max_n;
		this.mov = mov;
		this.id_mov = id_mov;
		this.cells = cells;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getMax_n() {
		return max_n;
	}

	public void setMax_n(int max_n) {
		this.max_n = max_n;
	}

	public int[][] getMov() {
		return mov;
	}

	public void setMov(int[][] mov) {
		this.mov = mov;
	}

	public String[] getId_mov() {
		return id_mov;
	}

	public void setId_mov(String[] id_mov) {
		this.id_mov = id_mov;
	}

	public Map<String, Cell> getCells() {
		return cells;
	}

	public void setCells(Map<String, Cell> cells) {
		this.cells = cells;
	}

	@Override
	public String toString() {
		return "Labyrinth [rows=" + rows + ", cols=" + cols + ", max_n=" + max_n + ", mov=" + Arrays.toString(mov)
				+ ", id_mov=" + Arrays.toString(id_mov) + ", cells=" + cells + "]";
	}

}
