package view;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.vo.UsuarioVO;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class TelaControleEstoque {

	private JFrame frame;
	private ArrayList<UsuarioVO> usuarios;
	private String[] nomesColunas = { "Produto", "Marca", "Tamanho", "Cor", "Quantidade", "Preço" };
	private JTable tblProdutos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaControleEstoque window = new TelaControleEstoque();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	/**
	 * Create the application.
	 */
	public TelaControleEstoque() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		
		frame.setBounds(100, 100, 946, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 946, 585);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblVoltar = new JLabel("");
		lblVoltar.setIcon(new ImageIcon("C:\\Users\\GGC12037\\Downloads\\icons8-voltar-20.png"));
		lblVoltar.setBounds(0, 0, 48, 48);
		panel.add(lblVoltar);
		
		tblProdutos = new JTable();
		tblProdutos.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
		tblProdutos.setBounds(0, 106, 946, 413);
		panel.add(tblProdutos);
		
		JLabel lblSair = new JLabel("  X");
		lblSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblSair.setForeground(new Color(0, 139, 139));
		lblSair.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblSair.setBounds(915, 0, 31, 30);
		panel.add(lblSair);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(201, 523, 118, 30);
		panel.add(btnAdicionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(420, 523, 118, 30);
		panel.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(640, 523, 118, 30);
		panel.add(btnExcluir);
		
		JLabel lblVga = new JLabel("VGA");
		lblVga.setForeground(new Color(0, 139, 139));
		lblVga.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblVga.setBounds(436, 0, 63, 41);
		panel.add(lblVga);
		
		JLabel lblControle = new JLabel("Controle de Estoque");
		lblControle.setForeground(new Color(0, 139, 139));
		lblControle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblControle.setBounds(378, 52, 191, 27);
		panel.add(lblControle);
	}
}
