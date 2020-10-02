package practica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

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
	
	public void setCells (Map<String, Cell> cells) {
		this.cells = cells;
	}
	
	@Override
	public String toString() {
		return "Labyrinth [rows=" + rows + ", cols=" + cols + ", max_n=" + max_n + ", mov=" + Arrays.toString(mov)
				+ ", id_mov=" + Arrays.toString(id_mov) + ", cells=" + cells + "]";
	}




}
