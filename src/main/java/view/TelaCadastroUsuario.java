package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
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
		
		btnCadastrar = new JButton("Cadastrar");
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
				JOptionPane.showMessageDialog(null, "Usuario salvo com sucesso!", 
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
		btnCadastrar.setBounds(184, 457, 141, 23);
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
		
		JPanel panel_1 = new JPanel();
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
		lblNewLabel.setIcon(new ImageIcon(TelaCadastroUsuario.class.getResource("/icones/icons8-voltar-30.png")));
		lblNewLabel.setBounds(0, 0, 48, 48);
		panel_1.add(lblNewLabel);
	}

	public void tornarVisivelForaDoFrame() {
		frame.setVisible(true);
	}
}
