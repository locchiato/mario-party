package conexion.shared;

public class NetworkMessage {
	private long time;
	private NetworkMessageType type;
	private NetworkLoginType loginType;
	private int idClient;
	private Object message;

	public NetworkMessage(NetworkMessageType type, int idClient, Object message, NetworkLoginType loginType) {
		this.time = System.nanoTime();
		this.type = type;
		this.idClient = idClient;
		this.message = message;
		this.loginType = loginType;
	}

	public NetworkMessage(NetworkMessageType type, Object message, NetworkLoginType loginType) {
		this(type, 0, message, loginType);
	}

	public NetworkMessage(NetworkMessageType type, int idClient, NetworkLoginType loginType) {
		this(type, idClient, null, loginType);
	}

	public NetworkMessage(NetworkMessageType type, NetworkLoginType loginType) {
		this(type, 0, null, loginType);
	}

	public NetworkMessageType getType() {
		return type;
	}
	
	public NetworkLoginType getLoginType() {
		return loginType;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public void setType(NetworkMessageType type) {
		this.type = type;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "NetworkMessage [time=" + time + ", type=" + type + ", idClient=" + idClient + ", message=" + message
				+ "]";
	}

}
