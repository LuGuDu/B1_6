package practica.busqueda;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.*;

/**
 * Esta clase representa la frontier de nuestro arbol, se 
 * encarga de ordenar los nodes hoja que hay en el.
 * Tiene un comparator para la ordenacion, y una cola de prioridad frontier donde
 * se almacenan
 * @author David González Bermúdez, Lucas Gutiérrez Durán, David Gutiérrez Mariblanca
 * Fecha: 28/10/2020
 */

public class Border {
	LinkedList<Node> frontier = new LinkedList<Node>();
	
	
	public Border () {
		
	}
	public Border (LinkedList<Node> frontier) {
		this.frontier = frontier;
	}
	
	public void push(Node node) {
		frontier.offer(node);
		Collections.sort(frontier,Comparator.comparing(Node::getValue).thenComparing(Node::getRow).thenComparing(Node::getCol));
	}

	public Node pop() {
		return frontier.poll();
	}
	
	public int size() {
		return frontier.size();
	}


	public LinkedList<Node> getFrontier() {
		return frontier;
	}
	public void setFrontier(LinkedList<Node> frontier) {
		this.frontier = frontier;
	}
}