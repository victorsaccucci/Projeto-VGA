package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import controller.UsuarioController;
import model.ExceptionVGA;
import model.vo.UsuarioVO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLoginUsuario {

	private JFrame frame;
	private JTextField txtEmail;
	private JPasswordField txtSenha;

	private JLabel lblDisable;
	private JLabel lblMostrar;

	private TelaMenuPrincipal telaMenuPrincipal;
	private UsuarioController usuarioController;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLoginUsuario window = new TelaLoginUsuario();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaLoginUsuario() {
		initialize();
	}

	private void initialize() {
		// clean
		frame = new JFrame();
		frame.setUndecorated(true);

		frame.setBounds(100, 100, 946, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel painelLogo = new JPanel();
		painelLogo.setForeground(new Color(0, 128, 128));
		painelLogo.setBackground(new Color(207, 243, 242));
		painelLogo.setBounds(0, 0, 472, 585);
		frame.getContentPane().add(painelLogo);

		JPanel painelCampos = new JPanel();
		painelCampos.setBackground(new Color(0, 139, 139));
		painelCampos.setLayout(null);
		painelCampos.setBounds(474, 0, 472, 585);
		frame.getContentPane().add(painelCampos);

		JLabel lblSair = new JLabel("   X");
		lblSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblSair.setForeground(new Color(255, 255, 255));
		lblSair.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblSair.setBounds(441, 0, 31, 30);
		painelCampos.add(lblSair);

		JLabel lblVGA = new JLabel("VGA");
		lblVGA.setFont(new Font("Yu Gothic Medium", Font.BOLD, 59));
		lblVGA.setForeground(new Color(255, 255, 255));
		lblVGA.setBounds(186, 51, 190, 169);
		painelCampos.add(lblVGA);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBackground(new Color(255, 255, 255));
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(71, 212, 46, 17);
		painelCampos.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setSelectionColor(new Color(0, 128, 128));
		txtEmail.setSelectedTextColor(new Color(0, 128, 128));
		txtEmail.setForeground(new Color(0, 128, 128));
		txtEmail.setDisabledTextColor(new Color(0, 128, 128));
		txtEmail.setColumns(10);
		txtEmail.setBackground(Color.WHITE);
		txtEmail.setBounds(71, 239, 347, 19);
		painelCampos.add(txtEmail);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSenha.setBounds(71, 285, 46, 17);
		painelCampos.add(lblSenha);

		JLabel lblUsuarioImagem = new JLabel("");
		lblUsuarioImagem.setIcon(new ImageIcon("D:\\Downloads\\.opera\\LoginForm\\src\\icon\\icons8_user_20px_1.png"));
		lblUsuarioImagem.setBounds(427, 239, 35, 23);
		painelCampos.add(lblUsuarioImagem);

		lblMostrar = new JLabel("");
		lblMostrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSenha.setEchoChar((char) 8226);

				lblMostrar.setVisible(false);
				lblMostrar.setEnabled(false);

				lblDisable.setVisible(true);
				lblDisable.setEnabled(true);
			}
		});
		lblMostrar.setIcon(new ImageIcon("D:\\Downloads\\.opera\\LoginForm\\src\\icon\\icons8_eye_20px_1.png"));
		lblMostrar.setBounds(427, 312, 20, 20);
		painelCampos.add(lblMostrar);

		lblDisable = new JLabel("");
		lblDisable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSenha.setEchoChar((char) 0);

				lblMostrar.setVisible(true);
				lblMostrar.setEnabled(true);

				lblDisable.setVisible(false);
				lblDisable.setEnabled(false);

			}
		});
		lblDisable.setIcon(new ImageIcon("D:\\Downloads\\.opera\\LoginForm\\src\\icon\\icons8_invisible_20px_1.png"));
		lblDisable.setBounds(427, 312, 20, 20);
		painelCampos.add(lblDisable);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String email = txtEmail.getText();
				String senha = String.valueOf(txtSenha.getPassword());

				telaMenuPrincipal = new TelaMenuPrincipal();
				usuarioController = new UsuarioController();

				try {
					UsuarioVO usuarioAutenticado = usuarioController.realizarLoginController(email, senha);
					frame.setVisible(false);
					telaMenuPrincipal.tornarVisivelForaDoFrame();

				} catch (ExceptionVGA exception) {
					JOptionPane.showConfirmDialog(null, exception.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnEntrar.setForeground(new Color(0, 139, 139));
		btnEntrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnEntrar.setBounds(195, 380, 114, 30);
		painelCampos.add(btnEntrar);

		txtSenha = new JPasswordField();
		txtSenha.setForeground(new Color(0, 128, 128));
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtSenha.setBounds(71, 312, 347, 19);
		painelCampos.add(txtSenha);
	}
}
