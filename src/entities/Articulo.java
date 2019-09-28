package entities;

import entities.Personaje;

public abstract class Articulo {
	public String nombre; //nombre de articulo
	public String efecto; //uno mismo o jugador rival

	abstract public void usarArticulo(Personaje pj);

}
