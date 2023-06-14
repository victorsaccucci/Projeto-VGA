package view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import controller.ProdutoController;
import model.ExceptionVGA;
import model.vo.ProdutoVO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PainelCadastroDeProduto extends JPanel {

	private static final long serialVersionUID = -1049818762473417992L;
	private JTextField txtModelo;
	private JTextField txtMarca;
	private JButton btnCadastrar;
	private JLabel lblMarca;
	private JLabel lblModelo;
	
	private ProdutoVO novoProduto;
	private ProdutoController produtoController;

	public PainelCadastroDeProduto() {
		setBackground(new Color(0, 139, 139));
		setLayout(null);
		
		lblModelo = new JLabel("Modelo:");
		lblModelo.setForeground(Color.WHITE);
		lblModelo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblModelo.setBounds(234, 156, 68, 20);
		add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setForeground(new Color(0, 139, 139));
		txtModelo.setColumns(10);
		txtModelo.setBounds(312, 155, 358, 28);
		add(txtModelo);
		
		lblMarca = new JLabel("Marca:");
		lblMarca.setForeground(Color.WHITE);
		lblMarca.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblMarca.setBounds(243, 212, 68, 20);
		add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setForeground(new Color(0, 139, 139));
		txtMarca.setColumns(10);
		txtMarca.setBounds(312, 211, 358, 28);
		add(txtMarca);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				novoProduto = new ProdutoVO();
				produtoController = new ProdutoController();
				
				novoProduto.setModelo(txtModelo.getText());
				novoProduto.setMarca(txtMarca.getText());
				
				try {
					produtoController.inserirProdutoController(novoProduto);
					JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", 
							"Sucesso", JOptionPane.INFORMATION_MESSAGE);
				}catch(ExceptionVGA e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), 
							"Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCadastrar.setForeground(new Color(0, 139, 139));
		btnCadastrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnCadastrar.setBounds(438, 280, 107, 27);
		add(btnCadastrar);

	}
}
