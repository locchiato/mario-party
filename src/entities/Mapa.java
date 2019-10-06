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
	private Casilla[][] tablero;
	private List<Personaje> jugadores = new LinkedList<Personaje>();
	private List<Minijuego> minijuegos = new ArrayList<Minijuego>();
	private int cantidadRondas;
	private Casilla casillaInicio;
	
	// Constructor , aca comienza la partida
	public Mapa(List<Jugador> listaJug,int cantidadRondas) throws FileNotFoundException {
		this.cantidadRondas = cantidadRondas;
		this.dado = new Dado(1, 6);
		for (Jugador jug : listaJug) {
			jugadores.add(new Personaje(jug.getNickName()));
		}
		
		rellenarCasillas();
		//ordenTurnos();
		inicioJuego();
	}

	// Cargar casillas con efectos aleatorios

	public void rellenarCasillas() throws FileNotFoundException {
		String Path = "..\\recursos\\";
		Scanner sc = new Scanner(new File(Path+"tablero.txt"));
 
		//casilla inicio
		int x = sc.nextInt();
		int y = sc.nextInt();
		boolean []dir = new boolean [4];
		for (int i = 0; i < dir.length ; i++) {
			dir[i]= (sc.nextInt() == 1? true : false); 
		}
		this.casillaInicio = new Casilla(x,y,dir);
		this.tablero[x][y] = casillaInicio;
		
		
		//casillas desicion
		int cantidad = sc.nextInt();
		for (int j = 0; j < cantidad ; j++) {
			x = sc.nextInt();
			y = sc.nextInt();
			for (int i = 0; i < dir.length ; i++) {
				dir[i]= (sc.nextInt() == 1? true : false); 
			}
			
			this.tablero[x][y] = new Casilla(x,y,dir);			
		}
		
		//casillas normales
		cantidad = sc.nextInt();
		for (int j = 0; j < cantidad ; j++) {
			x = sc.nextInt();
			y = sc.nextInt();
			for (int i = 0; i < dir.length ; i++) {
				dir[i]= (sc.nextInt() == 1? true : false); 
			}
			
			this.tablero[x][y] = new Casilla(x,y,dir);			
		}
		
		//casillas estrellas
		cantidad = sc.nextInt();
		for (int j = 0; j < cantidad ; j++) {
			x = sc.nextInt();
			y = sc.nextInt();
			for (int i = 0; i < dir.length ; i++) {
				dir[i]= (sc.nextInt() == 1? true : false); 
			}
			
			this.tablero[x][y] = new CasillaGanarEstrella(x,y,dir);			
		}
		
		//casillas Sumar o restar monedas
		cantidad = sc.nextInt();
		for (int j = 0; j < cantidad ; j++) {
			x = sc.nextInt();
			y = sc.nextInt();
			for (int i = 0; i < dir.length ; i++) {
				dir[i]= (sc.nextInt() == 1? true : false); 
			}
			
			this.tablero[x][y] = new CasillaSumarRestarMonedas(x,y,dir,sc.nextInt());			
		}
		
		//casillas paralizado
		cantidad = sc.nextInt();
		for (int j = 0; j < cantidad ; j++) {
			x = sc.nextInt();
			y = sc.nextInt();
			for (int i = 0; i < dir.length ; i++) {
				dir[i]= (sc.nextInt() == 1? true : false); 
			}
			
			this.tablero[x][y] = new CasillaParalizar(x,y,dir,sc.nextInt());			
		}
		
		//conexion con la casilla anterior
		while(sc.hasNext()) {
			x = sc.nextInt();
			y = sc.nextInt();
			int xAnt = sc.nextInt();
			int yAnt = sc.nextInt();
			tablero[x][y].setCasillaAnt(tablero[xAnt][yAnt]);
		}
		
		sc.close();

	}
	
	

	// Aca se ve el orden de cada uno
//	public void ordenTurnos() {
//		
//		List<Integer> turnos = tirarDados();
//		for (Personaje pj : this.jugadores) {
//			turnos.add(pj.getNumJug());
//		}
//	}

	public void inicioJuego() {
		// Aca se van a jugar las rondas hasta ver quien gana
		
		
		// rondas del juego 
		for (int i = 0; i < cantidadRondas; i++) {
			
			for (Personaje personaje : jugadores) {
				iniciaTurno(personaje);
			}
			//turno de cada jugador por ronda
			
			finRonda();
		}
		
		//fin del juego
	}
	
	private void iniciaTurno(Personaje personaje) {
		//usa item?
		//tira el dado
		//avanza
		//efecto de la casilla
		int valorDado = this.dado.tirarDado();
		personaje.avanzar(valorDado, this);
	}

	public void finRonda() {
			// Aca se jugara el minijuego entre los personajes , las recompensas y perdidas
			// se veran
			// segun el minijuego jugado
		
	}
	
	private List<Integer> tirarDados() {
		List<Integer> dados = new ArrayList<>(); 
		for(Personaje personaje : this.jugadores) {
			dados.add(this.dado.tirarDado());
		}
			
		return dados;
	}


	public void siguienteTurno() {

	}


	public Casilla obtenerCasilla(int x, int y) {
		return  this.tablero[x][y];
	}

}