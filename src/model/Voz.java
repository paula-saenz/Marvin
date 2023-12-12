package model;

public class Voz {
	public final String nombre;
	public final String voz;
	public final String fecha;
	
	public Voz(String nombre, String voz, String fecha) {
		this.nombre = nombre;
		this.voz = voz;
		this.fecha = fecha;
	}
	
	public String toString() {
		String salida = "Grabación: (" +nombre+ ", " +voz+ ", " +fecha+ ")";
    	return salida;
	}
}
