package entities;


import entities.Personaje;

public class Casilla {
	private int x;
	private int y;
	private int tipoCasilla;
	private Casilla casillaSig;
	private Casilla casillaAnt;
	private Casilla casillaAlternativa;
	private boolean decision; //eligio casilla alternativa (izq) false / eligio casilla siguiente (derecha) true

	public Casilla(int x, int y, int tipo) {
		this.x = x;
		this.y = y;
		this.tipoCasilla = tipo;
	}
	
	public Casilla(int x, int y, int tipo,Casilla ant , Casilla sig , Casilla alternativa) {
		this.x = x;
		this.y = y;
		this.tipoCasilla = tipo;
		this.casillaAlternativa = alternativa;
		this.casillaAnt = ant;
		this.casillaSig = sig;
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
	
	public Casilla decision(boolean eleccion) {
		if(eleccion) {
			return this.casillaSig;			
		}
		return this.casillaAlternativa;
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

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Casilla getCasillaAlternativa() {
		return casillaAlternativa;
	}

	public void setCasillaAlternativa(Casilla casillaAlternativa) {
		this.casillaAlternativa = casillaAlternativa;
	}


}