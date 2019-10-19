package entities.test;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Casilla;
import entities.CasillaGanarEstrella;
import entities.CasillaParalizar;
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
	public void testCasillaSumarRestarMonedas() {
		
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
	
	@Test
	public void testCasillaParalizado() {
		
		boolean [] dir = {true, true, false, false};
		casilla = new CasillaParalizar(0,0,dir,3);
		
		Personaje pj  = new Personaje("Batman");
		pj.setCasillaActual(casilla);

		assertEquals("Normal", pj.getEstado());
		pj.getCasillaActual().aplicarEfecto(pj);
		assertEquals(3, pj.getTurnosParalizados());
		assertEquals("Paralizado", pj.getEstado());
		
	}
	
	@Test
	public void testcalcularTotalDirecciones() {
		
		boolean [] dir = {true, false, true, false};
		casilla = new CasillaParalizar(0,0,dir,3);
		assertEquals(2, casilla.getcantidadDirecciones());
		
		boolean [] dir2 = {true, false, false, false};
		casilla = new CasillaParalizar(0,0,dir2,2);
		assertEquals(1, casilla.getcantidadDirecciones());
	}
	
	@Test
	public void testDesocuparCasilla() {
		
		boolean [] dir = {true, false, true, false};
		casilla = new CasillaParalizar(0,0,dir,3);
		
		Personaje pj  = new Personaje("Batman");
		pj.setCasillaActual(casilla);
		casilla.setPersonajePosicionado(pj);
		Personaje pj2  = new Personaje("Robin");
		pj.setCasillaActual(casilla);
		casilla.desocuparCasilla(pj2);
		assertEquals(pj,casilla.getPersonajePosicionado());
		casilla.desocuparCasilla(pj);
		assertEquals(null,casilla.getPersonajePosicionado());
	}
	
	
}
