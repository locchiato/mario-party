package entities.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import entities.EncuentreLaBola;
import entities.Personaje;

public class EncuentreLaBolaTest {



	@Test
	public void testSeDanPuntos() {
		List<Personaje> listaJug = new ArrayList<Personaje>();
		listaJug.add(new Personaje("Batman"));
		listaJug.add(new Personaje("Robin"));
		listaJug.add(new Personaje("Superma"));
		listaJug.add(new Personaje("Mujer Maravilla"));
		
		EncuentreLaBola juego=new EncuentreLaBola();
		juego.jugar(listaJug);
		
		Personaje ganador=juego.darGanador();
		if(ganador!=null)
			assertEquals(20, ganador.getMonedas());
		else
			assertEquals(3,listaJug.get(0).getMonedas());
		
		
	}

}
