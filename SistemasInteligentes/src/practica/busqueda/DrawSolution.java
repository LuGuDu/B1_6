package practica.busqueda;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import practica.utilidades.DrawLab;
import practica.utilidades.Functions;
import practica.utilidades.Constants;

public class DrawSolution {
		
		public static  String getPath(Problem prob) {
						
			String path = prob.getMaze();			
			DrawLab.drawLab(prob.getLab(), path);	
			path = System.getProperty("user.home") + "/desktop/" + path +".jpg";
			return path;
		}
		
		public static BufferedImage getImage(String path) {
			BufferedImage image = null;
			
			try {
				image = ImageIO.read(new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			File file = new File(path);
			file.delete();
			
			return image;
		}
		
		public static BufferedImage drawCells(BufferedImage image, ArrayList<Node> solution, ArrayList<String> visited, Border border) {
			Graphics g = image.getGraphics();
			String id = null;
			int tcell = Constants.TCELL;
			
			//Dibujado de los nodos de la frontera
			g.setColor(Color.BLUE);
			while(!border.getFrontier().isEmpty()) { 				
				Node n = border.pop();
				id = n.getIdState();			
				fillCell(g, Functions.getCol(id), Functions.getRow(id), tcell);
			}
			
			//Dibujado de los nodos visitados
			g.setColor(Color.GREEN);		
			for(String s:visited) {
				fillCell(g, Functions.getCol(s), Functions.getRow(s), tcell);		
			}
			
			//Dibujado del conjunto de nodos solucion
			g.setColor(Color.RED);			
			for(Node n:solution) {
				id = n.getIdState();				
				fillCell(g, Functions.getCol(id), Functions.getRow(id), tcell);

			}
			return image;
		}
		
		public static void fillCell(Graphics g, int col, int row, int tcell) {
			
			for(int i=tcell+1; i<(tcell*2); i++) {
				g.drawLine((col * tcell)+(tcell+1), (row * tcell)+(i), ((col + 1) * tcell)+(tcell-1), ((row) * tcell)+(i));
			}	
		}
		
		public static void generateFile(BufferedImage imageSolution, int strategy) {
			
			File test = new File("test.png");
			String path=System.getProperty("user.home")+"/desktop";
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
			
			boolean seguir = false;
			do {
			try {
				path = path + "/Solucion_" + nameStrategy;
				BufferedImage image = (BufferedImage) imageSolution;
				ImageIO.write(image, "png", test);

				BufferedImage pngBuffer = ImageIO.read(test);
				BufferedImage jpgBuffer = new BufferedImage(pngBuffer.getWidth(), pngBuffer.getHeight(),
						BufferedImage.TYPE_INT_RGB);
				jpgBuffer.createGraphics().drawImage(pngBuffer, 0, 0, Color.WHITE, null);
				ImageIO.write(jpgBuffer, "jpg", new File(path + ".jpg"));
				seguir = true;
				test.delete();
			}
			catch (IOException e) {
				System.out.println("Error: Nombre no valido para su archivo");
			}
			}while (!seguir);
		}
		
		public static void saveImageSolution(Problem prob, ArrayList<Node> solution, int strategy, ArrayList<String> visited, Border border) {
			String path;
			BufferedImage imageOriginal, imageSolution;
			
			path = getPath(prob);
			imageOriginal = getImage(path);
			imageSolution = drawCells(imageOriginal, solution, visited, border);
			generateFile(imageSolution,strategy);
		}
}
