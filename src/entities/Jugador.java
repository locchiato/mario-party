package entities;

public class Jugador {
	
	private String nickName;
	private String estado;
	private String contrase�a;
	
	public Jugador(String nick,String clave) {
		this.nickName = nick;
		this.contrase�a = clave;
	}
		
	public String verEstado() {
		return this.estado;
	}
	
	public void estadoListo() {
		this.estado = "Listo";
	}

}