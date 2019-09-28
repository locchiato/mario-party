package entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entities.Articulo;
import entities.Casilla;

public class Personaje {

	private String nombre;
	private boolean turno;
	private List<Articulo> items;
	private int monedas;
	private int estrellas;
	private Casilla casillaActual;
	private String estado;
	private int numJug;
	private int turnosParalizados;

	// Se le pone un nombre al personaje (nickname)
	// Se le pone numero de personaje (posicion)
	public Personaje(String nom, int num) {
		this.turno = false;
		this.nombre = nom;
		this.monedas = 0;
		this.estrellas = 0;
		this.estado = "Vivo";
		this.numJug = num;
		this.items = new ArrayList<Articulo>();
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

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
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

	public int getNumJug() {
		return numJug;
	}

	public void setNumJug(int numJug) {
		this.numJug = numJug;
	}

	// Funciones del personaje

	public boolean puedeMoverse() {
		if (estado == "Vivo") {
			return true;
		} else if (estado == "Paralizado" && turnosParalizados == 0) {
			estado = "Vivo";
			return true;
		}
		turnosParalizados--;
		return false;
	}

	public void esTuTurno() {
		turno = true;
	}

	public void avanzar(int posiciones) {
		
		if(puedeMoverse()) {
			for (int i = 0; i < posiciones; i++) {
				
				if(casillaActual.getTipoCasilla() == 7 ) { //7 valor casilla tipo decision
					casillaActual = casillaActual.aplicarEfecto(this); //preguntar 
				}	
				else {
					casillaActual= casillaActual.casillaSig;
				}
			}
		}
	}

	public void Retroceder(int posiciones) {
		for (int i = 0; i < posiciones; i++) {
				casillaActual= casillaActual.casillaAnt;
		}
	}

	public boolean colision() {
		return false;
	}

	public Personaje seleccionarPersonaje(LinkedList<Personaje> lista) {
		int i = 0, num;
		for (Personaje pj : lista) {
			System.out.println(i + " : " + pj.nombre);
			i++;
		}
		System.out.println("Elige un personaje :");
		// Eleccion por teclado
		num = 1;
		// Segun efecto selecciona a otro jugador o a uno mismo
		return lista.get(num);
	}

	public Articulo elegirItem(int itemNumber) {
		if (--itemNumber >= 0 && itemNumber < items.size()) {
			Articulo item = this.items.get(itemNumber);
			if (item != null)
				this.items.remove(itemNumber);
			return item;
		}
		return null;
	}

	public void sumarMonedas(int cantMonedas) {
		this.monedas += cantMonedas;
	}

	public void quitarMonedas(int cantMonedas) {
		this.monedas -= cantMonedas;
	}

	public void paralizado() {
		this.setEstado("Paralizado");
		this.turnosParalizados = 2;
	}

	public void obtenerEstrella() {
		this.estrellas++;
	}

	public void recogerItem(Articulo articulo) {
		this.items.add(articulo);
	}

	public boolean esGanador() {
		if (this.estrellas == 5) {
			return true;
		} else if (this.casillaActual.getTipoCasilla() == 7) {
			return true;
		}
		return false;
	}

}
