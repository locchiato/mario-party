package entities;

import java.util.List;

public class Generala extends Minijuego {

	private int cantidadDeJugadores;
	private int resultado[];
	private List<Personaje> jugadores;
	private int maximo;

	public Generala() {
		
	}

	public void jugar(List<Personaje> jugadores) {
this.cantidadDeJugadores = jugadores.size();
		
		this.resultado = new int[this.cantidadDeJugadores];
		this.jugadores = jugadores;
		
		
		int suma, maximo=0;
		Dado dado = new Dado(1, 6);
		for (int i = 0; i < this.cantidadDeJugadores; i++) {
			suma = 0;
			for (int j = 0; j < 5; j++) {
				suma += dado.tirarDado();
			}
			resultado[i] = suma;
			maximo = suma;
			if (maximo < suma) {
				maximo = suma;
			//	posicionDelMaximo = i;
			}
		}
		this.maximo=maximo;
		anunciar(maximo);
		darPuntos(maximo);
	}

	public int[] getResultado() {
		return resultado;
	}
	
	

	public void darPuntos(int max){
		int i=0;
		for(Personaje j:jugadores) {
			if(resultado[i]!=max)
				j.sumarRestarMonedas(3);
			else
				j.sumarRestarMonedas(20);
		}		
	}
	
	
	public void anunciar(int max){
		
		int i=0;
		for(Personaje j:jugadores) {
			if(resultado[i]==max)
				System.out.println(j.getNombre()+"Gano");
		}		
			
	}
	

	public Personaje darGanador() {
		Personaje ganador=null;
		int i=0;
		for(Personaje j:jugadores) {
			if(resultado[i]==maximo)
				ganador=j;
		}
		
		return ganador;
	}
	
	
}
