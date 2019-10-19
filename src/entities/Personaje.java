package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Articulo;
import entities.Casilla;

public class Personaje {

	private String nombre;
	private List<Articulo> items;
	private int monedas;
	private int estrellas;
	private String estado;
	private Casilla casillaActual;
	private int casillasExtras;
	private int turnosParalizados;

	// Se le pone un nombre al personaje (nickname)
	public Personaje(String nom) {
		// this.turno = false;
		this.nombre = nom;
		this.monedas = 0;
		this.estrellas = 0;
		this.estado = "Normal";
		// this.numJug = num;
		this.items = new ArrayList<Articulo>();
	}

	// Funciones del personaje

	public void sumarRestarMonedas(int cantMonedas) {
		this.monedas += cantMonedas;
		if (this.monedas < 0) {
			this.monedas = 0;
		}
	}

	public void obtenerEstrella() {
		this.estrellas++;
	}

	public boolean esGanador(int estrellasVictoria) {
		return this.estrellas == estrellasVictoria;
	}

	public boolean hayColision() {
		return casillaActual.getPersonajePosicionado() != null;
	}

	public void paralizado(int num) {
		this.setEstado("Paralizado");
		this.turnosParalizados += num;
	}

	public void curarParalisis(int num) {
		this.turnosParalizados -= num;

		if (this.turnosParalizados <= 0) {
			this.setEstado("Normal");
			this.turnosParalizados = 0;
		}
	}

	public boolean puedeMoverse() {
		return this.estado != "Paralizado";
	}

	public void retroceder(int posiciones) {
		
		casillaActual.desocuparCasilla(this);
		for (int i = 0; i < posiciones; i++) {
			casillaActual = casillaActual.getCasillaAnt();
		}
		
		this.llegarSinEfecto();
	}

	public void avanzar(int posiciones, Mapa mapa) {

		if (puedeMoverse()) {
			casillaActual.desocuparCasilla(this);
			for (int i = 0; i < posiciones; i++) {
				if (this.casillaActual.getcantidadDirecciones() > 1) {
					this.casillaActual = this.casillaActual.decisionSiguiente(mapa);
				} else
					casillaActual = this.casillaActual.casillaSiguiente(mapa);
			}
			this.llegar();
		}
		// no avanza jugador paralizado
	}

	public void llegar() {
		if (hayColision()) {
			Personaje personajePosicionado = casillaActual.getPersonajePosicionado();
			personajePosicionado.retroceder(2);
		}
		casillaActual.setPersonajePosicionado(this);
		casillaActual.aplicarEfecto(this);
	}
	
	public void llegarSinEfecto() {
		if (hayColision()) {
			Personaje personajePosicionado = casillaActual.getPersonajePosicionado();
			personajePosicionado.retroceder(2);
		}
		casillaActual.setPersonajePosicionado(this);
	}
	
	
	//falta testear
	public void recogerItem(Articulo articulo) {
		this.items.add(articulo);
	}

	public Personaje seleccionarPersonaje(List<Personaje> jugadores) {
		int i = 0, num;
		for (Personaje pj : jugadores) {
			System.out.println(i + " : " + pj.nombre);
			i++;
		}
		System.out.println("Elige un personaje :");
		// Eleccion por teclado , nunca a uno mismo
		num = 1;
		return jugadores.get(num);
	}

	public int elegirItem() {
		int indice = 1;
		Scanner entradaEscaner;
		int itemSel;
		if (this.items.size() != 0) {
			for (Articulo art : this.items) {
				System.out.println("Elija un item:");
				System.out.println(indice + "-" + art.getClass().getName());

			}
			entradaEscaner = new Scanner(System.in);
			itemSel = entradaEscaner.nextInt();
			entradaEscaner.close();
			return itemSel - 1;
		}
		return -1;
	}

	public void usarItem(int itemNumber, List<Personaje> jugadores) {
		Personaje eleccion;
		if (this.items.get(itemNumber).getEfecto() == 1) {
			eleccion = this.seleccionarPersonaje(jugadores);
			this.items.get(itemNumber).usarArticulo(eleccion, jugadores);
		} else {
			this.items.get(itemNumber).usarArticulo(this, jugadores);
		}
		this.items.remove(itemNumber);
	}


	// Setters y Getters

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getMonedas() {
		return monedas;
	}

	public void setMonedas(int monedas) {
		this.monedas = monedas;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public Casilla getCasillaActual() {
		return casillaActual;
	}

	public void setCasillaActual(Casilla casillaActual) {
		this.casillaActual = casillaActual;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getTurnosParalizados() {
		return turnosParalizados;
	}

	public int getCasillasExtras() {
		return this.casillasExtras;
	}
	
	public void setCasillasExtras(int cas) {
		this.casillasExtras = cas;
	}
}
