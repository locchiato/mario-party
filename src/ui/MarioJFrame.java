package ui;
import javax.swing.JFrame;
import entities.Casilla;

public class MarioJFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private MarioJPanel panel;

	public MarioJFrame(Casilla[][] tablero, int cantidadCasillas) {
		//Full Screen
		setExtendedState(MAXIMIZED_BOTH); 
		
		//cerrar con la X la ventana
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setTitle(Textos.TITULO_PRINCIPAL);
		setLocationRelativeTo(null);
		
		panel = new MarioJPanel(tablero, cantidadCasillas);
		setContentPane(panel);
		
		setVisible(true);
	}
	
	public void redibujar(Casilla[][] tablero){
		panel.redibujar(tablero);
		repaint();
	}

}
