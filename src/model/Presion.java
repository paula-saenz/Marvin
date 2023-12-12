package model;

public class Presion {
	public final String nombre;
	public final int presionNivel;
	public final String fecha;
	
	public Presion(String nombre, int presionNivel, String fecha) {
		this.nombre = nombre;
		this.presionNivel = presionNivel;
		this.fecha = fecha;
	}
	
	public String toString() {
		String salida = "Presión: (" +nombre+ ", " +presionNivel+ ", " +fecha+  ")";
    	return salida;
	}
}
