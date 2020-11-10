package practica.busqueda;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PrintSolution {

	public static void printSolution(ArrayList<Node> solution, int strategy) {
		
		String path = System.getProperty("user.home")+"/Desktop";		
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
		
		path=path+"/Solucion_"+nameStrategy+".txt";
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(new File(path)));
			out.write("[id][cost,state,father_id,action,depth,h,value]");
			for (Node n: solution) {
				out.write("["+n.getId()+"]");
				out.write("["+n.getCost()+","+n.getIdState()+","+n.getFather()+","+n.getAction()+","+n.getDepth()+","+
				n.getHeuristic()+","+n.getValue()+"]");
				out.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
}
