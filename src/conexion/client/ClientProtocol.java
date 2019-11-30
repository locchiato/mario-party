package conexion.client;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import conexion.server.RespuestaLogueo;
import conexion.shared.NetworkMessage;
import ventana.login.LoginIncorrecto;

public class ClientProtocol {
	public static void processInput(String input) throws Exception {
		NetworkMessage message = (new Gson()).fromJson(input, NetworkMessage.class);
		
		switch (message.getType()) {
		case LOGIN:
			login((RespuestaLogueo) message.getMessage());
			// processNew(caller, message);
			break;
		default:
			throw new Exception("Caso fallido");
		/*
		 * case MSG: processMessage(caller, message); break; case MOV:
		 * processMovement(caller, message); break; case PAU: processPause(caller,
		 * message); break; case BYE: processQuit(caller, message); break; case PNG:
		 * processPing(caller, message); break; case SNC: processSync(caller, message);
		 * break;
		 */
		}
	}
//	private static void processNew(NetworkMessage message) {
//		BallList.getInstance().getBall(message.getIdClient()).setInfo((String) message.getMessage());
//	}
//
//	private static void processMessage(NetworkMessage message) {
//		System.out.println(message.getIdClient() + ": " + (String) message.getMessage());
//	}
//
//	private static void processMovement(NetworkMessage message) {
//		BallList.getInstance().getBall(message.getIdClient()).setInfo((String) message.getMessage());
//	}
//
//	private static void processPause(NetworkMessage message) {
//
//	}
//
//	private static void processQuit(NetworkMessage message) {
//		BallList.getInstance().destroyBall(message.getIdClient());
//	}
//
//	private static void processPing(NetworkMessage message) {
//		Client.getInstance().refreshPing();
//	}
//
//	private static void processSync(NetworkMessage message) {
//		Double elapsedTime = (Double) message.getMessage();
//		Client.getInstance().setGameTimeStart(elapsedTime.longValue());
//	}

	private static void login(RespuestaLogueo respuestaLogueo) {
		if (respuestaLogueo.getRespuesta()) {
			JOptionPane.showMessageDialog(null, "Operaci�n realizada correctamente");
			// abro el lobby de las Salas
		} else {
			LoginIncorrecto er = new LoginIncorrecto();
			er.setVisible(true);
		}

	}
}
