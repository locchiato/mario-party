package ventana.login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.Hibernate;
import conexion.client.Client;
import conexion.shared.NetworkLoginType;
import conexion.shared.NetworkMessageType;
import entities.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	private static final long serialVersionUID = 6415486916552967167L;
	private JPanel contentPane;
	private JTextField txtIngresarNombreUsuario;
	private JPasswordField pwdIingresarContra;
	private static Login INSTANCE = null;
	private static Client cliente = null;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Login frame = new Login(new Client("localhost",5555));
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public static Login getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Login(cliente);
		return INSTANCE;
	}

	public Login(Client cliente) {
		this.cliente = cliente;
		setTitle("McFly Party");
		this.setResizable(false); // para no modificar el tamaño
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 305, 239);
		this.setLocationRelativeTo(null); // para que aparesca centro de la pantalla

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(10, 85, 69, 14);
		contentPane.add(lblUsuario);

		txtIngresarNombreUsuario = new JTextField();
		txtIngresarNombreUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		txtIngresarNombreUsuario.setBounds(89, 82, 135, 20);
		contentPane.add(txtIngresarNombreUsuario);
		txtIngresarNombreUsuario.setColumns(10);

		JLabel lblContracea = new JLabel("Contrase\u00F1a:");
		lblContracea.setBounds(10, 122, 69, 14);
		contentPane.add(lblContracea);

		pwdIingresarContra = new JPasswordField();
		pwdIingresarContra.setBounds(89, 119, 135, 20);
		contentPane.add(pwdIingresarContra);

		JButton btnIngresar = new JButton("INGRESAR");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nombre = txtIngresarNombreUsuario.getText();
				String contracenia = pwdIingresarContra.getText();

				if (nombre.isEmpty() || contracenia.isEmpty()) {
					CamposVacios cv = new CamposVacios();
					cv.setVisible(true);
				} else {
					Usuario usuario = new Usuario(nombre, contracenia);
					cliente.sendLogin(NetworkMessageType.LOGIN,usuario);
					// en Cliente-Servidor aca envio el mensaje para validar datos del usuario en la
					// base de datos

//					Hibernate bd = new Hibernate("hibernateMIO.cfg.xml");
//					Usuario usuario = new Usuario(nombre, contracenia);
//					boolean respuesta = bd.usuarioValidar(usuario);

//					if (respuesta) {
//						JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
//						dispose();
//						// abro el lobby de las Salas
//					} else {
//						LoginIncorrecto er = new LoginIncorrecto();
//						er.setVisible(true);
//					}

				}
			}
		});
		btnIngresar.setBounds(178, 168, 109, 23);
		contentPane.add(btnIngresar);

		JButton btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Registro registro = new Registro();

				registro.setVisible(true);

			}
		});
		btnRegistrar.setBounds(10, 168, 109, 23);
		contentPane.add(btnRegistrar);

		JLabel lblMcflyParty = new JLabel("McFly Party");
		lblMcflyParty.setHorizontalAlignment(SwingConstants.CENTER);
		lblMcflyParty.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblMcflyParty.setBounds(0, 11, 299, 41);
		contentPane.add(lblMcflyParty);
	}
}
