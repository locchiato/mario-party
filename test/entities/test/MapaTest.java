package entities.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import entities.Casilla;
import entities.Jugador;
import entities.Mapa;
import entities.Personaje;

class MapaTest {
	
	//comentar la funcion inicioJuego que esta en el constructor para hacer el test

	@Test
	void testCargarMapaCasillas() throws FileNotFoundException {
		//prueba con el tablero1.txt
		List<Jugador> listaJug = new ArrayList<Jugador>();
		listaJug.add(new Jugador("Batman"));
		listaJug.add(new Jugador("Robin"));
		listaJug.add(new Jugador("Superma"));
		listaJug.add(new Jugador("Mujer Maravilla"));
		
		boolean [] dir = {true, false, true, false};
		Casilla c = new Casilla(0,0,dir);
		Casilla [][] tablero = {{c,c,c,c,c},{c,null,c,null,c},{c,null,c,null,c},{c,null,c,null,c},{c,c,c,c,c}};
		
		Mapa m = new Mapa(listaJug,5);
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Casilla ct = tablero[i][j];
				Casilla cm = m.obtenerCasilla(i, j);
				if(ct != null) {
					assertTrue(null != cm);
				}
				else {
					assertTrue(null == cm);
				}
			}
		}
	}

	
	
}
