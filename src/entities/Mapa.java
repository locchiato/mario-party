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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Mapa {

	private Casilla[][] tablero;
	private List<Personaje> jugadores = new LinkedList<Personaje>();
	private List<Integer> turnos = new LinkedList<Integer>();
	private Dado dado;
	private int turnosPendientes;
	private List<Minijuego> minijuegos = new ArrayList<Minijuego>();
	public void cargarTablero(Casilla[][] tablero) {
		this.tablero = tablero;
	}
	
	
	public Mapa( List<Jugador> listaJug) throws FileNotFoundException {
		int i = 0;
		this.dado = new Dado(1, 6);
		for (Jugador jug : listaJug) {
			jugadores.add(new Personaje(jug.getNickName(), i));
			i++;
		}
		this.turnosPendientes = this.jugadores.size();
		rellenarCasillas("tablero.in");
		ordenTurnos();
		inicioJuego();
	}
	
	public void rellenarCasillas(String tArchivo) throws FileNotFoundException {

		Scanner sc = new Scanner(new File(tArchivo));
		int fila;
		int columna;
		int j = 0;
		String linea = sc.nextLine();
		int puntRecor;
		char caracter;

		fila = Integer.parseInt(linea.split(" ")[0]);
		columna = Integer.parseInt(linea.split(" ")[1]);
		this.tablero = new Casilla[fila][columna];

		for (int i = 0; i < fila; i++) {
			linea = sc.nextLine();
			char[] miArray = linea.toCharArray();
			puntRecor = 0;
			j=0;
			while (j < columna) {
				caracter = miArray[puntRecor];

				switch (caracter) {
				case '1':// casilla vacia
					tablero[i][j] = new Casilla(i, j, 1, null);
					break;
				case '2':// casilla sumamonedas
					tablero[i][j] = new Casilla(i, j, 2, "" + miArray[puntRecor + 1]);
					break;
				case '5':// casilla pierdemonedas
					tablero[i][j] = new Casilla(i, j, 5, "" + miArray[puntRecor + 1]);
					break;
				case '7':// casilla union
					tablero[i][j] = new Casilla(i, j, 7, "" + miArray[puntRecor + 1] + miArray[puntRecor + 2]);
					break;
				}

				if (tablero[i][j].getTipoCasilla() == 1)
					puntRecor++;
				else if (tablero[i][j].getTipoCasilla() != 7)
					puntRecor += 2;
				else
					puntRecor += 3;

				j++;
			}

		}
		sc.close();

	}
	
	public void ordenTurnos() {
		for (Personaje pj : this.jugadores) {
			this.turnos.add(pj.getNumJug());
		}
	}
	
	public void inicioJuego() {
		// Aca se van a jugar las rondas hasta ver quien gana
	}
	
	
}

