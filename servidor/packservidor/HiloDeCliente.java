package packservidor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.*;

import comunicaciones.*;

import entities.Jugador;

public class HiloDeCliente extends Thread {

	// SOCKET PARA COMUNICARSE CON EL CLIENTE QUE SE CONECTO
	private Socket clientSocket;
	// CLASES PARA ESCRIBIR Y LEER, NECESARIAS PARA LA COMINICACION
	private ObjectInputStream in;
	private ObjectOutputStream out;
	// ARRAYLIST DE LOS USUARIOS
	private ArrayList<Socket> usuarios;

	private ServidorFrame frame;
	Servidor servidor;
	private String ipCliente;

	private int idUsuario;

	public HiloDeCliente(Socket s, ArrayList<Socket> u, ServidorFrame frame, Servidor servidor) {
		clientSocket = s;
		this.frame = frame;
		setUsuarios(u);
		this.servidor = servidor;
		try {
			// VINCULAMOS LOS INPUT Y OUTPUT CON LOS DEL CLIENTE( ESTOS LOS TIENE EL SOCKET
			// )
			in = new ObjectInputStream(clientSocket.getInputStream());
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			ipCliente = clientSocket.getInetAddress().getHostAddress();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// ESTA FUNCION SE EJECUTA UNA VEZ QUE EL SERVIDOR LE MANDA EL START AL HILO
	// ESTA SE ENCARGA DE ESCUCHAR CONSTANTEMENTE AL SOCKET DE CLIENTE
	@Override
	public void run() {
		try {

			while (true) {
				// RECIBIMOS EL OBJETO
				Object peticion = in.readObject();
				System.out.println(" Se recibio un objeto " + peticion.getClass().getName());
				/// Pregunta por el tipo de objeto recibido, por cada nueva clase para
				/// comunicacion
				// se agrega un case
				switch (peticion.getClass().getSimpleName()) {
				case "AltaJugador":
					altaJugador((AltaJugador) peticion);
					System.out.println("Recibido");
					break;
			
				}

			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private void altaJugador(AltaJugador peticion) throws FileNotFoundException {
		System.out.println(peticion.getNick() + peticion.getColor().toString());
		servidor.getJugadores().add(new Jugador(peticion.getNick(), peticion.getColor(), clientSocket, in, out));
		preguntarComienzoJuego();
	}

	private void preguntarComienzoJuego() throws FileNotFoundException {
		servidor.verComienzoJuego();
	}

	// Lo dejo para que nos guiemos como ejemplo
	/*
	 * private void enviarPartidas( ActualizarPartidasBean peticion ) throws
	 * IOException{ // DEBERIAMOS HACER ESTO PARA CADA PARTIDA for (Partida partida
	 * : partidas) {
	 * 
	 * peticion.getPartidas().add(partida.getNombre() + " " +
	 * partida.getCantJugadoresEnCurso() + " " + partida.getCantJugadoresMax() + " "
	 * + partida.isEstado() ); } out.writeObject(peticion);
	 * 
	 * }
	 */

	private void desconectarUsuario() {
		// QUITAMOS AL USURIO DE LA LISTA DE USUARIOS CONECTADOS Y DEJAMOS QUE EL
		// CLIENTE CIERRE LA
		// LA CONEXION
		usuarios.remove(clientSocket);
	}

	private boolean loguear() {
		return true;
	}

	public boolean agregarUsuario() {
		return true;
	}

	public ArrayList<Socket> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Socket> usuarios) {
		this.usuarios = usuarios;
	}

}
