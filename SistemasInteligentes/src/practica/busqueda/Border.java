package practica.busqueda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Esta clase representa la borderQueue de nuestro arbol, se 
 * encarga de ordenar los nodes hoja que hay en el.
 * Tiene un comparator para la ordenacion, y una cola de prioridad borderQueue donde
 * se almacenan
 * @author David González Bermúdez, Lucas Gutiérrez Durán, David Gutiérrez Mariblanca
 * Fecha: 28/10/2020
 * 
 */

public class Border {
	private Comparator<Node> comparatorNode = new Order();
	private PriorityQueue<Node> borderQueue = new PriorityQueue<Node>(50, comparatorNode);
	
	public Border () {
		
	}
	public Border (PriorityQueue<Node> borderQueue) {
		this.borderQueue = borderQueue;
	}
	
	public void push(Node node) {
		borderQueue.add(node);
	}

	public Node pop() {
		return borderQueue.poll();
	}
	
	public int size() {
		return borderQueue.size();
	}


	public class Order implements Comparator<Node> {
		public int compare(Node x, Node y) {
			if (x.getValue() < y.getValue()) {
				return -1;
			}
			else if (x.getValue() > y.getValue()) {
				return 1;
			}
			else {
				if (x.getRow() < y.getRow()) {
					return -1;
				}
				else if (x.getRow() > y.getRow()) {
					return 1;
				}
				else {
					if (x.getCol() < y.getCol()) {
						return -1;
					}
					else if (x.getCol() > y.getCol()) {
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
