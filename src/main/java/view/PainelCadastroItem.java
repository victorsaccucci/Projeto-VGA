package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import controller.ItemController;
import controller.ProdutoController;
import model.ExceptionVGA;
import model.vo.ItemVO;
import model.vo.ProdutoVO;

import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PainelCadastroItem extends JPanel {
	private JTextField txtTam;
	private JTextField txtCor;
	private JTextField txtQtd;
	private JTextField txtPreco;
	private JLabel lblProduto;
	private JLabel lblTamanho;
	private JLabel lblCor;
	private JLabel lblQtd;
	private JButton btnCadastrar;
	private JComboBox cbProduto;
	private JLabel lblPreco;
	private ItemController itemController;
	private ItemVO novoItem;
	private JButton btnCadastrar1;

	/**
	 * Create the panel.
	 */
	public PainelCadastroItem() {
		setLayout(null);
		
		lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblProduto.setBounds(186, 26, 53, 20);
		add(lblProduto);
		
		
		
		ProdutoController controller = new ProdutoController();
		cbProduto = new JComboBox(controller.consultarTodosProdutosController().toArray());
		cbProduto.setBounds(278, 27, 358, 22);
		add(cbProduto);
		
		lblPreco = new JLabel("Preço:");
		lblPreco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPreco.setBounds(186, 257, 75, 20);
		add(lblPreco);
		
		txtQtd = new JTextField();
		txtQtd.setColumns(10);
		txtQtd.setBounds(278, 199, 358, 28);
		add(txtQtd);
		
		txtPreco = new JTextField();
		txtPreco.setColumns(10);
		txtPreco.setBounds(278, 259, 358, 28);
		add(txtPreco);
		
		lblTamanho = new JLabel("Tamanho:");
		lblTamanho.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTamanho.setBounds(186, 80, 60, 20);
		add(lblTamanho);
		
		txtTam = new JTextField();
		txtTam.setColumns(10);
		txtTam.setBounds(278, 78, 358, 28);
		add(txtTam);
		
		txtCor = new JTextField();
		txtCor.setColumns(10);
		txtCor.setBounds(278, 133, 358, 28);
		add(txtCor);
		
		lblCor = new JLabel("Cor:");
		lblCor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCor.setBounds(186, 139, 45, 13);
		add(lblCor);
		
		lblQtd = new JLabel("Quantidade:");
		lblQtd.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblQtd.setBounds(186, 197, 75, 20);
		add(lblQtd);
		
		btnCadastrar1 = new JButton("Cadastrar");
		btnCadastrar1.setBounds(347, 338, 155, 39);
		add(btnCadastrar1);
		btnCadastrar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemController = new ItemController();
				novoItem = new ItemVO();
				
				ProdutoVO produtoSelecionado = (ProdutoVO) cbProduto.getSelectedItem();
				novoItem.setIdProduto(produtoSelecionado.getId());
				novoItem.setTamanho(txtTam.getText());
				novoItem.setCor(txtCor.getText());
				novoItem.setQuantidade(Integer.parseInt(txtQtd.getText()));
				novoItem.setPrecoUnitario(Double.parseDouble(txtPreco.getText()));
				
				try {
					itemController.inserirItem(novoItem);
					JOptionPane.showMessageDialog(null, "Item salvo com sucesso!", 
							"Sucesso", JOptionPane.INFORMATION_MESSAGE);
				} catch (ExceptionVGA e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), 
							"Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCadastrar1.setBounds(381, 357, 126, 28);
		add(btnCadastrar1);
		
		
		

	}
}
