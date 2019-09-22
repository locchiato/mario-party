package entities;

import java.util.Random;

public class Personaje {
	
	private boolean turno;
	private Articulo[] items;
	private int monedas;
	private int estrellas;
	
	public Personaje() {
		this.turno = false;
		this.items = new Articulo[10];
		this.monedas = 0;
		this.estrellas = 0;
	}

	public void esTuTurno() {
		turno = true;
	}
	
	public int tirarDado() {
		return (new Random()).nextInt() % 6 + 1;
	}
	
	public boolean mover() {
		return true;
	}
	
	public boolean colision() {
		return false;
	}
	
	public void elegirItem(int itemNumber) {
		
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
		boolean noSeUbico = true;
		for(int i=0; i<items.length; i++) {
			if(noSeUbico) 
				if(items[i] == null) {
					items[i] = articulo;
					noSeUbico = false;
				}
		}
	}

}
