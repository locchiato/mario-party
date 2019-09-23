package entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PersonajeTest {

	private Personaje personaje;
	
	@Before
	public void setUp() {
		personaje = new Personaje();
	}
	
	@Test
	public void test() {
		Articulo articulo = new ItemUno();
		
		assertEquals(null, personaje.elegirItem(1));
		personaje.recogerItem(articulo);
		
		assertEquals(articulo, personaje.elegirItem(1));
		
	}

}
