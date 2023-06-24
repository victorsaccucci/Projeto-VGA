package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import controller.ItemController;
import model.seletor.SeletorItem;
import model.seletor.SeletorProduto;
import model.vo.ItemVO;
import model.vo.ProdutoVO;
import model.vo.UsuarioVO;

import javax.swing.JButton;

public class TelaCarrinho {

	private JFrame frame;
	private JLabel lblSair;
	private JLabel lblMaximizar;
	private JLabel lblMinimizar;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTable tabelaCarrinho;

	private String[] nomesColunas = { "Modelo", "Marca", "Cor", "Tamanho", "Quantidade", "Valor", "Total" };
	private List<ItemVO> itens;

	private ItemController controllerCarrinho;
	protected TelaMenuPrincipal telaMenuPrincipal;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCarrinho window = new TelaCarrinho();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCarrinho() {
		initialize();
	}

	private void limparTabela() {
		tabelaCarrinho.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	private void atualizarTabelaCarrinho() {
		this.limparTabela();

		DefaultTableModel model = (DefaultTableModel) tabelaCarrinho.getModel();
		itens = controllerCarrinho.consultarTodos();

		for (ItemVO itemVO : itens) {
			Object[] novaLinhaDaTabela = new Object[6];
			novaLinhaDaTabela[0] = itemVO.getProduto().getMarca();
			novaLinhaDaTabela[1] = itemVO.getProduto().getModelo();
			novaLinhaDaTabela[2] = itemVO.getCor();
			novaLinhaDaTabela[3] = itemVO.getTamanho();
			novaLinhaDaTabela[4] = itemVO.getQuantidade();
			novaLinhaDaTabela[5] = itemVO.getPrecoUnitario();
			model.addRow(novaLinhaDaTabela);
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 139, 139));
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 946, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				telaMenuPrincipal = new TelaMenuPrincipal();
				telaMenuPrincipal.tornarVisivelForaDoFrame();
				frame.setVisible(false);

			}
		});
		lblNewLabel.setIcon(new ImageIcon(TelaCarrinho.class.getResource("/icones/icons8-voltar-50 (1).png")));

		tabelaCarrinho = new JTable();
		tabelaCarrinho.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				controllerCarrinho = new ItemController();

				// TODO preencher o idCarrinho
				SeletorItem seletor = new SeletorItem();
				SeletorProduto seletorProduto = new SeletorProduto();

				controllerCarrinho.consultarComFiltros(seletor, seletorProduto);
				atualizarTabelaCarrinho();
			}
		});

		JButton btnFinalizar = new JButton("Finalizar comprar");
		btnFinalizar.setForeground(new Color(0, 139, 139));
		btnFinalizar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 45,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(901, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup().addContainerGap(203, Short.MAX_VALUE)
										.addComponent(tabelaCarrinho, GroupLayout.PREFERRED_SIZE, 684,
												GroupLayout.PREFERRED_SIZE)
										.addGap(125))
								.addGroup(groupLayout.createSequentialGroup().addContainerGap(473, Short.MAX_VALUE)
										.addComponent(btnFinalizar).addGap(388)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblNewLabel).addGap(25)
						.addComponent(tabelaCarrinho, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE)
						.addGap(32).addComponent(btnFinalizar).addContainerGap(34, Short.MAX_VALUE)));
		frame.getContentPane().setLayout(groupLayout);

		lblSair = new JLabel(" X");
		lblSair.setVerticalAlignment(SwingConstants.TOP);
		lblSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblSair.setForeground(Color.WHITE);
		lblSair.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblSair.setBounds(924, 0, 22, 21);
		frame.getContentPane().add(lblSair);

		lblMaximizar = new JLabel("  ");
		lblMaximizar.setBounds(881, 534, 25, 24);
		frame.getContentPane().add(lblMaximizar);

		lblMinimizar = new JLabel("  ");
		lblMinimizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setExtendedState(JFrame.ICONIFIED);
			}
		});
		lblMinimizar.setForeground(new Color(255, 255, 255));
		lblMinimizar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblMinimizar.setBackground(new Color(255, 255, 255));
		lblMinimizar.setBounds(700, 522, 8, 20);
		frame.getContentPane().add(lblMinimizar);

	}

	public void tornarVisivelForaDoFrame() {
		frame.setVisible(true);

	}
}
