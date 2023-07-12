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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

	private boolean novoCliente;
	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public PainelAdmCadastroUsuarios(UsuarioVO usuarioParaEditar) throws ParseException {
		if(usuarioParaEditar == null) {
			novoCliente = true;
			this.usuario = new UsuarioVO();
		}else {
			novoCliente = false;
			this.usuario = usuarioParaEditar;
		}
		
		setBackground(new Color(0, 139, 139));
		setLayout(null);
		
		lblTitulo = new JLabel(usuario.getId() == null ? "NOVO CLIENTE" : "EDIÇÃO DE CLIENTE");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblTitulo.setBounds(311, 63, 358, 13);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
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
		btnCadastrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnCadastrar.setBackground(new Color(255, 255, 255));
		btnCadastrar.setForeground(new Color(0, 139, 139));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuarioController = new UsuarioController();
				
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
				
			
					 if (!validarEmail(usuario.getEmail())) {
				            JOptionPane.showMessageDialog(null, "Verifique se o e-mail é válido ou se foi preenchido!", "Erro de validação",
				                    JOptionPane.ERROR_MESSAGE);
				        } else {
				            StringBuilder mensagemErro = new StringBuilder();
				            
				            if (!usuario.getSenha().matches(".*[a-z].*")) {
				                mensagemErro.append("- A senha deve conter pelo menos uma letra minúscula.\n");
				            }
				            if (!usuario.getSenha().matches(".*[A-Z].*")) {
				                mensagemErro.append("- A senha deve conter pelo menos uma letra maiúscula.\n");
				            }
				            if (!usuario.getSenha().matches(".*\\d.*")) {
				                mensagemErro.append("- A senha deve conter pelo menos um número.\n");
				            }
				            if (usuario.getSenha().length() < 6) {
				                mensagemErro.append("- A senha deve ter pelo menos 6 caracteres.\n");
				            }

				            if (mensagemErro.length() > 0) {
				                JOptionPane.showMessageDialog(null, mensagemErro.toString(), "Erro de validação",
				                        JOptionPane.ERROR_MESSAGE);
				            } else {
				            	if(novoCliente) {
				            		try {
										usuarioController.cadastrarUsuarioController(usuario);
									} catch (ExceptionVGA e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									JOptionPane.showMessageDialog(null, "Cliente SALVO com sucesso!", 
											"Sucesso", JOptionPane.INFORMATION_MESSAGE);
									limparAposCadastrar();
				            	} else {
				            		usuarioController.atualizarUsuarioController(usuario);
									JOptionPane.showMessageDialog(null, "Cliente ATUALIZADO com sucesso!", 
											"Sucesso", JOptionPane.INFORMATION_MESSAGE);
									limparAposCadastrar();
				            	}
				            }
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
	
	private boolean validarEmail(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	private void preencherCamposDaTela() {
		this.txtNome.setText(this.usuario.getNome());
		this.txtEmail.setText(this.usuario.getEmail());
		this.txtCpf.setText(this.usuario.getCpf());
		this.txtSenha.setText(this.usuario.getSenha());
		this.btnCadastrar.setText("Salvar");
	}
	
	private void limparAposCadastrar() {
		this.txtNome.setText(null);
		this.txtEmail.setText(null);
		this.txtCpf.setText(null);
		this.txtSenha.setText(null);
	}
}
