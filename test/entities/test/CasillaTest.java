package entities.test;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Casilla;
import entities.CasillaGanarEstrella;
import entities.CasillaSumarRestarMonedas;
import entities.Personaje;


public class CasillaTest {

	private Casilla casilla;
	

	@Test
	public void testCasillaGanarEstrellas() {
		
		boolean [] dir = {true, true, false, false};
		casilla = new CasillaGanarEstrella(0,0,dir);
		
		Personaje pj  = new Personaje("Batman");
		pj.setCasillaActual(casilla);
		
		assertEquals(0, pj.getEstrellas());
		pj.getCasillaActual().aplicarEfecto(pj);
		assertEquals(1, pj.getEstrellas());
		
	}
	
	@Test
	public void testSumarRestarMonedas() {
		
		boolean [] dir = {true, true, false, false};
		casilla = new CasillaSumarRestarMonedas(0,0,dir,50);
		
		Personaje pj  = new Personaje("Batman");
		pj.setCasillaActual(casilla);

		assertEquals(0, pj.getMonedas());
		pj.getCasillaActual().aplicarEfecto(pj);
		assertEquals(50, pj.getMonedas());

		Casilla casillaResta = new CasillaSumarRestarMonedas(0,0,dir,-10);
		pj.setCasillaActual(casillaResta);
		pj.getCasillaActual().aplicarEfecto(pj);
		assertEquals(40, pj.getMonedas());
		
		casillaResta = new CasillaSumarRestarMonedas(0,0,dir,-45);
		pj.setCasillaActual(casillaResta);
		pj.getCasillaActual().aplicarEfecto(pj);
		assertEquals(0, pj.getMonedas());
		
	}
	
}
