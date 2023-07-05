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
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	protected Point initialClick;

	public TelaDetalhes(String modeloTenis, String valorTenis, ImageIcon imagemDoTenis, int idDoItemClicado,
			UsuarioVO usuario) {
		initialize(idDoItemClicado);
		exibirValores(modeloTenis, valorTenis, imagemDoTenis, idDoItemClicado);
		usuarioVO = usuario;
	}

	private void initialize(final int idDoItemClicado) {
		frame = new JFrame();
		MouseAdapter mouseAdapter = new MouseAdapter() {
			private Point initialClick;

			@Override
			public void mousePressed(MouseEvent e) {
				initialClick = e.getPoint();
				frame.getComponentAt(initialClick);
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				int thisX = frame.getLocation().x;
				int thisY = frame.getLocation().y;

				int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
				int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

				int X = thisX + xMoved;
				int Y = thisY + yMoved;

				frame.setLocation(X, Y);
			}
		};
		frame.addMouseListener(mouseAdapter);
		frame.addMouseMotionListener(mouseAdapter);
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(new Color(64, 128, 128));
		frame.setBounds(100, 100, 1050, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblModeloTenis = new JLabel("");
		lblModeloTenis.setHorizontalAlignment(SwingConstants.CENTER);
		lblModeloTenis.setForeground(new Color(255, 255, 255));
		lblModeloTenis.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblModeloTenis.setBounds(0, 39, 1050, 67);
		frame.getContentPane().add(lblModeloTenis);

		lvlValorTenis = new JLabel("");
		lvlValorTenis.setHorizontalAlignment(SwingConstants.CENTER);
		lvlValorTenis.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lvlValorTenis.setForeground(new Color(255, 255, 255));
		lvlValorTenis.setBounds(0, 459, 1050, 60);
		frame.getContentPane().add(lvlValorTenis);

		lblVoltar = new JLabel("");
		lblVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		btnComprar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnComprar.setForeground(new Color(1, 139, 139));
		btnComprar.setBounds(471, 519, 107, 32);
		frame.getContentPane().add(btnComprar);
		
				lblTenis = new JLabel("");
				lblTenis.setHorizontalAlignment(SwingConstants.CENTER);
				lblTenis.setBounds(10, 118, 1030, 318);
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
