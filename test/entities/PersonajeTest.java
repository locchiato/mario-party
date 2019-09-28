package entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PersonajeTest {

	private Personaje personaje;
	
	@Before
	public void setUp() {
		personaje = new Personaje("Santi12",1);
	}
	
	@Test
	public void cuandoNoRecogiNingunItem_noPuedoElegirItem() {
		assertEquals(null, personaje.elegirItem(1));
		
	}

	@Test
	public void cuandoRecogiAlgunItem_puedoElegirlo() {
		Articulo articulo = new ItemUno();
		
		personaje.recogerItem(articulo);
		
		assertEquals(articulo, personaje.elegirItem(1));
	}

}
