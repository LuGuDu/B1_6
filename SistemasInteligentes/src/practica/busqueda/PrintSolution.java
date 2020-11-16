package practica.busqueda;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * Esta clase se encarga de guardar en un archivo nuestra solucion con la
 * estrategia correspondiente Tiene como atributo la solucion y la estrategia
 * 
 * @author David González Bermúdez, Lucas Gutiérrez Durán, David Gutiérrez
 *         Mariblanca Fecha: 14/11/2020
 */
public class PrintSolution {

	/**
	 * Nos permite guardar nuestra solucion segun la estrategia correspondiente
	 * 
	 * @param solution
	 * @param strategy
	 * 
	 */
	public static void printSolution(ArrayList<Node> solution, int strategy) {

		String path = System.getProperty("user.home") + "/Desktop";
		Deque<Node> nodesInverse = new ArrayDeque<Node>();
		for (Node n : solution) {
			nodesInverse.push(n);
		}
		String nameStrategy = null;

		switch (strategy) {
		case 1:
			nameStrategy = "Anchura";
			break;
		case 2:
			nameStrategy = "Profundidad";
			break;
		case 3:
			nameStrategy = "CostoUniforme";
			break;
		case 4:
			nameStrategy = "Voraz";
			break;
		case 5:
			nameStrategy = "A";
			break;
		}

		path = path + "/Solucion_" + nameStrategy + ".txt";
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(new File(path)));
			out.flush();
			out.write("[id][cost,state,father_id,action,depth,h,value]");
			out.newLine();

			while (!nodesInverse.isEmpty()) {
				Node n = nodesInverse.pop();
				out.write("[" + n.getId() + "]");

				switch (strategy) {
				case 2:
					out.write("[" + (int) n.getCost() + "," + n.getIdState() + "," + n.getFatherId() + ","
							+ n.getAction() + "," + n.getDepth() + "," + // FALTA IMPRIMIR PADRE
							(int) n.getHeuristic() + "," + n.getValue() + "]");
					out.newLine();
					break;

				default:
					out.write("[" + (int) n.getCost() + "," + n.getIdState() + "," + n.getFatherId() + ","
							+ n.getAction() + "," + n.getDepth() + "," + // FALTA IMPRIMIR PADRE
							(int) n.getHeuristic() + "," + (int) n.getValue() + "]");
					out.newLine();
				}
			}
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
