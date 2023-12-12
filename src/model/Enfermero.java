package model;

public class Enfermero {
	public final String nombre;
	public final String rol;
	public final String mail;
	public final String password;
	
	public Enfermero(String nombre, String rol, String mail, String password) {
		this.nombre = nombre;
		this.rol = rol;
		this.mail = mail;
		this.password = password;
	}
	
	public String toString() {
    	String salida = "Usuario: ("+nombre+", " +rol+ ", " +mail+ ", " +password+ ")";
    	return salida;
    }
}
