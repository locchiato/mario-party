package ventana.login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.Hibernate;
import entities.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registro extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JPasswordField passwordFieldContracenia;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Registro() {
		setTitle("McFly Party");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 284, 248);
		this.setResizable(false); //para no modificar el tamaño
		this.setLocationRelativeTo(null); // para que aparesca centro de la pantalla
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setBounds(10, 75, 79, 26);
		contentPane.add(lblNombre);
		
		JLabel lblContracea = new JLabel("Contrase\u00F1a:");
		lblContracea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContracea.setHorizontalAlignment(SwingConstants.LEFT);
		lblContracea.setBounds(10, 112, 79, 26);
		contentPane.add(lblContracea);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldNombre.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldNombre.setBounds(99, 78, 146, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		passwordFieldContracenia = new JPasswordField();
		passwordFieldContracenia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordFieldContracenia.setHorizontalAlignment(SwingConstants.LEFT);
		passwordFieldContracenia.setBounds(99, 115, 146, 20);
		contentPane.add(passwordFieldContracenia);
		
		JLabel lblNuevoUsuario = new JLabel("Nuevo Usuario");
		lblNuevoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevoUsuario.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblNuevoUsuario.setBounds(0, 11, 268, 37);
		contentPane.add(lblNuevoUsuario);
		
		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(10, 178, 103, 23);
		contentPane.add(btnVolver);
		
		JButton btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			    String	nombre = textFieldNombre.getText();
			    String contracenia = passwordFieldContracenia.getText();
				
				if(nombre.isEmpty() || contracenia.isEmpty()) {
					CamposVacios cv = new CamposVacios();
					cv.setVisible(true);
				}
				else {
					//en Cliente-Servidor aca envio el mensaje para insertar un nuevo usuario en la base de datos
					
					Hibernate bd = new Hibernate("hibernateMIO.cfg.xml");
					Usuario usuario = new Usuario(nombre,contracenia);
					int respuesta = bd.agregarUsuario(usuario);
					
					switch(respuesta){
						case 1 :
							RegistroCorrecto rc = new RegistroCorrecto();
							rc.setVisible(true);
							dispose();
							break;
						case 0 :
							ErrorRegistro er = new ErrorRegistro();
							er.setVisible(true);
							break;
						case -1:
							NombreUsuarioExistente ne = new NombreUsuarioExistente();
							ne.setVisible(true);
							break;

					}
					
				}
				
			}
		});
		btnRegistrar.setBounds(155, 178, 103, 23);
		contentPane.add(btnRegistrar);
	}

}
