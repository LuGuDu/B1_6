package practica;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Frontera {
	private Comparator<Nodo> comparador = new Ordenar();
	private PriorityQueue<Nodo> frontera = new PriorityQueue<Nodo>(50, comparador);
	
	public Frontera(PriorityQueue<Nodo> frontera) {
		this.frontera = frontera;
	}
	
	public class Ordenar implements Comparator<Nodo> {
		public int compare(Nodo x, Nodo y) {
			if (x.getValor() > y.getValor()) {
				return -1;
			}
			else if (x.getValor() < y.getValor()) {
				return 1;
			}
			else {
				if (x.getRow() > y.getRow()) {
					return -1;
				}
				else if (x.getRow() < y.getRow()) {
					return 1;
				}
				else {
					if (x.getCol() > y.getCol()) {
						return -1;
					}
					else if (x.getCol() < y.getCol()) {
						return 1;
					}
					else {
						return 0;
					}
				}
			}
		}
	}
}
