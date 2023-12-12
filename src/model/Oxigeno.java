package model;

public class Oxigeno {
	public final String nombre;
	public final int oxigenoNivel;
	public final String fecha;
	
	public Oxigeno(String nombre, int oxigenoNivel, String fecha) {
		this.nombre = nombre;
		this.oxigenoNivel = oxigenoNivel;
		this.fecha = fecha;
	}
	
	public String toString() {
		String salida = "Oxígeno: (" +nombre+ ", " +oxigenoNivel+ ", " +fecha+ ")";
    	return salida;
	}
}
