package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import entities.Casilla;
import entities.Dado;
import entities.Jugador;
import entities.Minijuego;
import entities.Personaje;
import ui.MarioJFrame;

public class Mapa {

	private Dado dado;
	private Casilla[][] tablero;
	private List<Personaje> jugadores = new LinkedList<Personaje>();
	private List<Minijuego> minijuegos = new ArrayList<Minijuego>();
	private int cantidadRondas;
	private int estrellasVictoria = 10;
	private Casilla casillaInicio;
	private MarioJFrame jFrame;

	// Constructor , aca comienza la partida
	public Mapa(List<Jugador> listaJug, int cantidadRondas) throws FileNotFoundException {
		this.cantidadRondas = cantidadRondas;
		this.dado = new Dado(1, 6);

		rellenarCasillas();

		for (Jugador jug : listaJug) {
			Personaje p = new Personaje(jug.getNickName());
			p.setCasillaActual(casillaInicio);
			jugadores.add(p);
		}

		ordenTurnos();
		// comentar esta para poder testear los metodos de forma individual
		inicioJuego();
	}

	public void ordenTurnos() {
		Map<Integer, Personaje> turnos = new TreeMap<Integer, Personaje>(Collections.reverseOrder());
		List<Personaje> jugOrd = new LinkedList<Personaje>();
		int tiro;
		for (Personaje pj : this.jugadores) {
			// Tira el dado , si el numero ya fue sacado se repite el tiro
			tiro = dado.tirarDado();
			while (turnos.containsKey(tiro)) {
				tiro = dado.tirarDado();
			}
			turnos.put(tiro, pj);
		}
		for (Entry<Integer, Personaje> pj : turnos.entrySet()) {
			jugOrd.add(pj.getValue());
		}
		
		//imprimir orden
		
		for (Personaje personaje : jugOrd) {
			System.out.println(personaje.getNombre() + " Estrellas: " + personaje.getEstrellas() + " Monedas: "
					+ personaje.getMonedas()+" Estado: " + personaje.getEstado());
		}
		
		
		this.jugadores = jugOrd;
	}

	// Cargar casillas con efectos aleatorios

	public void rellenarCasillas() throws FileNotFoundException {
		//WINDOWS
		//String Path = "recursos\\Tableros\\";
		//MAC
		String Path = "recursos/Tableros/";
		// String Path = "..\\..\\..\\recursos\\Tableros\\";
		Scanner sc = new Scanner(new File(Path + "tablero2.txt"));

		this.tablero = new Casilla[sc.nextInt()][sc.nextInt()];

		// casilla inicio
		int x = sc.nextInt();
		int y = sc.nextInt();
		boolean[] dir = new boolean[4];
		for (int i = 0; i < dir.length; i++) {
			dir[i] = (sc.nextInt() == 1 ? true : false);
		}
		this.casillaInicio = new Casilla(x, y, dir);
		this.tablero[x][y] = casillaInicio;

		// casillas desicion
		int cantidad = sc.nextInt();
		for (int j = 0; j < cantidad; j++) {
			x = sc.nextInt();
			y = sc.nextInt();
			dir = new boolean[4];
			for (int i = 0; i < dir.length; i++) {
				dir[i] = (sc.nextInt() == 1 ? true : false);
			}

			this.tablero[x][y] = new Casilla(x, y, dir);
		}

		// casillas normales
		cantidad = sc.nextInt();
		for (int j = 0; j < cantidad; j++) {
			x = sc.nextInt();
			y = sc.nextInt();
			dir = new boolean[4];
			for (int i = 0; i < dir.length; i++) {
				dir[i] = (sc.nextInt() == 1 ? true : false);
			}

			this.tablero[x][y] = new Casilla(x, y, dir);
		}

		// casillas estrellas
		cantidad = sc.nextInt();
		for (int j = 0; j < cantidad; j++) {
			x = sc.nextInt();
			y = sc.nextInt();
			dir = new boolean[4];
			for (int i = 0; i < dir.length; i++) {
				dir[i] = (sc.nextInt() == 1 ? true : false);
			}

			this.tablero[x][y] = new CasillaGanarEstrella(x, y, dir);
		}

		// casillas Sumar o restar monedas
		cantidad = sc.nextInt();
		for (int j = 0; j < cantidad; j++) {
			x = sc.nextInt();
			y = sc.nextInt();
			dir = new boolean[4];
			for (int i = 0; i < dir.length; i++) {
				dir[i] = (sc.nextInt() == 1 ? true : false);
			}

			this.tablero[x][y] = new CasillaSumarRestarMonedas(x, y, dir, sc.nextInt());
		}

		// casillas paralizado
		cantidad = sc.nextInt();
		for (int j = 0; j < cantidad; j++) {
			x = sc.nextInt();
			y = sc.nextInt();
			dir = new boolean[4];
			for (int i = 0; i < dir.length; i++) {
				dir[i] = (sc.nextInt() == 1 ? true : false);
			}

			this.tablero[x][y] = new CasillaParalizar(x, y, dir, sc.nextInt());
		}

		// conexion con la casilla anterior
		while (sc.hasNext()) {
			x = sc.nextInt();
			y = sc.nextInt();
			int xAnt = sc.nextInt();
			int yAnt = sc.nextInt();
			Casilla c = tablero[x][y];
			Casilla cAnt = tablero[xAnt][yAnt];
			c.setCasillaAnt(cAnt);
		}

		sc.close();

	}

	public void inicioJuego() {
		//Se dibuja la ventana
		
		jFrame = new MarioJFrame(tablero, tablero.length);
		
		// Aca se van a jugar las rondas hasta ver quien gana
		// rondas del juego
		for (int i = 0; i < cantidadRondas; i++) {

			for (Personaje personaje : jugadores) {
				System.out.println();
				System.out.println("TURNO JUGADOR: "+ personaje.getNombre());
				System.out.println();
				this.iniciaTurno(personaje);
			}
			// turno de cada jugador por ronda
			this.finRonda();
			if (this.hayGanador()) {
				break;
			}
		}

		// fin del juego
		this.definirPosiciones();
	}
	
	public void redibujar() {
		jFrame.redibujar(tablero);
		new EsperarParaRedibujarThread().run();
	}

	public void definirPosiciones() {
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("------------ Posiciones---------");
		System.out.println();
		System.out.println();
		
		Collections.sort(jugadores);

		for (Personaje personaje : jugadores) {
			System.out.println(personaje.getNombre() + " Estrellas: " + personaje.getEstrellas() + " Monedas: "
					+ personaje.getMonedas()+" Estado: " + personaje.getEstado());
		}

	}

	public boolean hayGanador() {

		for (Personaje personaje : jugadores) {
			if (personaje.esGanador(estrellasVictoria)) {
				return true;
			}
		}
		return false;
	}

	private void iniciaTurno(Personaje personaje) {
		// usa item?
//		Scanner entrada = new Scanner(System.in);
//		System.out.print("Prefiere usar un item en este turno? : 1 - SI , Otro - NO");
//		int respuesta = entrada.nextInt();
//		if (respuesta == 1) {
//			System.out.println("Eligio usar un item");
//			int item = personaje.elegirItem();
//			personaje.usarItem(item, this.jugadores);
//		} else {
//			System.out.println("No usara un item en este turno");
//		}
//		entrada.close();
		// tira el dado
		System.out.println("El jugador " + personaje.getNombre() + " tira el dado");
		int valorDado = this.dado.tirarDado();
		System.out.println("El jugador " + personaje.getNombre() + " ha sacado " + valorDado);
		//Mostrar Dado
		
		// avanza
		personaje.avanzar(valorDado, this);
	}

	public void finRonda() {
		// Aca se jugara el minijuego entre los personajes , las recompensas y perdidas
		// se veran
		// segun el minijuego jugado

	}

	// Getter y Setter (ver cuales no hacen falta y borrarlos)

	public Casilla obtenerCasilla(int x, int y) {
		return this.tablero[x][y];
	}

	public Dado getDado() {
		return dado;
	}

	public void setDado(Dado dado) {
		this.dado = dado;
	}

	public Casilla[][] getTablero() {
		return tablero;
	}

	public void setTablero(Casilla[][] tablero) {
		this.tablero = tablero;
	}

	public List<Personaje> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Personaje> jugadores) {
		this.jugadores = jugadores;
	}

	public List<Minijuego> getMinijuegos() {
		return minijuegos;
	}

	public void setMinijuegos(List<Minijuego> minijuegos) {
		this.minijuegos = minijuegos;
	}

	public int getCantidadRondas() {
		return cantidadRondas;
	}

	public void setCantidadRondas(int cantidadRondas) {
		this.cantidadRondas = cantidadRondas;
	}

	public Casilla getCasillaInicio() {
		return casillaInicio;
	}

	public void setCasillaInicio(Casilla casillaInicio) {
		this.casillaInicio = casillaInicio;
	}

}