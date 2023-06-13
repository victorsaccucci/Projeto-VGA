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
import java.awt.Color;

public class PainelCadastroItem extends JPanel {

	private static final long serialVersionUID = -3449651366973850921L;
	private JTextField txtTamanho;
	private JTextField txtCor;
	private JTextField txtQuantidade;
	private JTextField txtPreco;
	private JLabel lblProduto;
	private JLabel lblTamanho;
	private JLabel lblCor;
	private JLabel lblQtd;
	private JButton btnCadastrar;
	private JComboBox cbProduto;
	private JLabel lblPreco;
	
	private ItemController itemController;
	private ProdutoController produtoController;
	protected ItemVO novoItem;

	/**
	 * Create the panel.
	 */
	public PainelCadastroItem() {
		setBackground(new Color(0, 139, 139));
		setLayout(null);
		
		lblProduto = new JLabel("Produto:");
		lblProduto.setForeground(new Color(255, 255, 255));
		lblProduto.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblProduto.setBounds(213, 91, 59, 20);
		add(lblProduto);
		
		
		
		produtoController = new ProdutoController();
		cbProduto = new JComboBox(produtoController.consultarTodosProdutosController().toArray());
		
		cbProduto.setForeground(new Color(0, 139, 139));
		cbProduto.setBounds(305, 89, 358, 28);
		add(cbProduto);
		
		lblPreco = new JLabel("Preço:");
		lblPreco.setForeground(new Color(255, 255, 255));
		lblPreco.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPreco.setBounds(213, 322, 75, 20);
		add(lblPreco);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setForeground(new Color(0, 139, 139));
		txtQuantidade.setColumns(10);
		txtQuantidade.setBounds(305, 264, 358, 28);
		add(txtQuantidade);
		
		txtPreco = new JTextField();
		txtPreco.setForeground(new Color(0, 139, 139));
		txtPreco.setColumns(10);
		txtPreco.setBounds(305, 324, 358, 28);
		add(txtPreco);
		
		lblTamanho = new JLabel("Tamanho:");
		lblTamanho.setForeground(new Color(255, 255, 255));
		lblTamanho.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblTamanho.setBounds(213, 145, 68, 20);
		add(lblTamanho);
		
		txtTamanho = new JTextField();
		txtTamanho.setForeground(new Color(0, 139, 139));
		txtTamanho.setColumns(10);
		txtTamanho.setBounds(305, 143, 358, 28);
		add(txtTamanho);
		
		txtCor = new JTextField();
		txtCor.setForeground(new Color(0, 139, 139));
		txtCor.setColumns(10);
		txtCor.setBounds(305, 198, 358, 28);
		add(txtCor);
		
		lblCor = new JLabel("Cor:");
		lblCor.setForeground(new Color(255, 255, 255));
		lblCor.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblCor.setBounds(213, 204, 45, 13);
		add(lblCor);
		
		lblQtd = new JLabel("Quantidade:");
		lblQtd.setForeground(new Color(255, 255, 255));
		lblQtd.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblQtd.setBounds(213, 262, 84, 20);
		add(lblQtd);
		
		JButton btnCadastrarItem = new JButton("Cadastrar");
		btnCadastrarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemController = new ItemController();
				novoItem = new ItemVO();
				
				novoItem.setCor(txtCor.getText());
				novoItem.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
				novoItem.setPrecoUnitario(Double.parseDouble(txtPreco.getText()));
				novoItem.setTamanho(txtTamanho.getText());
				novoItem.setIdProduto(cbProduto.getSelectedIndex());
				
				try {
					itemController.inserirItem(novoItem);
					JOptionPane.showMessageDialog(null, "Item cadastrado com sucesso!", 
							"Sucesso", JOptionPane.INFORMATION_MESSAGE);
				}catch(ExceptionVGA e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), 
							"Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCadastrarItem.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnCadastrarItem.setForeground(new Color(0, 139, 139));
		btnCadastrarItem.setBounds(438, 381, 91, 27);
		add(btnCadastrarItem);
		
		
		

	}
}
