package model;

public class CocheInfo  {
	public final int usuarios_id;
	public final String matricula;
	public final String modelo;
	public final String anio;
	
	public CocheInfo(int usuarios_id, String matricula, String modelo, String anio) {
		this.usuarios_id = usuarios_id;
		this.matricula = matricula;
		this.modelo = modelo;
		this.anio = anio;
	}
	
	
	public String getAnio() {
		return anio;
	}


	public String toString() {
		String salida = "Informción Coche: (" +usuarios_id+ ", " +matricula+ ", " +modelo+ ", " +anio+ ")";
		return salida;
	}
}
