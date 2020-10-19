package practica;

public class Nodo {
	private String id;
	private int costo;
	private String idEstado;
	private String idPadre;
	private String accion;
	private int profundidad;
	private int heuristica;
	private int valor;
	
	public Nodo() {
		
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public String getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}

	public String getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(String idPadre) {
		this.idPadre = idPadre;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public int getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}

	public int getHeuristica() {
		return heuristica;
	}

	public void setHeuristica(int heuristica) {
		this.heuristica = heuristica;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
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
	@Override
	public String toString() {
		return "Nodo [costo=" + costo + ", idEstado=" + idEstado + ", idPadre=" + idPadre + ", accion=" + accion
				+ ", profundidad=" + profundidad + ", heuristica=" + heuristica + "]";
	}
	
}
