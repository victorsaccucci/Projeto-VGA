package view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ItemController;
import model.vo.ItemVO;
import model.vo.ProdutoVO;
import model.vo.UsuarioVO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class PainelListagemItens extends JPanel {

	public String[] nomeColunas = {"IdProduto", "Tamanho", "Cor", "Qtd", "Preço"};
	private JTable tabelaItens;
	private ProdutoVO[] produto;
	private List<ItemVO> item;
	private ItemController itemController;
	
	/**
	 * Create the panel.
	 */
	
	private void limparTabela() {
		tabelaItens.setModel(new DefaultTableModel(new Object[][] { nomeColunas, }, nomeColunas));
	}
	
	private void atualizarTabelaClientes() {
		this.limparTabela();

		DefaultTableModel model = (DefaultTableModel) tabelaItens.getModel();
		
			
			for( ItemVO vo : item) {
				Object[] novaLinhaDaTabela = new Object[5];
				novaLinhaDaTabela[0] = vo.getIdProduto();
				novaLinhaDaTabela[1] = vo.getTamanho();
				novaLinhaDaTabela[2] = vo.getCor();
				novaLinhaDaTabela[3] = vo.getQuantidade();
				novaLinhaDaTabela[4] = vo.getPrecoUnitario();
				model.addRow(novaLinhaDaTabela);
			}
		
		
	}
	
	public PainelListagemItens() {
		setBackground(new Color(0, 139, 139));
		setForeground(new Color(0, 139, 139));
		setLayout(null);
		
		tabelaItens = new JTable();
		tabelaItens.setBounds(57, 365, 554, -229);
		add(tabelaItens);
		
		JButton btnBuscarTodos = new JButton("Buscar Todos");
		btnBuscarTodos.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				item = itemController.consultarTodos();
				atualizarTabelaClientes();
			}
		});
		btnBuscarTodos.setBounds(507, 41, 97, 23);
		add(btnBuscarTodos);

		
		
	}
}
