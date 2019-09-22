package entities;

import java.util.List;
import javax.swing.Timer;

public class Juego {
	
	private Mapa mapa;
	private List<Personaje> jugadores;
	private Timer tiempo;
	
	public void cargarMapa(Mapa mapa) {
		this.mapa = mapa;
	}
	
	public void cargarJugadores(List<Personaje> jugadores) {
		this.jugadores = jugadores;
	}
	
	public void iniciarJuego() {
		tiempo.restart();
	}

	public void pausarJuego() {
		tiempo.stop();
	}
	
	public void reanudarJuego() {
		tiempo.start();
	}

	public void detenerJuego() {
		tiempo.stop();
	}
}
