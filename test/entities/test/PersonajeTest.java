package entities.test;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Casilla;
import entities.CasillaSumarRestarMonedas;
import entities.Personaje;

public class PersonajeTest {

	@Test
	public void testSumarRestarMonedas() {
		Personaje pj = new Personaje("Diablo666");

		assertEquals(0, pj.getMonedas());

		pj.sumarRestarMonedas(50);
		assertEquals(50, pj.getMonedas());

		pj.sumarRestarMonedas(-10);
		assertEquals(40, pj.getMonedas());

		pj.sumarRestarMonedas(-45);
		assertEquals(0, pj.getMonedas());
	}

	@Test
	public void testObtenerEstrella() {
		Personaje pj = new Personaje("Diablo666");

		assertEquals(0, pj.getEstrellas());
		pj.obtenerEstrella();
		assertEquals(1, pj.getEstrellas());
		pj.obtenerEstrella();
		assertEquals(2, pj.getEstrellas());
		pj.obtenerEstrella();
		assertEquals(3, pj.getEstrellas());
	}

	@Test
	public void testEsGanador() {
		Personaje pj = new Personaje("Diablo666");
		int estrellasParaGanar = 3;
		pj.obtenerEstrella();
		assertEquals(false, pj.esGanador(estrellasParaGanar));
		pj.obtenerEstrella();
		assertEquals(false, pj.esGanador(estrellasParaGanar));
		pj.obtenerEstrella();
		assertEquals(true, pj.esGanador(estrellasParaGanar));
	}

	@Test
	public void testHayColision() {
		Personaje pj = new Personaje("Diablo666");
		Personaje pjPosicionado = new Personaje("Robin");
		boolean[] dir = { true, true, false, false };
		Casilla casilla = new CasillaSumarRestarMonedas(0, 0, dir, 50);
		pj.setCasillaActual(casilla);
		assertEquals(false, pj.hayColision());

		pjPosicionado.setCasillaActual(casilla);
		casilla.setPersonajePosicionado(pjPosicionado);
		assertEquals(true, pj.hayColision());
	}

	@Test
	public void testParalizado() {
		Personaje pj = new Personaje("Diablo666");
		assertEquals("Normal", pj.getEstado());
		pj.paralizado(5);
		assertEquals("Paralizado", pj.getEstado());
		assertEquals(5, pj.getTurnosParalizados());
	}
	
	@Test
	public void testCurarParalisis() {
		Personaje pj = new Personaje("Diablo666");
		pj.paralizado(3);
		pj.curarParalisis(2);
		assertEquals(1, pj.getTurnosParalizados());
		assertEquals("Paralizado", pj.getEstado());
		pj.curarParalisis(2);
		assertEquals(0, pj.getTurnosParalizados());
		assertEquals("Normal", pj.getEstado());
		pj.paralizado(3);
		pj.curarParalisis(3);
		assertEquals(0, pj.getTurnosParalizados());
		assertEquals("Normal", pj.getEstado());
	}
	
	public void testPuedeMoverse() {
		Personaje pj = new Personaje("Diablo666");
		pj.paralizado(2);
		assertEquals(false, pj.puedeMoverse());
		pj.curarParalisis(2);
		assertEquals(true, pj.puedeMoverse());
	}
	
	@Test
	public void testRetroceder() {
		Personaje pj = new Personaje("Diablo666");
		boolean[] dir = { true, true, false, false };
		Casilla casillaInicial = new CasillaSumarRestarMonedas(0, 0, dir, 50);
		Casilla casillaMedio = new CasillaSumarRestarMonedas(0, 1, dir, 50);
		Casilla CasillaFinal =  new CasillaSumarRestarMonedas(0, 2, dir, 50);
		casillaInicial.setCasillaAnt(casillaMedio);
		casillaMedio.setCasillaAnt(CasillaFinal);
		
		pj.setCasillaActual(casillaInicial);
		assertEquals(casillaInicial, pj.getCasillaActual());
		System.out.println(pj.getCasillaActual().getY());
		pj.retroceder(2);
		assertEquals(CasillaFinal, pj.getCasillaActual());
		System.out.println(pj.getCasillaActual().getY());
	}

//	@Test
//	public void cuandoNoRecogiNingunItem_noPuedoElegirItem() {
//		assertEquals(null, personaje.elegirItem(1));
//		
//	}

//	@Test
//	public void cuandoRecogiAlgunItem_puedoElegirlo() {
//		Articulo articulo = new ItemUno();
//		
//		personaje.recogerItem(articulo);
//		
//		assertEquals(articulo, personaje.elegirItem(1));
//	}

//	@Test
//	public void pisarCasillaMonedas() {
//		Casilla casilla1 = new Casilla(1,2,1);
//		personaje.setCasillaActual(casilla1);
//		casilla1.aplicarEfecto(personaje);
//		assertEquals(10, personaje.getMonedas());
//	}

}
