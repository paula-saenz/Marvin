package model;

public class Mensajeria_Model {

	private String nombreEmitido;
	private String apellidoEmitido;
	private String nombreRecibido;
	private String apellidoRecibido;
	private String mensaje;

	
	public Mensajeria_Model (String nombreRemitenteConst,String apellidoRemitenteConst, String nombreDestinatarioConst,String apellidoDestinatarioConst,String mensajeConst) {

		this.nombreEmitido = nombreRemitenteConst;
		this.apellidoEmitido = apellidoRemitenteConst;
		this.nombreRecibido = nombreDestinatarioConst;
		this.apellidoRecibido = apellidoDestinatarioConst;
		this.mensaje = mensajeConst;
	}
	
	public Mensajeria_Model(String nombreDConst,String apellidoDConst, String mensaje){
		this.nombreEmitido = nombreDConst;
		this.apellidoEmitido = apellidoDConst;
		this.mensaje = mensaje;
	}

	
	public void setNombreEmitido(String nombreRemitente) {
		this.nombreEmitido = nombreRemitente;
	}
	
	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreRecibido = nombreDestinatario;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	

	
	public String getNombreEmitido() {
		return nombreEmitido;
	}
	
	public String getApellidoEmitido() {
		return apellidoEmitido;
	}

	public String getNombreRecibido() {
		return nombreRecibido;
	}

	public String getApellidoRecibido() {
		return apellidoRecibido;
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public String toString() {
		String sRet = "Mensaje: ";
		sRet += "\nEscrito por: " + nombreEmitido;
		sRet += "\nEnviado a: " + nombreRecibido;
		sRet += "\nMensaje: " + mensaje;

		return sRet;
	}


}
