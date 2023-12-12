package model;

import java.sql.Date;

public class SensorNoBinario {
	public int id;
	public String nombre;
	public int usuarios_id;
	public float nivelhb;
	public float nivelsp02;
	public Date fecha;
	public String tipo_sensor;
	
	public SensorNoBinario(int id, String nombre, int usuarios_id, float nivelhb, float nivelsp02, Date fecha2) {
		this.id = id;
		this.nombre = nombre;
		this.usuarios_id = usuarios_id;
		this.nivelhb = nivelhb;
		this.nivelsp02 = nivelsp02;
		this.fecha = fecha2;
		
	}
}
