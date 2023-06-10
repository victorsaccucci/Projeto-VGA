package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;

import controller.UsuarioController;
import model.ExceptionVGA;
import model.vo.UsuarioVO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;

import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class TelaLoginUsuario {

	private JFrame frame;
	private JTextField txtEmail;
	private JPasswordField txtSenha;

	private JLabel lblDisable;
	private JLabel lblMostrar;

	private UsuarioController usuarioController;
	private UsuarioVO usuarioAutenticado;

	private TelaControleEstoque telaControleEstoque;
	private TelaMenuPrincipal telaMenuPrincipal;
	private TelaCadastroUsuario telaCadastroUsuario;
	private TelaMenuAdm telaMenuAdm;

	private JLabel lblCadastrar;
	private JLabel lblUsuarioImagem;
	private JLabel lblSenha;
	private JLabel lblEmail;
	private JLabel lblVGA;
	private JLabel lblSair;
	private JPanel painelCampos;
	private JPanel painelLogo;
	private JLabel lblEsqueceuSenha;
	private JLabel lblNewLabel;
	private JLabel lblMaximizar;
	private JLabel lblMinizar;

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
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
		    private Point initialClick;

		    @Override
		    public void mousePressed(MouseEvent e) {
		        initialClick = e.getPoint();
		        frame.getComponentAt(initialClick);
		    }

		    @Override
		    public void mouseDragged(MouseEvent e) {
		        int thisX = frame.getLocation().x;
		        int thisY = frame.getLocation().y;

		        int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
		        int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

		        int X = thisX + xMoved;
		        int Y = thisY + yMoved;

		        frame.setLocation(X, Y);
		    }
		};

		frame.addMouseListener(mouseAdapter);
		frame.addMouseMotionListener(mouseAdapter);
		frame.setBounds(100, 100, 946, 585);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		painelLogo = new JPanel();
		painelLogo.setLayout(null);
		painelLogo.setForeground(new Color(0, 128, 128));
		painelLogo.setBackground(new Color(207, 243, 242));
		painelLogo.setBounds(0, 0, 472, 585);
		frame.getContentPane().add(painelLogo);

		lblNewLabel = new JLabel("New label");
		painelLogo.add(lblNewLabel);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaLoginUsuario.class.getResource("/icones/LogoVGA3.png")));
		lblLogo.setBounds(79, 78, 328, 411);
		painelLogo.add(lblLogo);

		painelCampos = new JPanel();
		painelCampos.setBackground(new Color(0, 139, 139));
		painelCampos.setLayout(null);
		painelCampos.setBounds(474, 0, 472, 585);
		frame.getContentPane().add(painelCampos);

		lblSair = new JLabel("   X");
		lblSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblSair.setForeground(new Color(255, 255, 255));
		lblSair.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblSair.setBounds(441, 5, 22, 21);
		painelCampos.add(lblSair);

		lblVGA = new JLabel("VGA");
		lblVGA.setFont(new Font("Yu Gothic Medium", Font.BOLD, 59));
		lblVGA.setForeground(new Color(255, 255, 255));
		lblVGA.setBounds(186, 51, 190, 169);
		painelCampos.add(lblVGA);

		lblEmail = new JLabel("E-mail");
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

		lblSenha = new JLabel("Senha");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSenha.setBounds(71, 285, 46, 17);
		painelCampos.add(lblSenha);

		lblUsuarioImagem = new JLabel("");
		lblUsuarioImagem.setIcon(new ImageIcon(TelaLoginUsuario.class.getResource("/icones/icons8_user_20px_1.png")));
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
		lblMostrar.setIcon(new ImageIcon(TelaLoginUsuario.class.getResource("/icones/icons8_eye_20px_1.png")));
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
		lblDisable.setIcon(new ImageIcon(TelaLoginUsuario.class.getResource("/icones/icons8_invisible_20px_1.png")));
		lblDisable.setBounds(427, 312, 20, 20);
		painelCampos.add(lblDisable);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String email = txtEmail.getText();
				String senha = String.valueOf(txtSenha.getPassword());

				telaMenuPrincipal = new TelaMenuPrincipal();
				usuarioController = new UsuarioController();
				telaMenuAdm = new TelaMenuAdm();

				try {
					if (usuarioController.realizarLoginController(email, senha).isAdm() == false) {

						usuarioAutenticado = usuarioController.realizarLoginController(email, senha);
						telaMenuPrincipal.tornarVisivelForaDoFrame();
						frame.setVisible(false);

					} else {
						telaMenuAdm.tornarVisivelForaDoFrame();
						frame.setVisible(false);
					}

				} catch (ExceptionVGA exception) {
					JOptionPane.showConfirmDialog(null, exception.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnEntrar.setForeground(new Color(0, 139, 139));
		btnEntrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnEntrar.setBounds(158, 372, 190, 30);
		painelCampos.add(btnEntrar);

		txtSenha = new JPasswordField();
		txtSenha.setForeground(new Color(0, 128, 128));
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtSenha.setBounds(71, 312, 347, 19);
		painelCampos.add(txtSenha);

		lblCadastrar = new JLabel("Não possui cadastro?");
		lblCadastrar.setForeground(new Color(255, 255, 255));
		lblCadastrar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCadastrar.setBounds(158, 408, 114, 14);
		painelCampos.add(lblCadastrar);

		JLabel lblCadastrarse = new JLabel("Cadastrar-se");
		lblCadastrarse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					telaCadastroUsuario = new TelaCadastroUsuario();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				telaCadastroUsuario.tornarVisivelForaDoFrame();
				frame.setVisible(false);
			}
		});
		lblCadastrarse.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCadastrarse.setForeground(new Color(255, 255, 255));
		lblCadastrarse.setBounds(282, 408, 103, 14);
		painelCampos.add(lblCadastrarse);

		lblEsqueceuSenha = new JLabel("Esqueceu sua senha?");
		lblEsqueceuSenha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblEsqueceuSenha.setForeground(new Color(255, 255, 255));
		lblEsqueceuSenha.setBounds(322, 335, 110, 16);
		painelCampos.add(lblEsqueceuSenha);

		lblMaximizar = new JLabel("  ");
		lblMaximizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (frame.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
					frame.setExtendedState(JFrame.NORMAL);
				} else {
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
			}
		});
		lblMaximizar.setIcon(new ImageIcon(TelaLoginUsuario.class.getResource("/icones/icons8-maximizar-15.png")));
		lblMaximizar.setBounds(422, 5, 25, 24);
		painelCampos.add(lblMaximizar);
		
		lblMinizar = new JLabel("  ");
		lblMinizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 frame.setExtendedState(JFrame.ICONIFIED);
			}
		});
		lblMinizar.setIcon(new ImageIcon(TelaLoginUsuario.class.getResource("/icones/icons8-menos-30.png")));
		lblMinizar.setBounds(387, 3, 22, 30);
		painelCampos.add(lblMinizar);

	}

	public void tornarVisivelForaDoFrame() {
		frame.setVisible(true);

	}
}
