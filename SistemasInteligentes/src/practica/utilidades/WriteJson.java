package practica.utilidades;

import com.google.gson.Gson;

import practica.busqueda.Problem;
import practica.creacion.Labyrinth;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

		Gson gson = new Gson();				
		try (FileWriter writer = new FileWriter(file.getAbsolutePath() + ".json")) {
			gson.toJson(lab, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que escribe un archivo .json a partir de un objeto problema y un 
	 * archivo para poder general el problema.
	 * @param prob
	 * @param file
	 */
	
	public static void writeJsonProblem(Problem prob, File file) {

		Gson gson = new Gson();	
		try (FileWriter writer = new FileWriter(file.getAbsolutePath() + ".json")) {
			gson.toJson(prob, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
