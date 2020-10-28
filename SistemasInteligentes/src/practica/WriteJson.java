package practica;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
/**
 * Esta clase 
 * @author David González Bermúdez, Lucas Gutiérrez Durán, David Gutiérrez Mariblanca
 * Fecha: 16/10/2020
 */
public class WriteJson {
	/**
	 * Método que escribe un archivo .json a partir de un objeto Labyrinth y un nombre de
	 * archivo.
	 * @param lab
	 * @param name
	 */
	public static void writeJsonLab(Labyrinth lab, String name) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// Por defecto mandamos los archivos al escritorio
		String path=System.getProperty("user.home")+"/desktop";
		path = path + "/" +name;
		
		try (FileWriter writer = new FileWriter(path + ".json")) {
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
