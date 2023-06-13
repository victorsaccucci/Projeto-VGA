package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import controller.UsuarioController;
import model.vo.UsuarioVO;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PainelListagemClientes extends JPanel {

	private static final long serialVersionUID = -1185570684789843566L;

	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEmail;
	
	private JTable tabelaUsuarios;
	private JButton btnBuscar;
	private ArrayList<UsuarioVO> usuario;
	private UsuarioController usuarioController;
	
	private String[] nomesColunas = {"Nome", "Email", "Cpf", "Administrador"};
	private JButton btnBuscarTodos;
	
	private void limparTabela() {
		tabelaUsuarios.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}
	
	private void atualizarTabelaClientes() {
		this.limparTabela();

		DefaultTableModel model = (DefaultTableModel) tabelaUsuarios.getModel();

		for (UsuarioVO vo : usuario) {
			Object[] novaLinhaDaTabela = new Object[4];
			novaLinhaDaTabela[0] = vo.getNome();
			novaLinhaDaTabela[1] = vo.getEmail();
			novaLinhaDaTabela[2] = vo.getCpf();
			novaLinhaDaTabela[3] = vo.isAdm() ? "Sim" : "Não";
			model.addRow(novaLinhaDaTabela);
		}
	}
	

	public PainelListagemClientes() {
		setBackground(new Color(0, 139, 139));
		setLayout(null);
		
		btnBuscar = new JButton("Buscar");
//		btnBuscar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				atualizarTabelaClientes();
//			}
//		});
		btnBuscar.setBounds(651, 102, 85, 27);
		add(btnBuscar);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNome.setBounds(42, 49, 45, 13);
		add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(97, 44, 283, 28);
		add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CPF:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(418, 45, 45, 20);
		add(lblNewLabel);
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBounds(456, 44, 160, 28);
		add(txtCpf);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEmail.setBounds(42, 102, 45, 13);
		add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(97, 101, 519, 28);
		add(txtEmail);
		
		tabelaUsuarios = new JTable();
		tabelaUsuarios.setBounds(23, 166, 902, 324);
		add(tabelaUsuarios);
		
		btnBuscarTodos = new JButton("Buscar todos");
		btnBuscarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuarioController = new UsuarioController();
				usuario = usuarioController.consultarTodosUsuariosController();
				atualizarTabelaClientes();
			}
		});
		btnBuscarTodos.setBounds(763, 102, 113, 27);
		this.add(btnBuscarTodos);
	}
}
