package entities;

import entities.Personaje;

public class Casilla {
	private int x;
	private int y;
	private int tipoCasilla;
	private String dirSig;

	public Casilla(int x, int y, int tipo, String dirSig) {
		this.x = x;
		this.y = y;
		this.tipoCasilla = tipo;
		this.dirSig = dirSig;
	}

	public String getDirSig() {
		return dirSig;
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

	public void aplicarEfecto(Personaje pj) {
		switch (this.tipoCasilla) {
		case 1:
			// Nada
			break;
		case 2:
			// SumaMonedas
			pj.sumarMonedas(10);
			break;
		case 3:
			// SumaEstrella
			pj.obtenerEstrella();
			break;
		case 4:
			// Paralizado
			break;
		case 5:
			// PierdeMonedas
			pj.sumarMonedas(-10);
		case 6:
			// GanaItemAleatorio
			break;
		}
	}

}