package view;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.ExceptionVGA;
import model.vo.ItemVO;
import model.vo.VendaVO;
import controller.VendaController;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class PainelListagemVendas extends JPanel {

	public String[] nomeColunas = { "Data Venda", "Usuario", "Item"};
	private List<VendaVO> vendas;
	protected VendaController vendaController;
	private JTable tabelaVendas;
	private JFileChooser jfc;
	
	private void limparTabela() {
		tabelaVendas.setModel(new DefaultTableModel(new Object[][] { nomeColunas, }, nomeColunas));
	}
	
	private void atualizarTabelaItens() {
		this.limparTabela();

		DefaultTableModel model = (DefaultTableModel) tabelaVendas.getModel();

		for (VendaVO vo : vendas) {
			Object[] novaLinhaDaTabela = new Object[3];
			novaLinhaDaTabela[0] = vo.getDataVenda();
			novaLinhaDaTabela[1] = vo.getIdUsuario();
			novaLinhaDaTabela[2] = vo.getIdItem();
			model.addRow(novaLinhaDaTabela);
		}
	}
	
	/**
	 * Create the panel.
	 */
	public PainelListagemVendas() {
		setBackground(new Color(0, 139, 139));
		setForeground(new Color(0, 139, 139));
		setLayout(null);
		
		JButton btnBuscarTodos = new JButton("Buscar Todos");
		btnBuscarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vendaController = new VendaController();
				vendas = vendaController.consultarTodos();
				atualizarTabelaItens();
			}
		});
		btnBuscarTodos.setForeground(new Color(0, 139, 139));
		btnBuscarTodos.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnBuscarTodos.setBounds(663, 395, 127, 27);
		add(btnBuscarTodos);
		
		tabelaVendas = new JTable();
		tabelaVendas.setBounds(79, 104, 721, 258);
		add(tabelaVendas);
		
		JButton btnRelatorio = new JButton("Rel�torio");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jfc = new JFileChooser();
				jfc.setDialogTitle("Salvar Relatorio como...");
				
				int opcaoSelecionada = jfc.showSaveDialog(null);
				if(opcaoSelecionada == JFileChooser.APPROVE_OPTION) {
					String caminhoEscolhido = jfc.getSelectedFile().getAbsolutePath();
					String resultado;
					try {
						resultado = vendaController.gerarPlanilha(vendas, caminhoEscolhido);
						JOptionPane.showMessageDialog(null, resultado);
					} catch (ExceptionVGA e1) {
						JOptionPane.showConfirmDialog(null, e1.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
					}
				}
			
			}
		});
		btnRelatorio.setForeground(new Color(0, 139, 139));
		btnRelatorio.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnRelatorio.setBounds(526, 395, 127, 27);
		add(btnRelatorio);

	}
}
