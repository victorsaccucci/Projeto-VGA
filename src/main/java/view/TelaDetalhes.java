package view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.ItemController;
import controller.VendaController;
import model.vo.ItemVO;
import model.vo.UsuarioVO;
import model.vo.VendaVO;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JTextField;

public class TelaDetalhes {

	private JFrame frame;
	private JLabel lblTenis;
	private JLabel lblModeloTenis;
	private JLabel lvlValorTenis;
	private ImageIcon imagemDoTenis;
	private JLabel lblVoltar;
	private PainelMenuPrincipalParaClientes painelMenuParaClientes;

	private TelaMenuPrincipal telaMenu;
	private JLabel lblNewLabel;
	private JLabel lblAdicionarCarrinho;
	private ItemVO itemSelecionado;

	// lista vazia que
	private ArrayList<ItemVO> listaCarrinho = null;

	private int idItem = 0;
	private int idCarrinho = 0;
	private ItemVO itemSelecionadoPeloCliente;
	private ItemController itemController;
	private JTextField txtQuantidade;
	private int quantidade = 0;
	private VendaVO vendaVO;
	private UsuarioVO usuarioVO;
	private int idDoItemSelecionado;
	private int id = 0;

	private TelaLoginUsuario telaLoginUsuario;
	private JTextField txtId;
	protected int idDoItem;

	public TelaDetalhes(String modeloTenis, String valorTenis, ImageIcon imagemDoTenis, int idDoItemClicado,
			UsuarioVO usuario) {
		initialize(idDoItemClicado);
		exibirValores(modeloTenis, valorTenis, imagemDoTenis, idDoItemClicado);
		usuarioVO = usuario;
	}

	private void initialize(final int idDoItemClicado) {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(new Color(64, 128, 128));
		frame.setBounds(100, 100, 1050, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblModeloTenis = new JLabel("");
		lblModeloTenis.setForeground(new Color(255, 255, 255));
		lblModeloTenis.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblModeloTenis.setBounds(409, 39, 448, 67);
		frame.getContentPane().add(lblModeloTenis);

		lvlValorTenis = new JLabel("");
		lvlValorTenis.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lvlValorTenis.setForeground(new Color(255, 255, 255));
		lvlValorTenis.setBounds(422, 459, 224, 60);
		frame.getContentPane().add(lvlValorTenis);

		lblVoltar = new JLabel("");
		lblVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				telaMenu = new TelaMenuPrincipal();
				telaMenu.tornarVisivelForaDoFrame();
				frame.setVisible(false);
			}
		});
		lblVoltar.setIcon(new ImageIcon(TelaDetalhes.class.getResource("/icones/icons8-voltar-35.png")));
		lblVoltar.setBounds(20, 20, 80, 60);
		frame.getContentPane().add(lblVoltar);

		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(929, 514, 22, 27);
		frame.getContentPane().add(txtQuantidade);
		txtQuantidade.setColumns(10);

		lblAdicionarCarrinho = new JLabel("comprar");
		lblAdicionarCarrinho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				vendaVO = new VendaVO();
				usuarioVO = new UsuarioVO();
				painelMenuParaClientes = new PainelMenuPrincipalParaClientes();
				itemController = new ItemController();
				itemSelecionadoPeloCliente = new ItemVO();
				itemSelecionadoPeloCliente = painelMenuParaClientes.getItemSelecionado();
				quantidade = Integer.parseInt(txtQuantidade.getText());

				id = Integer.parseInt(txtId.getText());

				if (itemController.consultarPorId(idDoItemClicado).getQuantidade() <= 0) {
					JOptionPane.showMessageDialog(null, "Indisponível no momento, volte mais tarde!");
				} else {
					itemController.diminuirtQuantidadeController(idDoItemClicado, quantidade);
					JOptionPane.showMessageDialog(null, "Compra realizada!");
				}

				telaLoginUsuario = new TelaLoginUsuario();

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				vendaVO.setDataVenda(LocalDateTime.now().format(formatter));
				vendaVO.setIdItem(idDoItemClicado);
				vendaVO.setIdUsuario(id);

				VendaController vendaController = new VendaController();
				vendaController.inserirVenda(vendaVO);

			}
		});

		lblAdicionarCarrinho.setForeground(new Color(255, 255, 255));
		lblAdicionarCarrinho.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblAdicionarCarrinho.setBounds(481, 514, 65, 20);
		frame.getContentPane().add(lblAdicionarCarrinho);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblQuantidade.setForeground(new Color(255, 255, 255));
		lblQuantidade.setBounds(810, 513, 94, 22);
		frame.getContentPane().add(lblQuantidade);

		JLabel lblId = new JLabel("Seu ID:");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblId.setBounds(810, 546, 94, 22);
		frame.getContentPane().add(lblId);

		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(929, 550, 22, 27);
		frame.getContentPane().add(txtId);

		lblTenis = new JLabel("");
		lblTenis.setBounds(288, 118, 515, 318);
		frame.getContentPane().add(lblTenis);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				TelaDetalhes.class.getResource("/icones/textura-de-cor-ciano-escuro-grunge_469558-34227 (2) (1).png")));
		lblNewLabel.setBounds(0, 0, 1050, 600);
		frame.getContentPane().add(lblNewLabel);
	}

	public void exibirValores(String modeloTenis, String valorTenis, ImageIcon imagemDoTenis, int idDoItemClicado) {
		lblModeloTenis.setText("Modelo: " + modeloTenis);
		lvlValorTenis.setText("Valor: " + valorTenis);
		lblTenis.setIcon(imagemDoTenis);

		System.out.println("Modelo do Tênis: " + modeloTenis);
		System.out.println("Valor do Tênis: " + valorTenis);
	}

	public void tornarVisivelForaDoFrame() {
		frame.setVisible(true);
	}
}
