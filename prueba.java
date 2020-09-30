import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.*;
import javax.imageio.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class prueba extends JPanel {

   public void paint(Graphics g) {
	  int rows=4;
	  int cols=4;
      Image img = createGrid(rows,cols);
      g.drawImage(img, 20,20,this);
   }

   private Image createGrid(int rows, int cols) {
      BufferedImage image = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);

      for (int i=0; i<rows; i++) {
    	  for (int j=0; j<cols; j++) {
    		  drawNeighbours(image, i, j);
    	  }
      }
      
      deleteNeighbours(image, 0,0);
      
      readWriteImage(image);
      
      return image;
   }
   
   public void readWriteImage(BufferedImage image) {
	   try {
		ImageIO.write(image, "png", new File("C:\\Users\\lugud\\Desktop\\test.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   
   public Image drawNeighbours(BufferedImage image, int row, int col) {
	   
	   Graphics g = image.getGraphics();
	   
	   g.setColor(Color.BLACK);
	   //West Neighbour
	   g.drawLine((row*50), (col*50), ((row)*50), ((col+1)*50));	
	   //North Neighbour
	   g.drawLine((row*50), (col*50), ((row+1)*50), ((col)*50));
	   //East Neighbour
	   g.drawLine(((row+1)*50), (col*50), ((row+1)*50), ((col+1)*50));
	   //South Neighbour
	   g.drawLine(((row+1)*50), (col*50), (row*50), ((col+1)*50));
	   return image;
   }
   
   public Image deleteNeighbours(BufferedImage image, int row, int col) {
	   Graphics g = image.getGraphics();
	   g.setColor(Color.RED);
	   //West Neighbour
	   g.drawLine((row*50), (col*50), ((row)*50), ((col+1)*50));	
	   //North Neighbour
	   g.drawLine((row*50), (col*50), ((row+1)*50), ((col)*50));
	   //East Neighbour
	   g.drawLine(((row+1)*50), (col*50), ((row+1)*50), ((col+1)*50));
	   //South Neighbour
	   g.drawLine(((row)*50), (col+1*50), (row*50), ((col+1)*50));
	   
	   return image;
   }
   
   
   public static void main(String[] args) {
      JFrame frame = new JFrame();
      frame.getContentPane().add(new prueba());

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(1280, 720);
      frame.setVisible(true);
   }
}
