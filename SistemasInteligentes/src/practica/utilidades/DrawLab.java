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
 * @author David Gonz�lez Berm�dez, Lucas Guti�rrez Dur�n, David Guti�rrez Mariblanca
 * Fecha: 16/10/2020
 */
public class DrawLab {

	static Scanner sc = new Scanner(System.in);
	private static int weidth;
	private static int length;

	/**
	 * M�todo principal que va generando el dibujo de todas las celdas y al final llama al
	 * m�todo que genera el archivo, devuelve el objeto BufferedImage image ya que tendr� 
	 * que ser usado por la clase paint
	 * @param lab
	 * @param name
	 * @return
	 */
	public static BufferedImage drawLab(Labyrinth lab, String name) {
		weidth=((lab.getCols() * 5) + 10);
		length=((lab.getRows() * 5) + 10);
		BufferedImage image = new BufferedImage(weidth, length, BufferedImage.TYPE_INT_ARGB);
		int counter = 0;
		Cell[] cellArray = new Cell[(lab.getRows()) * (lab.getCols())];
		cellArray = Functions.getCellsFromMap(lab.getCells());

		for (int i = 0; i < lab.getRows(); i++) {
			for (int j = 0; j < lab.getCols(); j++) {
				drawNeighbors(image, cellArray[counter], i, j);
				drawCell(image, cellArray[counter], i, j);
				counter++;
			}
		}
		generateFile(image, name);
		return image;
	}

	/**
	 * M�todo que dibuja cada uno de los lados de las celdas y lo a�ade a image
	 * @param image
	 * @param cell
	 * @param row
	 * @param col
	 */
	private static void drawNeighbors(BufferedImage image, Cell cell, int row, int col) {
		Graphics g = image.getGraphics();
		boolean[] list = cell.getNeighbors();
		g.setColor(Color.BLACK);
		// Order - N,E,S,W
		if (list[0] == false) {
			g.drawLine((col * 5)+5, (row * 5)+5, ((col + 1) * 5)+5, ((row) * 5)+5); // North Neighbor
		}
		if (list[1] == false) {
			g.drawLine(((col + 1) * 5)+5, (row * 5)+5, ((col + 1) * 5)+5, ((row + 1) * 5)+5); // East Neighbor
		}
		if (list[2] == false) {
			g.drawLine(((col) * 5)+5, ((row + 1) * 5)+5, ((col + 1) * 5)+5, ((row + 1) * 5)+5); // South Neighbor
		}
		if (list[3] == false) {
			g.drawLine((col * 5)+5, (row * 5)+5, ((col) * 5)+5, ((row + 1) * 5)+5); // West Neighbor
		}
	}
	
	private static void drawCell(BufferedImage image, Cell cell, int row, int col) {
		Graphics g = image.getGraphics();
		int value = cell.getValue();
		switch(value) {
		case 0:
			g.setColor(Color.WHITE); 

			g.drawLine(((col) * 5)+6, ((row) * 5)+6, ((col + 1) * 5)+4, ((row) * 5)+6);
			g.drawLine(((col) * 5)+6, ((row) * 5)+7, ((col + 1) * 5)+4, ((row) * 5)+7);
			g.drawLine(((col) * 5)+6, ((row) * 5)+8, ((col + 1) * 5)+4, ((row) * 5)+8);
			g.drawLine(((col) * 5)+6, ((row) * 5)+9, ((col + 1) * 5)+4, ((row) * 5)+9);	
			break;
		case 1:
			Color myBrown = new Color(131,99,35);
			g.setColor(myBrown); //tierra

			g.drawLine(((col) * 5)+6, ((row) * 5)+6, ((col + 1) * 5)+4, ((row) * 5)+6);
			g.drawLine(((col) * 5)+6, ((row) * 5)+7, ((col + 1) * 5)+4, ((row) * 5)+7);
			g.drawLine(((col) * 5)+6, ((row) * 5)+8, ((col + 1) * 5)+4, ((row) * 5)+8);
			g.drawLine(((col) * 5)+6, ((row) * 5)+9, ((col + 1) * 5)+4, ((row) * 5)+9);	
			break;
		case 2:
			Color myGreen = new Color(87,206,116);
			g.setColor(myGreen); //hierba

			g.drawLine(((col) * 5)+6, ((row) * 5)+6, ((col + 1) * 5)+4, ((row) * 5)+6);
			g.drawLine(((col) * 5)+6, ((row) * 5)+7, ((col + 1) * 5)+4, ((row) * 5)+7);
			g.drawLine(((col) * 5)+6, ((row) * 5)+8, ((col + 1) * 5)+4, ((row) * 5)+8);
			g.drawLine(((col) * 5)+6, ((row) * 5)+9, ((col + 1) * 5)+4, ((row) * 5)+9);	
			break;
		case 3:
			Color myBlue = new Color(82,217,254);
			g.setColor(myBlue); //agua

			g.drawLine(((col) * 5)+6, ((row) * 5)+6, ((col + 1) * 5)+4, ((row) * 5)+6);
			g.drawLine(((col) * 5)+6, ((row) * 5)+7, ((col + 1) * 5)+4, ((row) * 5)+7);
			g.drawLine(((col) * 5)+6, ((row) * 5)+8, ((col + 1) * 5)+4, ((row) * 5)+8);
			g.drawLine(((col) * 5)+6, ((row) * 5)+9, ((col + 1) * 5)+4, ((row) * 5)+9);	
			break;
		}	
	}
	
	/**
	 * Este m�todo genera un archivo .jpg con la imagen del laberinto y un archivo .json con el laberinto
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