import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class WriteJson {

	public static void writeJson(Labyrinth lab, String name) {

		// pretty print
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String path=System.getProperty("user.home")+"/desktop";
		path = path + "/" +name;
		// Java objects to File
		try (FileWriter writer = new FileWriter(path + ".json")) {
			gson.toJson(lab, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
