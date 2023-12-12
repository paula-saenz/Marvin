package model;

public class Conductor {
	public String nombre;
	public String apellidos;
	public String rol;
	public String supervisor;
	public String segundoSupervisor;
	public String mail;
	public String password;
	public String phone;
	
	public Conductor(String nombre, String apellidos, String rol, String supervisor, String segundoSupervisor, String mail, String password, String phone) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.rol = rol;
		this.supervisor = supervisor;
		this.segundoSupervisor = segundoSupervisor;
		this.mail = mail;
		this.password = password;
		this.phone = phone;
	}
	
	


	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getApellidos() {
		return apellidos;
	}




	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}




	public String getRol() {
		return rol;
	}




	public void setRol(String rol) {
		this.rol = rol;
	}




	public String getSupervisor() {
		return supervisor;
	}




	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}




	public String getSegundoSupervisor() {
		return segundoSupervisor;
	}




	public void setSegundoSupervisor(String segundoSupervisor) {
		this.segundoSupervisor = segundoSupervisor;
	}




	public String getMail() {
		return mail;
	}




	public void setMail(String mail) {
		this.mail = mail;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getPhone() {
		return phone;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}




	public String toString() {
    	String salida = "Usuario: ("+nombre+", "+apellidos+", " +rol+ ", " +supervisor+ ", " +segundoSupervisor+ ", " +mail+", "+password+", "+phone+")";
    	return salida;
    }
}
