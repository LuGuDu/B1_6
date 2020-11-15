package practica.utilidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.*;
import javax.imageio.*;

import practica.creacion.Cell;
import practica.creacion.Labyrinth;

import java.util.Scanner;
/**
 * Clase encargada de dibujar un laberinto a partir de un objeto Labyrinth y generar el archivo
 * correspondiente con esa imagen
 * @author David González Bermúdez, Lucas Gutiérrez Durán, David Gutiérrez Mariblanca
 * Fecha: 16/10/2020
 */
public class DrawLab {

	static Scanner sc = new Scanner(System.in);
	private static int weidth;
	private static int length;
	private static int tcell;
	/**
	 * Método principal que va generando el dibujo de todas las celdas y al final llama al
	 * método que genera el archivo, devuelve el objeto BufferedImage image ya que tendrá 
	 * que ser usado por la clase paint
	 * @param lab
	 * @param name
	 * @return
	 */
	public static BufferedImage drawLab(Labyrinth lab, String name) {
    
		tcell = Constants.TCELL;
		weidth=((lab.getCols() * tcell) + (tcell*2));
		length=((lab.getRows() * tcell) + (tcell*2));
		

		BufferedImage image = new BufferedImage(weidth, length, BufferedImage.TYPE_INT_ARGB);
		int counter = 0;
		Cell[] cellArray = new Cell[(lab.getRows()) * (lab.getCols())];
		cellArray = Functions.getCellsFromMap(lab.getCells());

		for (int i = 0; i < lab.getRows(); i++) {
			for (int j = 0; j < lab.getCols(); j++) {
				drawNeighbors(image, cellArray[counter], i, j, tcell);
				drawCell(image, cellArray[counter], i, j, tcell);
				counter++;
			}
		}
		generateFile(image, name);
		return image;
	}

	/**
	 * Método que dibuja cada uno de los lados de las celdas y lo añade a image
	 * @param image
	 * @param cell
	 * @param row
	 * @param col
	 */
	private static void drawNeighbors(BufferedImage image, Cell cell, int row, int col, int tcell) {
		Graphics g = image.getGraphics();
		//Graphics g = image.getGraphics();
		boolean[] list = cell.getNeighbors();
		g.setColor(Color.BLACK);
		// Order - N,E,S,W
		if (list[0] == false) {
			g.drawLine((col * tcell)+tcell, (row * tcell)+tcell, ((col + 1) * tcell)+tcell, ((row) * tcell)+tcell); // North Neighbor
		}
		if (list[1] == false) {
			g.drawLine(((col + 1) * tcell)+tcell, (row * tcell)+tcell, ((col + 1) * tcell)+tcell, ((row + 1) * tcell)+tcell); // East Neighbor
		}
		if (list[2] == false) {
			g.drawLine(((col) * tcell)+tcell, ((row + 1) * tcell)+tcell, ((col + 1) * tcell)+tcell, ((row + 1) * tcell)+tcell); // South Neighbor
		}
		if (list[3] == false) {
			g.drawLine((col * tcell)+tcell, (row * tcell)+tcell, ((col) * tcell)+tcell, ((row + 1) * tcell)+tcell); // West Neighbor
		}
	}
	
	/**
	 * Método que elige el color que tiene que tener la celda segun el valor que contiene
	 * @param image
	 * @param cell
	 * @param row
	 * @param col
	 */
	
	private static void drawCell(BufferedImage image, Cell cell, int row, int col, int tcell) {
		Graphics g = image.getGraphics();
		int value = cell.getValue();
		switch(value) {
		case 0:
			g.setColor(Color.WHITE); 
			break;
		case 1:
			Color myBrown = new Color(191,168,123);
			g.setColor(myBrown); //tierra
			break;
		case 2:
			Color myGreen = new Color(196,249,159);
			g.setColor(myGreen); //hierba
			break;
		case 3:
			Color myBlue = new Color(159,244,249);
			g.setColor(myBlue); //agua
			break;
		}	
		
		for(int i=tcell+1; i<(tcell*2); i++) {
			g.drawLine((col * tcell)+(tcell+2), (row * tcell)+(i), ((col + 1) * tcell)+(tcell-2), ((row) * tcell)+(i));
		}

	}
	
	/**
	 * Este método genera un archivo .jpg con la imagen del laberinto y un archivo .json con el laberinto
	 * en formato de texto.
	 * @param image
	 * @param name
	 */
	public static void generateFile(BufferedImage image, String name) {
		File test = new File("test.png");
		String path=System.getProperty("user.home")+"/desktop";
		
		boolean seguir = false;
		do {
		try {
			path = path + "/" + name;
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
	
}