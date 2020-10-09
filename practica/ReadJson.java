import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

public class ReadJson {
	public static Labyrinth readJsons(String path) {

		String json = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			String line = "";
			while ((line = br.readLine()) != null) {
				json += line;
			}

			br.close();

		} catch (FileNotFoundException FNFE) {
			System.out.println(FNFE.toString());
		} catch (IOException IOE) {
			System.out.println(IOE.toString());
		}

		Gson gson = new Gson();

		Labyrinth lab = gson.fromJson(json, Labyrinth.class);
		return lab;
	}
}
