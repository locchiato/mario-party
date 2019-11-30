package conexion.client;

import conexion.shared.Global;
import ventana.login.Login;

public class Main {

	public static void main(String[] args) throws Exception {
		Client client = new Client(Global.IP, Global.PORT);
		client.connect();
		Thread serverListener = new ServerListener(client);
		serverListener.start();
		Login ventanaLogin = new Login(client);
		ventanaLogin.setVisible(true);
//		RunnableGame game = new RunnableGame(client);
//		game.init();
//		game.run();
	}
}
