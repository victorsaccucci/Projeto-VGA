package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.UsuarioController;
import model.ExceptionVGA;
import model.vo.UsuarioVO;

import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import java.awt.Checkbox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class PainelAdmCadastroUsuarios extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5562228851751479011L;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JFormattedTextField txtCpf;
	private JLabel lblCpf;
	private JLabel lblEmail;
	private JLabel lblNome;
	private Checkbox cbAdmSimNao;
	private JButton btnCadastrar;
	
	private UsuarioController usuarioController;
	private UsuarioVO novoUsuario;
	private JPasswordField txtSenha;
	
	private MaskFormatter mascaraCpf;

	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public PainelAdmCadastroUsuarios() throws ParseException {
		setLayout(null);
		
		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNome.setBounds(256, 92, 45, 13);
		add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(311, 87, 358, 28);
		add(txtNome);
		txtNome.setColumns(10);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEmail.setBounds(256, 146, 45, 13);
		add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(311, 141, 358, 28);
		add(txtEmail);
		
		mascaraCpf = new MaskFormatter("###.###.###-##");
		mascaraCpf.setValueContainsLiteralCharacters(false);
		
		lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCpf.setBounds(256, 205, 45, 13);
		add(lblCpf);
		
		txtCpf = new JFormattedTextField(mascaraCpf);
		txtCpf.setColumns(10);
		txtCpf.setBounds(311, 200, 358, 28);
		add(txtCpf);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSenha.setBounds(256, 263, 45, 13);
		add(lblSenha);
		
		cbAdmSimNao = new Checkbox("Administrador");
		cbAdmSimNao.setBounds(311, 302, 99, 21);
		add(cbAdmSimNao);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuarioController = new UsuarioController();
				novoUsuario = new UsuarioVO();
				
				novoUsuario.setNome(txtNome.getText());
				novoUsuario.setEmail(txtEmail.getText());
				novoUsuario.setCpf(txtCpf.getText());
				novoUsuario.setSenha(String.valueOf(txtSenha.getPassword()));
				
				if(cbAdmSimNao.getState() == true) {
					novoUsuario.setAdm(cbAdmSimNao.getState());
				}
				
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
					
				} catch (ExceptionVGA e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), 
							"Erro", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
		});
		btnCadastrar.setBounds(418, 349, 155, 39);
		add(btnCadastrar);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(311, 258, 358, 28);
		add(txtSenha);

	}
}
