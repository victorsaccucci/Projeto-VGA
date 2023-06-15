package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.UsuarioController;
import model.ExceptionVGA;
import model.vo.UsuarioVO;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PainelListagemClientes extends JPanel {

	private static final long serialVersionUID = -1185570684789843566L;

	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEmail;
	
	private JTable tabelaUsuarios;
	private JButton btnBuscar;
	private ArrayList<UsuarioVO> usuarios;
	private UsuarioController usuarioController;
	
	private String[] nomesColunas = {"Nome", "Email", "Cpf", "Administrador"};
	private JButton btnBuscarTodos;

	private JButton btnEditar;

	private UsuarioVO usuarioSelecionado;

	private JButton btnExcluir;
	
	private void limparTabela() {
		tabelaUsuarios.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}
	
	private void atualizarTabelaClientes() {
		this.limparTabela();

		DefaultTableModel model = (DefaultTableModel) tabelaUsuarios.getModel();

		for (UsuarioVO vo : usuarios) {
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
		btnBuscar.setForeground(new Color(0, 139, 139));
		btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));
//		btnBuscar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				atualizarTabelaClientes();
//			}
//		});
		btnBuscar.setBounds(666, 98, 123, 34);
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
		txtCpf.setBounds(456, 44, 196, 28);
		add(txtCpf);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEmail.setBounds(42, 102, 45, 13);
		add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(97, 101, 555, 28);
		add(txtEmail);
		
		tabelaUsuarios = new JTable();
		tabelaUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indiceSelecionado = tabelaUsuarios.getSelectedRow();

				if (indiceSelecionado > 0) {
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					usuarioSelecionado = usuarios.get(indiceSelecionado - 1);
				} else {
					btnEditar.setEnabled(false);
					btnExcluir.setEnabled(true);
				}
			}
		});
		tabelaUsuarios.setBounds(23, 166, 902, 314);
		add(tabelaUsuarios);
		
		btnBuscarTodos = new JButton("Buscar todos");
		btnBuscarTodos.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnBuscarTodos.setForeground(new Color(0, 139, 139));
		btnBuscarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuarioController = new UsuarioController();
				usuarios = usuarioController.consultarTodosUsuariosController();
				atualizarTabelaClientes();
			}
		});

		btnBuscarTodos.setBounds(802, 98, 123, 34);
		this.add(btnBuscarTodos);
		
		btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnEditar.setForeground(new Color(0, 139, 139));
		btnEditar.setBounds(666, 490, 123, 34);
		add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int opcaoSelecionada = JOptionPane.showConfirmDialog(null,
						"Confirmar a exclusão do usuário selecionado?");
				
				if(opcaoSelecionada == JOptionPane.YES_OPTION) {
					try {
						usuarioController.excluirUsuarioController(usuarioSelecionado.getId());
						JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
						usuarios = usuarioController.consultarTodosUsuariosController();
						
						atualizarTabelaClientes();
					} catch (ExceptionVGA e1) {
						JOptionPane.showConfirmDialog(null, e1.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnExcluir.setForeground(new Color(0, 139, 139));
		btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnExcluir.setBounds(802, 490, 123, 34);
		add(btnExcluir);
	}
	public JButton getBtnEditar() {
		return this.btnEditar;
	}

	public UsuarioVO getUsuarioSelecionado() {
		return usuarioSelecionado;
	}
}
