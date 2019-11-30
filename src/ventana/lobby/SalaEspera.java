package ventana.lobby;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import entities.Usuario;

public class SalaEspera extends JFrame {

	private JPanel contentPane;
	private JList<String> list;
	DefaultListModel<String> modelo;
	List<Usuario> usuarios;
	int cantPartidas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					List<Usuario>jugadores = new ArrayList<Usuario>();
					Usuario usuario = new Usuario("mauro","123456");
					Usuario usuario2 = new Usuario("mauro66","123456");
					jugadores.add(usuario);
					jugadores.add(usuario2);
					
					SalaEspera frame = new SalaEspera(jugadores,10);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SalaEspera(List<Usuario> usuarios, int CantidadPartidas) {
		
		this.cantPartidas= cantPartidas;
		this.usuarios = usuarios;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 278, 279);
		
		this.setResizable(false); //para no modificar el tamaño
		this.setLocationRelativeTo(null); // para que aparesca centro de la pantalla
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSalaEspera = new JLabel("SALA ESPERA");
		lblSalaEspera.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 25));
		lblSalaEspera.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalaEspera.setBounds(0, 0, 272, 48);
		contentPane.add(lblSalaEspera);
		

		cargarUsuario();
		
		list.setBounds(134, 74, 124, 166);
		contentPane.add(list);
		list.setModel(modelo);
		
		JLabel lblJugadores = new JLabel("Jugadores:");
		lblJugadores.setBounds(134, 49, 77, 14);
		contentPane.add(lblJugadores);
		
		JButton btnIniciarPartida = new JButton("INICIAR");
		btnIniciarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/// iniciar la partida con los jugadores hasta el momento
			}
		});
		btnIniciarPartida.setBounds(10, 149, 114, 23);
		contentPane.add(btnIniciarPartida);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/// sacar jugador de la lista y abrir login
				
				
			}
		});
		btnSalir.setBounds(10, 217, 114, 23);
		contentPane.add(btnSalir);
		
		JButton btnConfigurar = new JButton("CONFIGURAR");
		btnConfigurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			
			}
		});
		btnConfigurar.setBounds(10, 183, 114, 23);
		contentPane.add(btnConfigurar);
	}

	private void cargarUsuario() {
		
		list = new JList<String>();
		modelo = new DefaultListModel<String>();
		
		for (Usuario usuario : usuarios) {
			modelo.addElement(usuario.getNombre());
		}
		list.setModel(modelo);
	}

}
