package model;

public class Usuario {
	public int id;
	public String nombre;
	public String apellidos;
	public String rol_tipo;
	public int supervisor_id;
	public String mail;
	public String password;
	public String phone;
	

	public Usuario(int id, String nombre, String apellidos, String rol_tipo, int supervisor_id, String mail, String password, String phone) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.rol_tipo = rol_tipo;
		this.supervisor_id = supervisor_id;
		this.mail= mail;
		this.password = password;
		this.phone = phone;
	}
	
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}
	
	public String getRolId() {
		return rol_tipo;
	}
	
	public int getSupervisorId() {
		return supervisor_id;
	}
	
	public String getMail() {
		return mail;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public void setRolId(String rol_tipo) {
		this.rol_tipo = rol_tipo;
	}
	
	public void setSupervisorId(int supervisor_id) {
		this.supervisor_id = supervisor_id;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String toString() {
    	String salida = "Usuario: ( "+nombre+", "+apellidos+", "+rol_tipo+", "+supervisor_id+", "+mail+", "+password+", "+phone+")";
    	return salida;
    }
		
	}
