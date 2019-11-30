package conexion.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;

import bd.Hibernate;
import conexion.shared.BallList;
import conexion.shared.BallMovementType;
import conexion.shared.NetworkMessage;
import conexion.shared.NetworkMessageType;
import entities.Usuario;
import conexion.shared.Ball;

public class ServerProtocol {
	public static void processInput(ServerThread caller, String input) {
		try {
			NetworkMessage message = (new Gson()).fromJson(input, NetworkMessage.class);
			switch (message.getType()) {
			case LOGIN:
				Hibernate bd = new Hibernate("hibernateMIO.cfg.xml");
				
				System.out.println("LA CONCHA DE LA LORA");
				
				boolean respuesta = bd.usuarioValidar((Usuario)message.getMessage());
				RespuestaLogueo res = new RespuestaLogueo(message.getIdClient(), respuesta);
				caller.send((new Gson()).toJson(new NetworkMessage(NetworkMessageType.LOGIN, message.getIdClient(), res)));
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

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	/*
	 * private static void processNew(ServerThread caller, NetworkMessage message) {
	 * Server.broadcast((new Gson()).toJson(new
	 * NetworkMessage(NetworkMessageType.NEW, caller.id,
	 * BallList.getInstance().getBall(caller.id).getInfo()))); }
	 * 
	 * private static void processMessage(ServerThread caller, NetworkMessage
	 * message) { Server.broadcast( (new Gson()).toJson(new
	 * NetworkMessage(NetworkMessageType.MSG, caller.id, message.getMessage()))); }
	 * 
	 * // This method generate a delay of 20 ms to balance the movements against
	 * high // values of ping private static void processMovement(ServerThread
	 * caller, NetworkMessage message) { ScheduledExecutorService executorService =
	 * Executors.newSingleThreadScheduledExecutor();
	 * 
	 * Runnable processMovementDelay = () -> { BallMovementType action =
	 * BallMovementType.valueOf((String) message.getMessage()); Ball ball =
	 * BallList.getInstance().getBall(caller.id); ball.doAction(action);
	 * Server.broadcast( (new Gson()).toJson(new
	 * NetworkMessage(NetworkMessageType.MOV, caller.id, ball.getInfo()))); };
	 * 
	 * executorService.schedule(processMovementDelay, 20, TimeUnit.MILLISECONDS);
	 * executorService.shutdown(); }
	 * 
	 * private static void processPause(ServerThread caller, NetworkMessage message)
	 * { // TODO Game pause }
	 * 
	 * private static void processQuit(ServerThread caller, NetworkMessage message)
	 * { caller.close(); }
	 * 
	 * private static void processPing(ServerThread caller, NetworkMessage message)
	 * { caller.send((new Gson()).toJson(new
	 * NetworkMessage(NetworkMessageType.PNG))); }
	 * 
	 * private static void processSync(ServerThread caller, NetworkMessage message)
	 * { // This could be used to synchronize everything, such as new players and
	 * players // who left the game long gameTime = System.nanoTime() -
	 * Server.getGameTimeStart(); caller.send((new Gson()).toJson(new
	 * NetworkMessage(NetworkMessageType.SNC, gameTime))); }
	 */

}
