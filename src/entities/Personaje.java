package entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Personaje {
	
	private boolean turno;
	private List<Articulo> items;
	private int monedas;
	private int estrellas;
	
	public Personaje() {
		this.turno = false;
		this.items = new LinkedList<Articulo>();
		this.monedas = 0;
		this.estrellas = 0;
	}

	public void esTuTurno() {
		turno = true;
	}
	
	public int tirarDado() {
		int menorValor = 1;
		int mayorValor = 6;
		return menorValor + (new Random()).nextInt() % (mayorValor - menorValor + 1);
	}
	
	public boolean mover() {
		return true;
	}
	
	public boolean colision() {
		return false;
	}
	
	public Articulo elegirItem(int itemNumber) {
		return (--itemNumber >= 0 && itemNumber < items.size()) ? this.items.get(itemNumber) : null;
	}

	public void sumarMonedas(int cantMonedas) {
		this.monedas += cantMonedas;
	}

	public void quitarMonedas(int cantMonedas) {
		this.monedas -= cantMonedas;
	}
	
	public void obtenerEstrella() {
		this.estrellas++;
	}
	
	public void recogerItem(Articulo articulo) {
		this.items.add(articulo);
	}

}
