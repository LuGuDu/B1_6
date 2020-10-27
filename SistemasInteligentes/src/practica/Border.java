package practica;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Border {
	private Comparator<Node> comparador = new Order();
	private PriorityQueue<Node> frontera = new PriorityQueue<Node>(50, comparador);
	
	public Border () {
		
	}
	public Border (PriorityQueue<Node> frontera) {
		this.frontera = frontera;
	}
	
	public void push(Node nodo) {
		frontera.add(nodo);
	}

	public Node pop() {
		return frontera.poll();
	}
	
	public int size() {
		return frontera.size();
	}


	public class Order implements Comparator<Node> {
		public int compare(Node x, Node y) {
			if (x.getValue() > y.getValue()) {
				return -1;
			}
			else if (x.getValue() < y.getValue()) {
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
