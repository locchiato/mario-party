package entities;

import entities.Personaje;

public class Casilla {
	private int x;
	private int tipoCasilla;

	public Casilla(int x, int tipo) { // Por ahora x
		this.x = x;
		this.tipoCasilla = tipo;
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
