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
		
		public static BufferedImage drawCells(BufferedImage image, ArrayList<Node> solution) {
			Graphics g = image.getGraphics();
			System.out.println(solution);
			String id = null;
			int row, col;
			g.setColor(Color.RED);
			for(Node n:solution) {
				id = n.getIdState();
				row = Functions.getRow(id);
				col = Functions.getCol(id);
						
				g.drawLine(((row) * 5)+6, ((col) * 5)+6, ((row + 1) * 5)+4, ((col) * 5)+6);
				g.drawLine(((row) * 5)+6, ((col) * 5)+7, ((row + 1) * 5)+4, ((col) * 5)+7);
				g.drawLine(((row) * 5)+6, ((col) * 5)+8, ((row + 1) * 5)+4, ((col) * 5)+8);
				g.drawLine(((row) * 5)+6, ((col) * 5)+9, ((row + 1) * 5)+4, ((col) * 5)+9);					
			}
			
			row = 0;
			col = 0;
			
			g.drawLine(((row) * 5)+6, ((col) * 5)+6, ((row + 1) * 5)+4, ((col) * 5)+6);
			g.drawLine(((row) * 5)+6, ((col) * 5)+7, ((row + 1) * 5)+4, ((col) * 5)+7);
			g.drawLine(((row) * 5)+6, ((col) * 5)+8, ((row + 1) * 5)+4, ((col) * 5)+8);
			g.drawLine(((row) * 5)+6, ((col) * 5)+9, ((row + 1) * 5)+4, ((col) * 5)+9);	
			
			
			return image;
		}
		
		public static void generateFile(BufferedImage imageSolution) {
			
			File test = new File("test.png");
			String path=System.getProperty("user.home")+"/desktop";
			
			boolean seguir = false;
			do {
			try {
				path = path + "/Solution";
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
		
		public static void saveImageSolution(Problem prob, ArrayList<Node> solution) {
			String path;
			BufferedImage imageOriginal, imageSolution;
			
			path = getPath(prob);
			System.out.println(path);
			imageOriginal = getImage(path);
			imageSolution = drawCells(imageOriginal, solution);
			generateFile(imageSolution);
		}
}
