package model;

public class Tecnico {
	public final String nombre;
	public final String usuario;
	public final String rol;
	public final String segundoUsuario;
	public final String mail;
	public final String password;
	
	public Tecnico(String nombre, String usuario, String rol, String segundoUsuario, String mail, String password) {
		this.nombre = nombre;
		this.usuario = usuario;
		this.rol = rol;
		this.segundoUsuario = segundoUsuario;
		this.mail = mail;
		this.password = password;
	}
	
	public String toString() {
    	String salida = "Usuario: ("+nombre+ ", " +rol+ ", "+usuario+ ", " +segundoUsuario+ ", " +mail+ ", " +password+ ")";
    	return salida;
    }
}
