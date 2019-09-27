package entities;

import java.util.List;

public class Sala {
	
	private String Nombre; 
	private List<Jugador> jugadores;
	private String estado; // En Espera , Activa, Cerrada
	private boolean conexion; //Privada = True - Publica = false
	private String contrace�a;
	
	public Sala(String NombreSala,List<Jugador> jugadores,boolean conexion,String contrace�a) {
		this.jugadores = jugadores;
		this.estado = "Espera";
		this.conexion = conexion;
		this.contrace�a = contrace�a;
	}
	
	public boolean ingresarSalaPrivada(Jugador jugador,String contrace�a) {
		
		if(!this.estado.equals("Cerrada") && this.contrace�a.equals(contrace�a)) {
			this.jugadores.add(jugador);
			return true;
		}
		
		return false;
	}
	
	public boolean ingresarSalaPublica(Jugador jugador,String contrace�a) {
		
		if(!this.estado.equals("Cerrada")) {
			this.jugadores.add(jugador);
			return true;
		}
		
		return false;
	}
	
	public boolean salidaJugador(Jugador jugador) {
		
		return	this.jugadores.remove(jugador);
	}

	
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isConexion() {
		return conexion;
	}

	public void setConexion(boolean conexion) {
		this.conexion = conexion;
	}

	public void setContrace�a(String contrace�a) {
		this.contrace�a = contrace�a;
	}

}
