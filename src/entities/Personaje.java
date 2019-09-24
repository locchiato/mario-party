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
	
	public boolean mover() {
		return true;
	}
	
	public boolean colision() {
		return false;
	}
	
	public Articulo elegirItem(int itemNumber) {
		if (--itemNumber >= 0 && itemNumber < items.size()) {
			Articulo item = this.items.get(itemNumber);
			if(item != null) this.items.remove(itemNumber);
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
	
	public void obtenerEstrella() {
		this.estrellas++;
	}
	
	public void recogerItem(Articulo articulo) {
		this.items.add(articulo);
	}

}
