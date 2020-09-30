package practica;

public class Principal {
	public static void main(String[] args) {

		Labyrinth lab = null;
		lab = practica.ReadJson.ReadJsons();
		System.out.println(lab);
		practica.WriteJson.writeJson(lab);
	}
}
