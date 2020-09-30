package practica;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WriteJson {

    public static void writeJson(Labyrinth lab) {

        // pretty print
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Java objects to String
        String json = gson.toJson(lab);

        //System.out.println(json);

        // Java objects to File
        try (FileWriter writer = new FileWriter("D:/ejemplo2.json")) {
            gson.toJson(lab, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}