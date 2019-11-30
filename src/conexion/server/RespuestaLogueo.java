package conexion.server;

public class RespuestaLogueo {
	private int id;
	private boolean respuesta;
	
	
	public RespuestaLogueo(int id,boolean respuesta) {
		this.id = id;
		this.respuesta = respuesta;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public boolean getRespuesta() {
		return respuesta;
	}


	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	}
	
	
}
