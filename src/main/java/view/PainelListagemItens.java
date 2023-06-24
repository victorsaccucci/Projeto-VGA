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
import model.seletor.SeletorProduto;
import model.vo.ItemVO;
import model.vo.ProdutoVO;
import model.vo.UsuarioVO;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

public class PainelListagemItens extends JPanel {

	private static final long serialVersionUID = -826234024317345122L;

	public String[] nomeColunas = { "Marca", "Modelo", "Tamanho", "Cor", "Qtd", "Preço" };
	private JTable tabelaItens;
	
	private List<ProdutoVO> produto;
	private List<ItemVO> itens;
	
	private ItemVO itemSelecionado;
	
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
	private SeletorProduto seletorProduto;
	private SeletorItem seletor;
	private ItemController itemControllerSeletor;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnGerarRelatorio;
	private JTextField txtMarca;
	private JTextField txtModelo;

	private void limparTabela() {
		tabelaItens.setModel(new DefaultTableModel(new Object[][] { nomeColunas, }, nomeColunas));
	}

	private void atualizarTabelaItens() {
		this.limparTabela();

		DefaultTableModel model = (DefaultTableModel) tabelaItens.getModel();

		for (ItemVO vo : itens) {
			Object[] novaLinhaDaTabela = new Object[6];
			novaLinhaDaTabela[0] = vo.getProduto().getMarca();
			novaLinhaDaTabela[1] = vo.getProduto().getModelo();
			novaLinhaDaTabela[2] = vo.getTamanho();
			novaLinhaDaTabela[3] = vo.getCor();
			novaLinhaDaTabela[4] = vo.getQuantidade();
			novaLinhaDaTabela[5] = vo.getPrecoUnitario();
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
		lblTamanho.setBounds(282, 61, 77, 26);
		add(lblTamanho);

		lblCor = new JLabel("Cor:");
		lblCor.setForeground(new Color(255, 255, 255));
		lblCor.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblCor.setBounds(282, 132, 53, 26);
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
		txtTamanho.setBounds(359, 61, 61, 31);
		add(txtTamanho);

		txtCor = new JTextField();
		txtCor.setColumns(10);
		txtCor.setBounds(321, 132, 99, 31);
		add(txtCor);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(new Color(255, 255, 255));
		btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnBuscar.setForeground(new Color(0, 139, 139));
		btnBuscar.setEnabled(false);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarItensComFiltro();
				atualizarTabelaItens();
			}
		});
		btnBuscar.setBounds(764, 89, 99, 31);
		add(btnBuscar);

		btnBuscarTodos = new JButton("Buscar Todos");
		btnBuscarTodos.setBackground(new Color(255, 255, 255));
		btnBuscarTodos.setForeground(new Color(0, 139, 139));
		btnBuscarTodos.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnBuscarTodos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				itemController = new ItemController();
				itens = itemController.consultarTodos();
				atualizarTabelaItens();
			}
		});
		btnBuscarTodos.setBounds(738, 131, 149, 31);
		this.add(btnBuscarTodos);

		tabelaItens = new JTable();
		tabelaItens.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indiceSelecionado = tabelaItens.getSelectedRow();

				if (indiceSelecionado > 0) {
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					itemSelecionado = itens.get(indiceSelecionado - 1);
				} else {
					btnEditar.setEnabled(false);
					btnExcluir.setEnabled(true);
				}
			}
		});
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
		this.add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBackground(new Color(255, 255, 255));
		btnExcluir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int opcaoSelecionada = JOptionPane.showConfirmDialog(null,
						"Confirmar a exclusão do item selecionado?");

				if (opcaoSelecionada == JOptionPane.YES_OPTION) {
					try {
						itemController.excluirItemController(itemSelecionado.getId());
						JOptionPane.showMessageDialog(null, "Item excluído com sucesso!");
						itens = itemController.consultarTodos();

						atualizarTabelaItens();
					} catch (ExceptionVGA e1) {
						JOptionPane.showConfirmDialog(null, e1.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnExcluir.setForeground(new Color(0, 139, 139));
		btnExcluir.setBounds(764, 381, 123, 34);
		add(btnExcluir);
		
		btnGerarRelatorio = new JButton("Relatorio");
		btnGerarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jfc = new JFileChooser();
				jfc.setDialogTitle("Salvar Relatorio como...");
				
				int opcaoSelecionada = jfc.showSaveDialog(null);
				if(opcaoSelecionada == JFileChooser.APPROVE_OPTION) {
					String caminhoEscolhido = jfc.getSelectedFile().getAbsolutePath();
					String resultado;
					try {
						resultado = itemController.gerarPlanilha(itens, caminhoEscolhido);
						JOptionPane.showMessageDialog(null, resultado);
					} catch (ExceptionVGA e1) {
						JOptionPane.showConfirmDialog(null, e1.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnGerarRelatorio.setForeground(new Color(0, 139, 139));
		btnGerarRelatorio.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnGerarRelatorio.setBackground(Color.WHITE);
		btnGerarRelatorio.setBounds(75, 430, 123, 34);
		add(btnGerarRelatorio);
		
		btnGerarRelatorio.setForeground(new Color(0, 139, 139));
		btnGerarRelatorio.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnGerarRelatorio.setBackground(Color.WHITE);
		btnGerarRelatorio.setBounds(485, 381, 123, 34);
		add(btnGerarRelatorio);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setForeground(Color.WHITE);
		lblMarca.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblMarca.setBounds(463, 61, 53, 26);
		add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		txtMarca.setBounds(526, 61, 99, 31);
		add(txtMarca);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setForeground(Color.WHITE);
		lblModelo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblModelo.setBounds(455, 134, 67, 26);
		add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setColumns(10);
		txtModelo.setBounds(526, 132, 99, 31);
		add(txtModelo);
		
		//Checa se os campos estão sendo preenchidos
        txtMaiorPreco.getDocument().addDocumentListener(new MyDocumentListener());
        txtMenorPreco.getDocument().addDocumentListener(new MyDocumentListener());
        txtTamanho.getDocument().addDocumentListener(new MyDocumentListener());
        txtCor.getDocument().addDocumentListener(new MyDocumentListener());
        txtMarca.getDocument().addDocumentListener(new MyDocumentListener());
        txtModelo.getDocument().addDocumentListener(new MyDocumentListener());
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
		if (!txtMenorPreco.getText().isEmpty() && !txtMaiorPreco.getText().isEmpty() || !txtTamanho.getText().isEmpty() 
				|| !txtCor.getText().isEmpty() || !txtMarca.getText().isEmpty() || !txtModelo.getText().isEmpty()){
			btnBuscar.setEnabled(true);
		}else {
			btnBuscar.setEnabled(false);
		}
	}

	protected void buscarItensComFiltro() {
		itemControllerSeletor = new ItemController();
		
		seletor = new SeletorItem();
		seletor.setCor(txtCor.getText());
		seletor.setTamanho(txtTamanho.getText());
		seletor.setPrecoInicial(txtMenorPreco.getText());
		seletor.setPrecoFinal(txtMaiorPreco.getText());
		
		seletorProduto = new SeletorProduto();
		seletorProduto.setMarca(txtMarca.getText());
		seletorProduto.setModelo(txtModelo.getText());
		itens = (List<ItemVO>) itemControllerSeletor.consultarComFiltros(seletor, seletorProduto);
		atualizarTabelaItens();

	}
	
	public JButton getBtnEditar() {
		return btnEditar;
	}

	public ItemVO getItemSelecionado() {
		return itemSelecionado;
	}
}
