import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;

public class ReadJson {

	static Scanner sc = new Scanner(System.in);

	public static Labyrinth readJsons() {

		String json = "";
		String path;
		boolean seguir = false;
		do {
			try {
				path = sc.next();
				BufferedReader br = new BufferedReader(new FileReader(path));
				seguir = true;
				String line = "";
				while ((line = br.readLine()) != null) {
					json += line;
				}

				br.close();

			} catch (FileNotFoundException FNFE) {
				System.out.println("Error: archivo no encontrado");
				System.out.println("Vuelva a intentar introducir la ruta: ");
			} catch (IOException IOE) {
				System.out.println(IOE.toString());
			}
		} while (!seguir);

		Gson gson = new Gson();

		Labyrinth lab = gson.fromJson(json, Labyrinth.class);
		return lab;
	}
}
