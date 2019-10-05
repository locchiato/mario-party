package entities;

import entities.Personaje;

public class Casilla {
	private int x;
	private int y;
	private int tipoCasilla;

	private boolean[] direcciones = new boolean[4];

	private Casilla casillaAnt;

	public Casilla(int x, int y, int tipo) {
		this.x = x;
		this.y = y;
		this.tipoCasilla = tipo;
	}

	public void aplicarEfecto(Personaje pj) {
		System.out.println("Se aplico efecto.");
	}

	public void decision(boolean eleccion) {
		if (eleccion)
			cerrarUltima();
		else
			cerrarPrimera();
	}

	private void cerrarPrimera() {
		int i = 0;
		while (!this.direcciones[i])	i++;
		this.direcciones[i] = false;
	}

	private void cerrarUltima() {
		int i = this.direcciones.length - 1;
		while (!this.direcciones[i])	i--;
		this.direcciones[i] = false;
	}

	public Casilla getCasillaAnt() {
		return casillaAnt;
	}

	public void setCasillaAnt(Casilla casillaAnt) {
		this.casillaAnt = casillaAnt;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getTipoCasilla() {
		return tipoCasilla;
	}

	public void setTipoCasilla(int tipoCasilla) {
		this.tipoCasilla = tipoCasilla;
	}

	public boolean casillaPisada(Personaje pj) {
		if (pj.getCasillaActual().getX() == this.x) {
			return true;
		}
		return false;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}