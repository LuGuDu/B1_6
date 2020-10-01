package practica;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

public class Principal {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Grupo B1-6 -- Practica de Laboratorio");
		Labyrinth lab = null;

		lab = practica.ReadJson.ReadJsons();		
		System.out.println(lab);		
		//practica.Interface.drawLab(lab);
		practica.WriteJson.writeJson(lab);

		frame.getContentPane().add(new Interface(lab));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		frame.setVisible(true);
	}
}
