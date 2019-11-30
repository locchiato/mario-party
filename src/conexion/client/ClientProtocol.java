package conexion.client;

import com.google.gson.Gson;

import conexion.shared.BallList;
import conexion.shared.NetworkLoginType;
import conexion.shared.NetworkMessage;

public class ClientProtocol {
	public static void processInput(String input) {
		NetworkMessage message = (new Gson()).fromJson(input, NetworkMessage.class);
		if(message.getLoginType().equals(NetworkLoginType.LOGIN)) {
			//LOGIN
			switch (message.getType()) {
			case NEW:
				//processNew(caller, message);
				break;
				}
		}else {
			//GAME
			switch (message.getType()) {
			case NEW:
				//processNew(caller, message);
				break;
				/*case MSG:
				processMessage(caller, message);
				break;
			case MOV:
				processMovement(caller, message);
				break;
			case PAU:
				processPause(caller, message);
				break;
			case BYE:
				processQuit(caller, message);
				break;
			case PNG:
				processPing(caller, message);
				break;
			case SNC:
				processSync(caller, message);
				break;
			*/
				}
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
}
