package entities;

import java.io.Serializable;

public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String contracenia;
	//private boolean estado; //Para saber si esta logueado. false = offline - true = online.
	

	public Usuario(){

	}
	
//	public Usuario(){
//		this.nombre = "";
//		this.contracenia = "";
//		this.estado = false;
//	}
	
	public Usuario(String nombre, String contra) {
		this.nombre = nombre;
		this.contracenia = contra;
	}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContracenia() {
		return contracenia;
	}

	public void setContracenia(String contracenia) {
		this.contracenia = contracenia;
	}
	
//
//	public boolean isEstado() {
//		return estado;
//	}
//
//	public void setEstado(boolean estado) {
//		this.estado = estado;
//	}

	@Override
	public String toString() {
		return nombre + "|" + contracenia +  "|"; //+ estado;
	}

}
