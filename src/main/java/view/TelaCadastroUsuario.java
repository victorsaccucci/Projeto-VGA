package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.UsuarioController;
import model.ExceptionVGA;
import model.vo.UsuarioVO;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class TelaCadastroUsuario {

	private JFrame frame;
	private JTextField txtNome;
	private JFormattedTextField txtCpf;
	private JPasswordField txtSenha;
	private JButton btnCadastrar;
	private JLabel lblSenha;
	private JLabel lblCpf;
	private JLabel lblEmail;
	private JLabel lblNome;
	private JLabel lblCadastro;
	private JLabel lblFechar;
	private JPanel panel;
	private UsuarioController usuarioController;
	private UsuarioVO novoUsuario;
	private MaskFormatter mascaraCpf;
	private JTextField txtEmail;
	private JLabel lblNewLabel;
	private TelaLoginUsuario telaLogin;
	private JLabel lblLogo;
	private JLabel lblMostrar;
	private JLabel lblNaoMostrar;
	private JLabel lblMaximizar;
	private JLabel lblMinimizar;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroUsuario window = new TelaCadastroUsuario();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ParseException 
	 */
	public TelaCadastroUsuario() throws ParseException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException 
	 */
	private void initialize() throws ParseException {
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
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 139, 139));
		panel.setBounds(471, 0, 475, 585);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		lblFechar = new JLabel("  X");
		lblFechar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblFechar.setForeground(new Color(255, 255, 255));
		lblFechar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblFechar.setBounds(449, 0, 26, 22);
		panel.add(lblFechar);
		
		lblMaximizar = new JLabel("  ");
		lblMaximizar.setBounds(881, 534, 25, 24);
		frame.getContentPane().add(lblMaximizar);

		lblMinimizar = new JLabel("  ");
		lblMinimizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 frame.setExtendedState(JFrame.ICONIFIED);
			}
		});
		lblMinimizar.setForeground(new Color(255, 255, 255));
		lblMinimizar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblMinimizar.setBackground(new Color(255, 255, 255));
		lblMinimizar.setBounds(700, 522, 8, 20);
		frame.getContentPane().add(lblMinimizar);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaDeProdutos.class.getResource("/icones/icons8-maximizar-10.png")));
		lblNewLabel_1.setBounds(896, 6, 15, 15);
		frame.getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 frame.setExtendedState(JFrame.ICONIFIED);
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon(TelaDeProdutos.class.getResource("/icones/icons8-minimizar-15.png")));
		lblNewLabel_2.setBounds(867, 0, 16, 27);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(TelaCadastroUsuario.class.getResource("/icones/icons8-minimizar-15.png")));
		lblNewLabel_3.setBounds(402, 8, 20, 14);
		panel.add(lblNewLabel_3);
		
		mascaraCpf = new MaskFormatter("###.###.###-##");
		mascaraCpf.setValueContainsLiteralCharacters(false);
		
		lblCadastro = new JLabel("Cadastro");
		lblCadastro.setForeground(new Color(255, 255, 255));
		lblCadastro.setFont(new Font("Yu Gothic Medium", Font.BOLD, 59));
		lblCadastro.setBounds(106, 56, 261, 95);
		panel.add(lblCadastro);
		
		lblNome = new JLabel("Nome");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setBackground(Color.WHITE);
		lblNome.setBounds(63, 150, 46, 17);
		panel.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setSelectionColor(new Color(0, 128, 128));
		txtNome.setSelectedTextColor(new Color(0, 128, 128));
		txtNome.setForeground(new Color(0, 128, 128));
		txtNome.setDisabledTextColor(new Color(0, 128, 128));
		txtNome.setColumns(10);
		txtNome.setBackground(Color.WHITE);
		txtNome.setBounds(63, 177, 347, 19);
		panel.add(txtNome);
		
		lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(63, 223, 46, 17);
		panel.add(lblEmail);
		
		lblCpf = new JLabel("Cpf");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCpf.setBackground(Color.WHITE);
		lblCpf.setBounds(63, 297, 46, 17);
		panel.add(lblCpf);
		
		txtCpf = new JFormattedTextField(mascaraCpf);
		txtCpf.setSelectionColor(new Color(0, 128, 128));
		txtCpf.setSelectedTextColor(new Color(0, 128, 128));
		txtCpf.setForeground(new Color(0, 128, 128));
		txtCpf.setDisabledTextColor(new Color(0, 128, 128));
		txtCpf.setColumns(10);
		txtCpf.setBackground(Color.WHITE);
		txtCpf.setBounds(63, 324, 347, 19);
		panel.add(txtCpf);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSenha.setBounds(63, 369, 46, 17);
		panel.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setForeground(new Color(0, 128, 128));
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtSenha.setBounds(63, 397, 347, 19);
		panel.add(txtSenha);
		
		btnCadastrar = new JButton("Cadastrar-se");
		btnCadastrar.setBackground(new Color(255, 255, 255));
		btnCadastrar.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
			novoUsuario = new UsuarioVO();
			usuarioController = new UsuarioController();
			
			novoUsuario.setNome(txtNome.getText());
			novoUsuario.setEmail(txtEmail.getText());
			novoUsuario.setSenha(String.valueOf(txtSenha.getPassword()));
			
			try {
				String cpfSemMascara = (String) mascaraCpf.stringToValue(
						txtCpf.getText());
				novoUsuario.setCpf(cpfSemMascara);
			} catch (ParseException e1) {
		
			}
			
			try {
				usuarioController.cadastrarUsuarioController(novoUsuario);
				JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!", 
						"Sucesso", JOptionPane.INFORMATION_MESSAGE);
				telaLogin = new TelaLoginUsuario();
				telaLogin.tornarVisivelForaDoFrame();
				
			} catch (ExceptionVGA e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), 
						"Erro", JOptionPane.ERROR_MESSAGE);
			}
			
			}
		});
		btnCadastrar.setForeground(new Color(1, 139, 139));
		btnCadastrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnCadastrar.setBounds(184, 457, 141, 29);
		panel.add(btnCadastrar);
		
		txtEmail = new JTextField();
		txtEmail.setSelectionColor(new Color(0, 128, 128));
		txtEmail.setSelectedTextColor(new Color(0, 128, 128));
		txtEmail.setForeground(new Color(0, 128, 128));
		txtEmail.setDisabledTextColor(new Color(0, 128, 128));
		txtEmail.setColumns(10);
		txtEmail.setBackground(Color.WHITE);
		txtEmail.setBounds(63, 251, 347, 19);
		panel.add(txtEmail);
		
		lblMostrar = new JLabel("");
		lblMostrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSenha.setEchoChar((char) 8226);

				lblMostrar.setVisible(false);
				lblMostrar.setEnabled(false);

				lblNaoMostrar.setVisible(true);
				lblNaoMostrar.setEnabled(true);
			}
		});
		lblMostrar.setIcon(new ImageIcon(TelaCadastroUsuario.class.getResource("/icones/icons8_eye_20px_1.png")));
		lblMostrar.setBounds(420, 397, 20, 20);
		panel.add(lblMostrar);
		
		lblNaoMostrar = new JLabel("");
		lblNaoMostrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSenha.setEchoChar((char) 0);

				lblMostrar.setVisible(true);
				lblMostrar.setEnabled(true);

				lblNaoMostrar.setVisible(false);
				lblNaoMostrar.setEnabled(false);
			}
		});
		lblNaoMostrar.setIcon(new ImageIcon(TelaCadastroUsuario.class.getResource("/icones/icons8_invisible_20px_1.png")));
		lblNaoMostrar.setBounds(420, 397, 20, 20);
		panel.add(lblNaoMostrar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(207, 243, 242));
		panel_1.setBounds(0, 0, 475, 585);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				telaLogin = new TelaLoginUsuario();
				telaLogin.tornarVisivelForaDoFrame();
				frame.setVisible(false);
			}
		});
		lblNewLabel.setIcon(new ImageIcon(TelaCadastroUsuario.class.getResource("/icones/icons8-voltar-40.png")));
		lblNewLabel.setBounds(0, 0, 48, 48);
		panel_1.add(lblNewLabel);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaCadastroUsuario.class.getResource("/icones/LogoVGA3.png")));
		lblLogo.setBounds(79, 78, 328, 411);
		panel_1.add(lblLogo);
		
		
	}

	public void tornarVisivelForaDoFrame() {
		frame.setVisible(true);
	}
}
