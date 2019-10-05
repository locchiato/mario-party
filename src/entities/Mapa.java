package entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import entities.Casilla;
import entities.Dado;
import entities.Jugador;
import entities.Minijuego;
import entities.Personaje;

public class Mapa {

	private Dado dado;
	private Casilla[][] tablero; // Por ahora un vector simple para testear
	private List<Personaje> jugadores = new LinkedList<Personaje>();

	private List<Minijuego> minijuegos = new ArrayList<Minijuego>();
	private int turnosPendientes;
	
	// Constructor , aca comienza la partida

	public Mapa(List<Jugador> listaJug) throws FileNotFoundException {
		int i = 0;
		this.dado = new Dado(1, 6);
		for (Jugador jug : listaJug) {
			jugadores.add(new Personaje(jug.getNickName(), i));
			i++;
		}
		this.turnosPendientes = this.jugadores.size();
		rellenarCasillas();
		ordenTurnos();
		inicioJuego();
	}

	// Cargar casillas con efectos aleatorios

	public void rellenarCasillas() throws FileNotFoundException {
		String Path = "C:\\Desktop\\Programacion Avanzada\\Mario Party\\mario-party\\";
		Scanner sc = new Scanner(new File(Path+"tablero.in"));

		int x, y, tipoCasilla,xSig,ySig,xAnt,yAnt,xAlter,yAlter;

		while (sc.hasNext()) {
			x = sc.nextInt();
			y = sc.nextInt();
			tipoCasilla = sc.nextInt();
			tablero[x][y] = new Casilla(x, y, tipoCasilla);
		}
		
		sc = new Scanner(new File("casillasDesicion.in"));
		while (sc.hasNext()) {
			x = sc.nextInt();
			y = sc.nextInt();
			tipoCasilla = sc.nextInt();
			xSig=sc.nextInt();
			ySig=sc.nextInt();
			xAnt=sc.nextInt();
			yAnt=sc.nextInt();
			xAlter=sc.nextInt();
			yAlter=sc.nextInt();
			
			tablero[x][y] = new Casilla(x, y, tipoCasilla);
		}
		sc.close();

	}

	// Aca se ve el orden de cada uno
	public void ordenTurnos() {
		List<Integer> turnos = tirarDados();
		for (Personaje pj : this.jugadores) {
			turnos.add(pj.getNumJug());
		}
	}

	private List<Integer> tirarDados() {
		List<Integer> dados = new ArrayList<>(); 
		for(Personaje personaje : this.jugadores) {
			dados.add(this.dado.tirarDado());
		}
			
		return dados;
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

}