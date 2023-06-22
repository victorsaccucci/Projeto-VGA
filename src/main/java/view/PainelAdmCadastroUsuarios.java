package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
import java.awt.Color;

public class PainelAdmCadastroUsuarios extends JPanel {
	
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
	private UsuarioVO usuario;
	private JPasswordField txtSenha;
	private MaskFormatter mascaraCpf;
	private JLabel lblTitulo;

	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public PainelAdmCadastroUsuarios(UsuarioVO usuarioParaEditar) throws ParseException {
		if(usuarioParaEditar == null) {
			this.usuario = new UsuarioVO();
		}else {
			this.usuario = usuarioParaEditar;
		}
		
		setBackground(new Color(0, 139, 139));
		setLayout(null);
		
		lblTitulo = new JLabel(usuario.getId() == null ? "NOVO CLIENTE" : "EDIÇÃO DE CLIENTE");
		lblTitulo.setBounds(438, 36, 231, 13);
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblTitulo, "4, 2, 9, 1, center, default");
		
		lblNome = new JLabel("Nome:");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNome.setBounds(256, 92, 45, 13);
		add(lblNome);
		
		txtNome = new JTextField(); 
		txtNome.setBounds(311, 87, 358, 28);
		add(txtNome);
		txtNome.setColumns(10);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblEmail.setBounds(256, 146, 45, 13);
		add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(311, 141, 358, 28);
		add(txtEmail);
		
		mascaraCpf = new MaskFormatter("###.###.###-##");
		mascaraCpf.setValueContainsLiteralCharacters(false);
		
		lblCpf = new JLabel("CPF:");
		lblCpf.setForeground(new Color(255, 255, 255));
		lblCpf.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblCpf.setBounds(256, 205, 45, 13);
		add(lblCpf);
		
		txtCpf = new JFormattedTextField(mascaraCpf);
		txtCpf.setColumns(10);
		txtCpf.setBounds(311, 200, 358, 28);
		add(txtCpf);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblSenha.setBounds(256, 263, 46, 20);
		add(lblSenha);
		
		cbAdmSimNao = new Checkbox("Administrador");
		cbAdmSimNao.setForeground(new Color(255, 255, 255));
		cbAdmSimNao.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cbAdmSimNao.setBounds(311, 302, 116, 22);
		add(cbAdmSimNao);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuarioController = new UsuarioController();
				usuario = new UsuarioVO();
				
				usuario.setNome(txtNome.getText());
				usuario.setEmail(txtEmail.getText());
				usuario.setCpf(txtCpf.getText());
				usuario.setSenha(String.valueOf(txtSenha.getPassword()));
				
				if(cbAdmSimNao.getState() == true) {
					usuario.setAdm(cbAdmSimNao.getState());
				}
				
				try {
					String cpfSemMascara = (String) mascaraCpf.stringToValue(
							txtCpf.getText());
					usuario.setCpf(cpfSemMascara);
				} catch (ParseException e1) {
			
				}
				
				try {
					//TODO verificar o id -> se tiver (atualizar), senão (cadastrar)
					if(usuario.getId() == null) {
						usuarioController.cadastrarUsuarioController(usuario);
					} else {
						usuarioController.atualizarUsuarioController(usuario);
					}
					
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
		
		if(this.usuario != null) {
			preencherCamposDaTela();
		}
		
	}
	
	private void preencherCamposDaTela() {
		this.txtNome.setText(this.usuario.getNome());
		this.txtEmail.setText(this.usuario.getEmail());
		this.txtCpf.setText(this.usuario.getCpf());
		this.txtSenha.setText(this.usuario.getSenha());
		this.btnCadastrar.setText("Salvar");
	}
}
