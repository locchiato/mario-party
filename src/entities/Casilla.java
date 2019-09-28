package entities;


import entities.Personaje;

public class Casilla {
	protected int x;
	protected int y;
	protected int tipoCasilla;
	protected Casilla casillaSig;
	protected Casilla casillaAnt;

	public Casilla(int x, int y, int tipo) {
		this.x = x;
		this.y = y;
		this.tipoCasilla = tipo;
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
			pj.paralizado();
			break;
		case 5:
			// PierdeMonedas
			pj.sumarMonedas(-10);
		case 6:
			// GanaItemAleatorio
			break;
		}
	}

	public Casilla getCasillaSig() {
		return casillaSig;
	}

	public void setCasillaSig(Casilla casillaSig) {
		this.casillaSig = casillaSig;
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


}