package model;

public class RevisionInfo {
	public final String nombre;
	public final String matricula;
	public final String modelo;
	public final String kilometraje;
	
	public RevisionInfo(String nombre, String matricula, String modelo, String kilometraje) {
		this.nombre = nombre;
		this.matricula = matricula;
		this.modelo = modelo;
		this.kilometraje = kilometraje;
	}
	
	
	public String getKilometraje() {
		return kilometraje;
	}


	public String toString() {
		String salida = "Informción Coche: (" +nombre+ ", " +matricula+ ", " +modelo+ ", " +kilometraje+ ")";
		return salida;
	}
}
