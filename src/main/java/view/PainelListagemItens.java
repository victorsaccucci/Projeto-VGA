package view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ItemController;
import model.seletor.SeletorItem;
import model.vo.ItemVO;
import model.vo.ProdutoVO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class PainelListagemItens extends JPanel {

	
	private static final long serialVersionUID = -826234024317345122L;
	
	public String[] nomeColunas = {"Tamanho", "Cor", "Qtd", "Preço"};
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

	private SeletorItem seletor;
	
	
	/**
	 * Create the panel.
	 */
	
	private void limparTabela() {
		tabelaItens.setModel(new DefaultTableModel(new Object[][] { nomeColunas, }, nomeColunas));
	}
	
	private void atualizarTabelaItens() {
		this.limparTabela();

		DefaultTableModel model = (DefaultTableModel) tabelaItens.getModel();
		
			for( ItemVO vo : item) {
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
		lblMenorPreco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblMenorPreco.setBounds(57, 61, 89, 26);
		add(lblMenorPreco);
		
		lblMaiorPreco = new JLabel("Maior Preço:");
		lblMaiorPreco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblMaiorPreco.setBounds(57, 132, 92, 26);
		add(lblMaiorPreco);
		
		lblTamanho = new JLabel("Tamanho:");
		lblTamanho.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTamanho.setBounds(366, 61, 67, 26);
		add(lblTamanho);
		
		lblCor = new JLabel("Cor:");
		lblCor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCor.setBounds(390, 132, 43, 26);
		add(lblCor);
		
		txtMenorPreco = new JTextField();
		txtMenorPreco.setBounds(147, 61, 99, 31);
		add(txtMenorPreco);
		txtMenorPreco.setColumns(10);
		
		txtMaiorPreco = new JTextField();
		txtMaiorPreco.setColumns(10);
		txtMaiorPreco.setBounds(147, 132, 99, 31);
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
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarItensComFiltro();
				atualizarTabelaItens();
			}
		});
		btnBuscar.setBounds(648, 132, 89, 31);
		add(btnBuscar);
		
		btnBuscarTodos = new JButton("Buscar Todos");
		btnBuscarTodos.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				itemController = new ItemController();
				item = itemController.consultarTodos();
				atualizarTabelaItens();
			}
		});
		btnBuscarTodos.setBounds(759, 132, 128, 31);
		this.add(btnBuscarTodos);
		
		tabelaItens = new JTable();
		tabelaItens.setBounds(57, 193, 830, 340);
		add(tabelaItens);
		
		JLabel lblAte = new JLabel("Até");
		lblAte.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblAte.setBounds(57, 98, 36, 26);
		add(lblAte);
		
	}
	protected void buscarItensComFiltro() {
		seletor = new SeletorItem();
		seletor.setPrecoInicial(txtMenorPreco.getText());
		seletor.setPrecoFinal(txtMaiorPreco.getText());
		ItemController itemController2 = new ItemController();
		item = (List<ItemVO>) itemController2.consultarComFiltros(seletor);
		atualizarTabelaItens();
		
	}
}
