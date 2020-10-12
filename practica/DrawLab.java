import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.*;
import javax.imageio.*;

import java.util.Scanner;

public class DrawLab {

	static Scanner sc = new Scanner(System.in);
	private Labyrinth lab;
	private String name;

	public void paint(Graphics g) {
		Image img = drawLab(lab, name);
		g.drawImage(img, 20, 55, (ImageObserver) this);
	}

	static BufferedImage drawLab(Labyrinth lab, String name) {
		BufferedImage image = new BufferedImage(((lab.getCols() * 50) + 1), ((lab.getRows() * 50) + 1),
				BufferedImage.TYPE_INT_ARGB);
		int counter = 0;
		Cell[] cellArray = new Cell[(lab.getRows()) * (lab.getCols())];
		cellArray = Functions.getCellsFromMap(lab.getCells());

		for (int i = 0; i < lab.getRows(); i++) {
			for (int j = 0; j < lab.getCols(); j++) {
				drawNeighbors(image, cellArray[counter], j, i);
				counter++;
			}
		}
		writeImage(image, name);
		return image;
	}

	private static Image drawNeighbors(BufferedImage image, Cell cell, int row, int col) {
		Graphics g = image.getGraphics();
		boolean[] list = cell.getNeighbors();
		g.setColor(Color.BLACK);
		// Order - N,E,S,W
		if (list[0] == false) {
			g.drawLine((row * 50), (col * 50), ((row + 1) * 50), ((col) * 50)); // North Neighbor
		}
		if (list[1] == false) {
			g.drawLine(((row + 1) * 50), (col * 50), ((row + 1) * 50), ((col + 1) * 50)); // East Neighbor
		}
		if (list[2] == false) {
			g.drawLine(((row) * 50), ((col + 1) * 50), ((row + 1) * 50), ((col + 1) * 50)); // South Neighbor
		}
		if (list[3] == false) {
			g.drawLine((row * 50), (col * 50), ((row) * 50), ((col + 1) * 50)); // West Neighbor
		}

		return image;
	}

	private static void writeImage(BufferedImage image, String name) {
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
