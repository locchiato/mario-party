package entities;

public class CasillaDecision extends Casilla {
	private Casilla casillaAlternativa;
	private boolean decision;
	
	public CasillaDecision(int x, int y, int tipo,Casilla sigDer,Casilla sigIzq) {
		super(x, y, tipo);
		this.casillaAlternativa=sigIzq;
		this.casillaSig=sigDer;
	}
	
	public void aplicarEfecto(Personaje pj) {
		
		
		decision(pj);
		//desarrollar la toma de desicion de un personaje par elegir un camino
		//sorbrescribir el metodo del padre para aplicar polimofirmo al tratar todas las casilla de tipo Casilla padre 
	}
	
	private Casilla decision(Personaje pj) {
		if(decision) {
			return this.casillaSig;			
		}
		
		return casillaAlternativa;
	}
	
	public boolean setDecision(boolean decision) {
		return decision;
	}

}
