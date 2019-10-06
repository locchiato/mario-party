package entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import entities.Personaje;

public class Casilla {
	private int x;
	private int y;
	private boolean[] direcciones = new boolean[4]; // N,S,E,O
	private Casilla casillaAnt;
	private Personaje personajePosicionado;
	private int cantidadDirecciones = 0;
	private Map<Integer, String> nombreDireccion = new HashMap<Integer, String>();

	public Casilla(int x, int y, boolean[] direcciones) {
		this.x = x;
		this.y = y;
		this.direcciones = direcciones;
		calcularTotalDirecciones();
		cargarDirecciones();
	}

	private void calcularTotalDirecciones() {
		for (int i = 0; i < direcciones.length; i++) {
			if (direcciones[i]) {
				cantidadDirecciones++;
			}
		}
	}

	private void cargarDirecciones() {
		nombreDireccion.put(1, "Arriba");
		nombreDireccion.put(2, "Abajo");
		nombreDireccion.put(3, "Derecha");
		nombreDireccion.put(4, "Izquierda");
	}

	public void aplicarEfecto(Personaje pj) {
		System.out.println("Se aplico efecto.");
	}

//	private void cerrarPrimera() {
//		int i = 0;
//		while (!this.direcciones[i])	i++;
//		this.direcciones[i] = false;
//	}
//
//	private void cerrarUltima() {
//		int i = this.direcciones.length - 1;
//		while (!this.direcciones[i])	i--;
//		this.direcciones[i] = false;
//	}

	public Casilla getCasillaAnt() {
		return casillaAnt;
	}

	public void setCasillaAnt(Casilla casillaAnt) {
		this.casillaAnt = casillaAnt;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

//	public int getTipoCasilla() {
//		return tipoCasilla;
//	}

//	public void setTipoCasilla(int tipoCasilla) {
//		this.tipoCasilla = tipoCasilla;
//	}

	public boolean casillaPisada(Personaje pj) {
		if (pj.getCasillaActual().getX() == this.x) {
			return true;
		}
		return false;
	}

	public boolean[] getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(boolean[] direcciones) {
		this.direcciones = direcciones;
	}

	public Personaje getPersonajePosicionado() {
		return personajePosicionado;
	}

	public void setPersonajePosicionado(Personaje personajePosicionado) {
		this.personajePosicionado = personajePosicionado;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	// N,S,E,O direcciones posibles
	// retorna null si hay mas de una direccion posible
	public Casilla casillaSiguiente(Mapa mapa) {

		if (this.cantidadDirecciones == 1) {
			int i = 0;
			while (i < direcciones.length - 1 && direcciones[i] == false) {
				i++;
			}
			return calcularCasilla(mapa, i);

		}
		return null;
	}

	// 0 = norte - 1 = sur - 2 = este - 3 = oeste
	private Casilla calcularCasilla(Mapa mapa, int direccion) {

		switch (direccion) {
		case 0:
			return mapa.obtenerCasilla(this.x -1, this.y);
		case 1:
			return mapa.obtenerCasilla(this.x + 1, this.y);
		case 2:
			return mapa.obtenerCasilla(this.x, this.y + 1);
		case 3:
			return mapa.obtenerCasilla(this.x, this.y - 1);
		}
		return null;
	}

	// si la eleccion es true: la direccion que toma es la primera habilitada
	// (recorriendo el vector desde el principio)
	// si la eleccion es false: la direccion que toma es la segunda habilitada
	// (recorriendo el vector desde el principio)

	private void mostrarDireccionesPosibles() {

		System.out.println("Direcciones posibles: ");

		for (int i = 0; i < direcciones.length; i++) {
			if (direcciones[i]) {
				System.out.println(i + " : " + nombreDireccion.get(i));
			}
		}
	}

	private int ingresarDireccion() {
		Scanner entrada = new Scanner(System.in);
		System.out.print("Ingresar el numero de la direccion: ");
		int respuesta = entrada.nextInt();
		while (respuesta <= 0 && respuesta > 4 && direcciones[respuesta] != true) {
			System.out.println();
			System.out.println("Direccion incorreta");
			System.out.print("Ingrese un numero nuevamente: ");
			respuesta = entrada.nextInt();
		}
		entrada.close();
		return respuesta;
	}

	public Casilla decisionSiguiente(Mapa mapa) {
		mostrarDireccionesPosibles();
		int respuesta = ingresarDireccion();

		return calcularCasilla(mapa, respuesta);
	}

	public int cantidadDirecciones() {
		return this.cantidadDirecciones;
	}

}