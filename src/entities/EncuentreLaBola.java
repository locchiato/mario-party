package entities;

import java.util.List;
import java.util.Scanner;

public class EncuentreLaBola extends Minijuego {
	
	private int elecciones[];
	private List<Personaje> jugadores;
	private int respuesta;
	
	

	public EncuentreLaBola() {
		
	}


	@Override
	public void jugar(List<Personaje> jugadores) {
		this.elecciones = new int[jugadores.size()];
		this.jugadores = jugadores;
		
		
		
		
		
		int eleccion;
		this.respuesta = (int)(4 * Math.random())+1;
		int i=0;
		for(Personaje p:jugadores) {
			
			do{
			System.out.println("Elija una opcion del 1 al 5");
			Scanner sc=new Scanner(System.in);
			eleccion=sc.nextInt();
		}while(eleccion<1 && eleccion<5);
			elecciones[i]=eleccion;		
		}
			anunciar(respuesta);
		darPuntos(respuesta);	
	}

	
	public void darPuntos(int resp){
		int i=0;
		for(Personaje j:jugadores) {
			if(elecciones[i]!=resp)
				j.sumarRestarMonedas(3);
			else
				j.sumarRestarMonedas(20);
		}		
	}
	
	public void anunciar(int resp){
		
		int i=0;
		for(Personaje j:jugadores) {
			if(elecciones[i]==resp)
				System.out.println(j.getNombre()+"Gano");
		}		
			
	}
	
	
	public Personaje darGanador() {
		Personaje ganador=null;
		int i=0;
		for(Personaje j:jugadores) {
			if(elecciones[i]==respuesta)
				ganador=j;
		}
		
		return ganador;
	}
	
}
