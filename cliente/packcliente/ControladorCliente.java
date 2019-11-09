package packcliente;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import comunicaciones.AltaJugador;
import ui.EscucharTeclaInterface;
import ui.MarioJFrame;

public class ControladorCliente implements EscucharTeclaInterface {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	// DATOS NECESARIOS PARA LA CONEXION
	private String host;
	private int puerto;
	private int jugador;
	private Object objLeido;
	private int idUsuario;

	MarioJFrame marioJFrame;
	private ClienteReg registro;

	public ControladorCliente(String host, int puerto) throws UnknownHostException, IOException {
		super();
		this.host = host;
		this.puerto = puerto;
		crearConexion();
		 registro = new ClienteReg(this);
		registro.setVisible(true);
	}

	private void crearConexion() throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		socket = new Socket(host, puerto);
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
		HiloDeJuego();
	}

	public static void main(String[] args) throws UnknownHostException, IOException {

		new ControladorCliente("localhost", 5000);

	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public Object escuchar() {
		try {
			System.out.println(in.toString());
			Object peticion = in.readObject();
			objLeido = peticion;
			return peticion;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void enviarMensaje(Object obj) {
		try {
			// USAR SIEMPRE los tres
			out.reset();
			out.writeObject(obj);
			out.flush();

			objLeido = (Object) in.readObject();
			if (objLeido instanceof Integer) {
				jugador = (Integer) objLeido; // REPRESENTA AL JUGADOR EN JUEGO
			}
			System.out.println(objLeido);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getJugador() {
		return jugador;
	}

	public void setJugador(int jugador) {
		this.jugador = jugador;
	}

	public Object leerMensaje() {
		return objLeido;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public void cerrarSocket() {
		try {
			enviarMensaje("KILL THREAD");
			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int id) {
		this.idUsuario = id;
	}

	/*
	 * private void iniciarMapa(MsjMapa msjMapa) { marioJFrame = new
	 * MarioJFrame(msjMapa.getMapa().getTablero(),
	 * msjMapa.getMapa().getTablero().length, this);
	 * 
	 * }
	 */

	/*
	 * private void redibujarMapa(MsjRedibujar peticion) {
	 * marioJFrame.redibujar(peticion.getMapa().getTablero()); }
	 */

	public void darAltaJugador(String nick, Color color) throws IOException {
		out.writeObject(new AltaJugador(nick, color));

	}

	@Override
	public void teclaPresionada(int tecla) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getTeclaPresionada() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void limpiarTeclaPresionada() {
		// TODO Auto-generated method stub

	}

	
	
	
	
	public void HiloDeJuego() {
		Thread hiloDeJuego = new Thread(new Runnable() {

			public void run() {
				Object peticion = null;
				while (true) {
					try {
						peticion = in.readObject();
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					switch (peticion.getClass().getSimpleName()) {
					case "AltaJugador":
						crearVentanaPrueba();
						break;
					}
				} // FIN WHILE TRUE
			}

		});
		hiloDeJuego.start();
	}

	protected void crearVentanaPrueba() {
		ventanaPrueba vp = new ventanaPrueba();
		vp.setVisible(true);
		registro.dispose();
	}

}
