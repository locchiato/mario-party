package entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

import entities.Casilla;
import entities.Dado;
import entities.Jugador;
import entities.Minijuego;
import entities.Personaje;

public class Mapa {

	private Casilla[] tablero; // Por ahora un vector simple para testear
	private List<Personaje> jugadores = new LinkedList<Personaje>();
	private List<Integer> turnos = new LinkedList<Integer>();
	private Dado dado;
	private int turnosPendientes;
	private List<Minijuego> minijuegos = new ArrayList<Minijuego>();

	// Constructor , aca comienza la partida
	public Mapa(int cantCasillas, List<Jugador> listaJug) {
		int i = 0;
		this.dado = new Dado(1, 6);
		for (Jugador jug : listaJug) {
			jugadores.add(new Personaje(jug.getNickName(), i));
			i++;
		}
		this.turnosPendientes = this.jugadores.size();
		rellenarCasillas(cantCasillas);
		ordenTurnos();
		inicioJuego();
	}
	
	// Alguna de las dos va a tener que volar de aca
	public void cargarTablero(entities.Casilla[] tablero) {
		this.tablero = tablero;
	}

	// Cargar casillas con efectos aleatorios
	public void rellenarCasillas(int cant) {
		this.tablero = new Casilla[cant+1];
		for (int i = 0; i < this.tablero.length-1; i++) {
			this.tablero[i] = new Casilla(i, this.dado.tirarDado());
		}
		this.tablero[this.tablero.length] = new Casilla(this.tablero.length, 7);
	}

	// Aca se ve el orden de cada uno
	public void ordenTurnos() {
		for (Personaje pj : this.jugadores) {
			this.turnos.add(pj.getNumJug());
		}
	}

	public void inicioJuego() {
		// Aca se van a jugar las rondas hasta ver quien gana
	}

	public void siguienteTurno() {

	}

	public boolean esFinRonda() {
		return this.turnosPendientes == 0;
	}

	public void finRonda() {
		if (this.esFinRonda()) {
			// Aca se jugara el minijuego entre los personajes , las recompensas y perdidas
			// se veran
			// segun el minijuego jugado
		}
	}

	public static void main(String[] args) {

	}
	
}
