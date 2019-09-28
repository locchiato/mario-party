package entities;

import java.util.List;

public class Generala extends Minijuego {

	private int cantidadDeJugadores;
	private int resultado[];
	private List<Personaje> jugadores;

	public Generala(List<Personaje> jugadores) {
		this.cantidadDeJugadores = jugadores.size();
		;
		this.resultado = new int[this.cantidadDeJugadores];
		this.jugadores = jugadores;
	}

	public void jugar() {
		int suma, maximo, posicionDelMaximo = 0;
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
				posicionDelMaximo = i;
			}
		}
		jugadores.get(posicionDelMaximo).sumarMonedas(1);
	}

	public int[] getResultado() {
		return resultado;
	}

}
