package entities;

import java.util.List;

public abstract class Minijuego {
	private int cantJugadores;
	private boolean inicio;
	
	public abstract void jugar(List<Personaje> jugadores);

}