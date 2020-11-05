package practica.utilidades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import practica.busqueda.Problem;
import practica.creacion.Labyrinth;

import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
/**
 * Esta clase 
 * @author David González Bermúdez, Lucas Gutiérrez Durán, David Gutiérrez Mariblanca
 * Fecha: 16/10/2020
 */
public class WriteJson {
	/**
	 * Método que escribe un archivo .json a partir de un objeto Labyrinth y un archivo.
	 * @param lab
	 * @param file
	 */
	public static void writeJsonLab(Labyrinth lab, File file) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();				
		try (FileWriter writer = new FileWriter(file.getAbsolutePath() + ".json")) {
			gson.toJson(lab, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que escribe un archivo .json a partir de un objeto Labyrinth y un nombre de
	 * archivo para poder general el problema.
	 * @param prob
	 * @param name
	 */
	
	public static void writeJsonProblem(Problem prob, String name) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// Por defecto mandamos los archivos al escritorio
		String path=System.getProperty("user.home")+"/desktop";
		path = path + "/" +name;
		
		try (FileWriter writer = new FileWriter(path + ".json")) {
			gson.toJson(prob, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
