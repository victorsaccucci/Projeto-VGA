package view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.ItemController;
import model.ExceptionVGA;
import model.seletor.SeletorItem;
import model.vo.ItemVO;
import model.vo.ProdutoVO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

public class PainelListagemItens extends JPanel {

	private static final long serialVersionUID = -826234024317345122L;

	public String[] nomeColunas = { "Tamanho", "Cor", "Qtd", "Preço" };
	private JTable tabelaItens;
	
	private List<ProdutoVO> produto;
	private List<ItemVO> item;
	
	private ItemController itemController;
	private JTextField txtMenorPreco;
	private JTextField txtMaiorPreco;
	private JTextField txtTamanho;
	private JTextField txtCor;
	private JLabel lblMenorPreco;
	private JLabel lblMaiorPreco;
	private JLabel lblTamanho;
	private JLabel lblCor;
	private JButton btnBuscar;
	private JButton btnBuscarTodos;
	private JFileChooser jfc;
	
	//Seletor
	private SeletorItem seletor;
	private ItemController itemControllerSeletor;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnGerarRelatorio;

	private void limparTabela() {
		tabelaItens.setModel(new DefaultTableModel(new Object[][] { nomeColunas, }, nomeColunas));
	}

	private void atualizarTabelaItens() {
		this.limparTabela();

		DefaultTableModel model = (DefaultTableModel) tabelaItens.getModel();

		for (ItemVO vo : item) {
			Object[] novaLinhaDaTabela = new Object[4];
			novaLinhaDaTabela[0] = vo.getTamanho();
			novaLinhaDaTabela[1] = vo.getCor();
			novaLinhaDaTabela[2] = vo.getQuantidade();
			novaLinhaDaTabela[3] = vo.getPrecoUnitario();
			model.addRow(novaLinhaDaTabela);
		}
	}

	public PainelListagemItens() {
		setBackground(new Color(0, 139, 139));
		setForeground(new Color(0, 139, 139));
		setLayout(null);

		lblMenorPreco = new JLabel("Menor Preço:");
		lblMenorPreco.setForeground(new Color(255, 255, 255));
		lblMenorPreco.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblMenorPreco.setBounds(57, 61, 118, 26);
		add(lblMenorPreco);

		lblMaiorPreco = new JLabel("Maior Preço:");
		lblMaiorPreco.setForeground(new Color(255, 255, 255));
		lblMaiorPreco.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblMaiorPreco.setBounds(57, 132, 118, 26);
		add(lblMaiorPreco);

		lblTamanho = new JLabel("Tamanho:");
		lblTamanho.setForeground(new Color(255, 255, 255));
		lblTamanho.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblTamanho.setBounds(366, 61, 77, 26);
		add(lblTamanho);

		lblCor = new JLabel("Cor:");
		lblCor.setForeground(new Color(255, 255, 255));
		lblCor.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblCor.setBounds(390, 132, 53, 26);
		add(lblCor);

		txtMenorPreco = new JTextField();
		txtMenorPreco.setBounds(156, 61, 99, 31);
		add(txtMenorPreco);
		txtMenorPreco.setColumns(10);

		txtMaiorPreco = new JTextField();
		txtMaiorPreco.setColumns(10);
		txtMaiorPreco.setBounds(156, 132, 99, 31);
		add(txtMaiorPreco);

		txtTamanho = new JTextField();
		txtTamanho.setColumns(10);
		txtTamanho.setBounds(443, 61, 55, 31);
		add(txtTamanho);

		txtCor = new JTextField();
		txtCor.setColumns(10);
		txtCor.setBounds(443, 132, 165, 31);
		add(txtCor);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnBuscar.setForeground(new Color(0, 139, 139));
		btnBuscar.setEnabled(false);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarItensComFiltro();
				atualizarTabelaItens();
			}
		});
		btnBuscar.setBounds(629, 131, 99, 31);
		add(btnBuscar);

		btnBuscarTodos = new JButton("Buscar Todos");
		btnBuscarTodos.setBackground(new Color(255, 255, 255));
		btnBuscarTodos.setForeground(new Color(0, 139, 139));
		btnBuscarTodos.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnBuscarTodos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				itemController = new ItemController();
				item = itemController.consultarTodos();
				atualizarTabelaItens();
			}
		});
		btnBuscarTodos.setBounds(738, 131, 149, 31);
		this.add(btnBuscarTodos);

		tabelaItens = new JTable();
		tabelaItens.setBounds(57, 193, 830, 177);
		add(tabelaItens);

		JLabel lblAte = new JLabel("Até");
		lblAte.setForeground(new Color(255, 255, 255));
		lblAte.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblAte.setBounds(57, 98, 36, 26);
		add(lblAte);
		
		btnEditar = new JButton("Editar");
		btnEditar.setForeground(new Color(0, 139, 139));
		btnEditar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnEditar.setEnabled(false);
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setBounds(629, 381, 123, 34);
		add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setForeground(new Color(0, 139, 139));
		btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setBounds(764, 381, 123, 34);
		add(btnExcluir);
		
		btnGerarRelatorio.setForeground(new Color(0, 139, 139));
		btnGerarRelatorio.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnGerarRelatorio.setBackground(Color.WHITE);
		btnGerarRelatorio.setBounds(485, 381, 123, 34);
		add(btnGerarRelatorio);
		
        txtMaiorPreco.getDocument().addDocumentListener(new MyDocumentListener());
        txtMenorPreco.getDocument().addDocumentListener(new MyDocumentListener());
        txtTamanho.getDocument().addDocumentListener(new MyDocumentListener());
        
        //TODO botao editar e excluir e as funções.

	}
	
	private class MyDocumentListener implements DocumentListener {
        public void insertUpdate(DocumentEvent e) {
            checarCampos();
        }

        public void removeUpdate(DocumentEvent e) {
            checarCampos();
        }

        public void changedUpdate(DocumentEvent e) {
            checarCampos();
        }
    }

	private void checarCampos() {
		if (!txtMenorPreco.getText().isEmpty() && !txtMaiorPreco.getText().isEmpty() && !txtTamanho.getText().isEmpty()) {
			btnBuscar.setEnabled(true);
		} else {
			btnBuscar.setEnabled(false);
		}
	}

	protected void buscarItensComFiltro() {
		itemControllerSeletor = new ItemController();
		
		seletor = new SeletorItem();
		seletor.setCor(txtCor.getText());
		seletor.setTamanho(Integer.parseInt(txtTamanho.getText()));
		seletor.setPrecoInicial(txtMenorPreco.getText());
		seletor.setPrecoFinal(txtMaiorPreco.getText());

		item = (List<ItemVO>) itemControllerSeletor.consultarComFiltros(seletor);
		atualizarTabelaItens();

	}
}
