package practica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.*;
import javax.imageio.*;

import javax.swing.JPanel;

public class Interface extends JPanel {
	
	private Labyrinth lab;

	   public Interface(Labyrinth lab) {
		this.lab = lab;
	}

	public void paint(Graphics g) {

		      Image img = drawLab(lab);
		      g.drawImage(img, 20,20,this);
		   }

	   
	/*private Image createGrid(int rows, int cols) {
		BufferedImage image = new BufferedImage(1280, 1280, BufferedImage.TYPE_INT_ARGB);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				drawNeighbours(image, i, j);
			}
		}

		deleteNeighbours(image, 0, 0);

		readWriteImage(image);

		return image;
	}*/

	private static Image drawLab(Labyrinth lab) {

		BufferedImage image = new BufferedImage(1280, 1280, BufferedImage.TYPE_INT_ARGB);
		int counter = 0;
		Cell[] cellArray = new Cell[(lab.getRows())*(lab.getCols())];
		cellArray = practica.Funciones.getCellsFromMap(lab.getCells());
		
		for (int i = 0; i < lab.getRows(); i++) {
			System.out.println(i);
			for (int j = 0; j < lab.getCols(); j++) {				
				drawNeighbors(image,cellArray[counter],j,i);
				counter++;
			}
		}
		writeImage(image);
		return image;
	}
	
	private static void writeImage(BufferedImage image) {
		try {
			ImageIO.write(image, "jpg", new File("test.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Image drawNeighbors(BufferedImage image, Cell cell, int row, int col) {
		Graphics g = image.getGraphics();
		boolean [] list = cell.getNeighbors();
		practica.Funciones.readBooleanList(list);
		g.setColor(Color.BLACK);
		//Order - N,E,S,W
		if (list[0] == false) {
			// North Neighbor
			g.drawLine((row*50), (col*50), ((row+1)*50), ((col)*50));
			System.out.println("Linea norte dibujada");
		} 
		if (list [1] == false) {
			// East Neighbor
			g.drawLine(((row+1)*50), (col*50), ((row+1)*50), ((col+1)*50));
			System.out.println("Linea Este dibujada");
		}
		if (list [2] == false) {
			// South Neighbor
			g.drawLine(((row)*50), ((col+1)*50), ((row+1)*50), ((col+1)*50));
			System.out.println("Linea Sur dibujada");
		} 
		if (list [3] == false) {
			// West Neighbor
			g.drawLine((row*50), (col*50), ((row)*50), ((col+1)*50));
			System.out.println("Linea Oeste Dibujada");
		}

		return image;
	}

	private Image deleteNeighbours(BufferedImage image, int row, int col) {
		//a esto hay que pasarle una celda
		Graphics g = image.getGraphics();
		g.setColor(Color.RED);
		// West Neighbor
		g.drawLine((row * 50), (col * 50), ((row) * 50), ((col + 1) * 50));
		// North Neighbor
		g.drawLine((row * 50), (col * 50), ((row + 1) * 50), ((col) * 50));
		// East Neighbor
		g.drawLine(((row + 1) * 50), (col * 50), ((row + 1) * 50), ((col + 1) * 50));
		// South Neighbor
		g.drawLine(((row + 1) * 50), (col + 1 * 50), (row * 50), ((col + 1) * 50));

		return image;
	}
}
