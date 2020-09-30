package practica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

public class LeerJson {
	public static void main(String []args) {
		
		String json = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader("D:/ejemplo.json"));
		
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
		
		System.out.println(json);
		
		Gson gson = new Gson();
		
		Labyrinth lab = gson.fromJson(json, Labyrinth.class);
		
		System.out.println(lab);
	}
}
