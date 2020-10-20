package practica;

import java.util.ArrayList;

public class Sucesores {
	private ArrayList<Sucesor> sucesores;
	
	public Sucesores (Sucesor sucesor) {
		conseguirSucesorN(sucesor);
	}
	
	public void conseguirSucesorN(Sucesor sucesor) {
		int row = sucesor.getRow();
		int col = sucesor.getCol();
		int rowN = row - 1;
		int colN = col;
		int rowE = row;
		int colE = col + 1;
		int rowS = row + 1;
		int colS = col;
		int rowO = row;
		int colO = col - 1;
		sucesores.add(new Sucesor("N", "(" + rowN + ", " + colN + ")", 1));
		sucesores.add(new Sucesor("E", "(" + rowE + ", " + colE + ")", 1));
		sucesores.add(new Sucesor("S", "(" + rowS + ", " + colS + ")", 1));
		sucesores.add(new Sucesor("O", "(" + rowO + ", " + colO + ")", 1));
	}
}
