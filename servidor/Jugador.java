
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class Jugador {
	//Este jugador lo usaremos de ejemplo pero falta actualizarlo, basicamente hay que evaluar 
	//si a nuestro jugador debemos agregarle metodos como:
	//setOut, getIn, etc...
	
	private Socket clientSocket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	
	private int x;
	private int y;
	private int direccion; // 0 arriba - 1 abajo - 2 izquierda - 3 derecha
	private String nick;
	private boolean esZombie;
	
	public boolean isEsZombie() {
		return esZombie;
	}

	public void setEsZombie(boolean esZombie) {
		this.esZombie = esZombie;
	}

	public boolean isEnvieDireccion() {
		return envieDireccion;
	}

	public void setEnvieDireccion(boolean envieDireccion) {
		this.envieDireccion = envieDireccion;
	}

	private boolean estoyListo;
	private boolean envieDireccion;
	
	public boolean isEstoyListo() {
		return estoyListo;
	}

	public void setEstoyListo(boolean estoyListo) {
		this.estoyListo = estoyListo;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDireccion() {
		return direccion;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public Jugador( Socket s , ObjectOutputStream o, ObjectInputStream i ){
		clientSocket = s;
		in = i;
		out = o;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public void setIn(ObjectInputStream in) {
		this.in = in;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	
	
}
