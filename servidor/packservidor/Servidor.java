package packservidor;

import java.io.FileNotFoundException;
import java.net.*;
import java.util.*;

import entities.Jugador;
import entities.Mapa;

public class Servidor {

	// ESCUCHARA CONSTANTEMENTE A LOS JUGADORES

	private ServerSocket sSocket;
	// ESCUCHA EN EL PUERTO 5000
	private static final int PUERTO = 5000;

	// ARRAYLIST DE LAS PERSONAS QUE SE CONECTAN A LA PARTIDA
	private ArrayList<Socket> usuarios;
private ArrayList<Jugador>jugadores;
	// FRAME PARA LA VISUALIZACION DEL SERVER
	private ServidorFrame frame;

	public Servidor(final ServidorFrame f) {
		this.frame = f;

		usuarios = new ArrayList<Socket>();
jugadores=new ArrayList<Jugador>();
		try {
			// GENERO EL SERVERSOCKET
			sSocket = new ServerSocket(PUERTO);
			frame.setIPServer(InetAddress.getLocalHost().getHostAddress());
			// ESCUCHO SIEMPRE A VER SI SE CONECTA UN CLIENTE
			// UNA VEZ QUE SE CONECTA GENERO UN HILO Y LO MANDO A EJECUTAR, A ESO
			// LO GUARDO EN UN ARRAYLIST
			frame.mostrarMensajeFrame("ESCUCHANDO EN PUERTO " + PUERTO);
			while (true) {
				// LO PONGO A ESCUCHAR HASTA QUE RECIBA UNA CONEXION
				Socket clientSocket = sSocket.accept();

				frame.mostrarMensajeFrame("Se conecto: " + clientSocket.getInetAddress().getHostAddress());
				usuarios.add(clientSocket);
				HiloDeCliente hilo = new HiloDeCliente(clientSocket, usuarios, frame,this);
				hilo.start();
				// MANDO A EJECUTAR EL HILO
				// VUELVO AL PRINCIPIO DEL WHILE A EMPEZAR A ESCUCHAR DE NUEVO
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	
	public void verComienzoJuego() throws FileNotFoundException {
		if(jugadores.size()==3) {
			Mapa juegoSuperMario=new Mapa(jugadores, 20);
		}
	}
	
	
	
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Servidor sv = new Servidor(new ServidorFrame());

	}
	

	
	
	/////////////////// METODOS PARA EL MANEJO DE BASE DE DATOS/////////
	public void desconectar() {
		// MySQLConnection.close();
	}
}
