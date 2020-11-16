package practica.busqueda;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.*;

/**

 * Esta clase representa la frontera de nuestro arbol, se 
 * encarga de ordenar los nodos hoja que hay en el segun el valor, la fila y la columna.
 * Tiene los metodos necesarios para sacar y meter los nodos correspondientes, y para obtener y 
 * establecer la frontera
 * 
 * @author David Gonz�lez Berm�dez, Lucas Guti�rrez Dur�n, David Guti�rrez Mariblanca
 * Fecha: 14/11/2020
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
		Collections.sort(frontier,Comparator.comparing(Node::getValue).thenComparing(Node::getRow).thenComparing(Node::getCol).thenComparing(Node::getId));
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