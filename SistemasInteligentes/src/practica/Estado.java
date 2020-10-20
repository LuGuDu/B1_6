package practica;

public class Estado {
	private String id;
	private Cell valor;
	
	public Estado () {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Cell getValor() {
		return valor;
	}

	public void setValor(Cell valor) {
		this.valor = valor;
	}
	
	public int getRow() {
		int x = id.indexOf(",");
		int row = Integer.parseInt(id.substring(1, x));
		return row;
	}

	public int getCol() {
		int x = id.indexOf(",");
		int col = Integer.parseInt(id.substring(x + 2, id.length() - 1));
		return col;
	}
	
}
