package ventana.login;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroCorrecto extends JDialog {


	public static void main(String[] args) {
		try {
			RegistroCorrecto dialog = new RegistroCorrecto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegistroCorrecto() {
		setTitle("McFly Party");
		setBounds(100, 100, 395, 162);
		this.setResizable(false); //para no modificar el tamaño
		this.setLocationRelativeTo(null); // para que aparesca centro de la pantalla
		getContentPane().setLayout(null);
		{
			JButton btnAceptar = new JButton("ACEPTAR");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});

			btnAceptar.setBounds(141, 83, 89, 23);
			getContentPane().add(btnAceptar);
		}
		{
			JLabel lblmensaje = new JLabel("Bienvenido te registraste en McFly Party");
			lblmensaje.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblmensaje.setHorizontalAlignment(SwingConstants.CENTER);
			lblmensaje.setBounds(0, 27, 379, 33);
			getContentPane().add(lblmensaje);
		}
	}

}
