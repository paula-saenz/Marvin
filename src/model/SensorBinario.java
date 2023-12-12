package model;

import java.sql.Date;

public class SensorBinario {
	public int id;
	public String nombre;
	public int usuarios_id;
	public String voz;
	public Date fecha;
	
	public SensorBinario(int id, String nombre, int usuarios_id, String voz, Date fecha) {
		this.id = id;
		this.nombre = nombre;
		this.usuarios_id = usuarios_id;
		this.voz = voz;
		this.fecha = fecha;
	}
}
