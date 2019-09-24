package entities;

public class Jugador {
	
	private String nickName;
	private String estado;
	private String contraseña;
	
	public Jugador(String nick,String clave) {
		this.nickName = nick;
		this.contraseña = clave;
	}
		
	public String verEstado() {
		return this.estado;
	}
	
	public void estadoListo() {
		this.estado = "Listo";
	}

}