package view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.dao.CarrinhoItensDAO;
import model.vo.CarrinhoItensVO;
import model.vo.CarrinhoVO;
import model.vo.ItemVO;


import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class TelaDetalhes {

    private JFrame frame;
    private JLabel lblTenis;
    private JLabel lblModeloTenis;
    private JLabel lvlValorTenis;
    private ImageIcon imagemDoTenis;
    private JLabel lblVoltar;
    private CarrinhoItensDAO carrinhoItemDAO;
    private ArrayList<ItemVO> listaCarrinho;
    private PainelMenuPrincipalParaClientes painelMenuParaClientes;
    private CarrinhoItensVO carrinhoItensVO;
    
    private TelaMenuPrincipal telaMenu;
    private JLabel lblNewLabel;
	private JLabel lblAdicionarCarrinho;

    public TelaDetalhes(String modeloTenis, String valorTenis, ImageIcon imagemDoTenis) {
        initialize();
        exibirValores(modeloTenis, valorTenis, imagemDoTenis);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(new Color(64, 128, 128));
        frame.setBounds(100, 100, 1050, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        lblTenis = new JLabel("");
        lblTenis.setBounds(288, 118, 515, 318);
        frame.getContentPane().add(lblTenis);

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
        
        lblAdicionarCarrinho = new JLabel("Adicionar ao Carrinho");
        lblAdicionarCarrinho.addMouseListener(new MouseAdapter() {
      
			

			@Override
        	public void mouseClicked(MouseEvent e) {
				
				painelMenuParaClientes = new PainelMenuPrincipalParaClientes();
        		carrinhoItemDAO = new CarrinhoItensDAO();
        		listaCarrinho = new ArrayList<ItemVO>();
        		
        		// carrinhoItemDAO.inserir(painelMenuParaClientes.getIdSelecionado(), idcarrinho)
        	}
        });
        lblAdicionarCarrinho.setForeground(new Color(255, 255, 255));
        lblAdicionarCarrinho.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblAdicionarCarrinho.setBounds(432, 530, 148, 20);
        frame.getContentPane().add(lblAdicionarCarrinho);
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(TelaDetalhes.class.getResource("/icones/textura-de-cor-ciano-escuro-grunge_469558-34227 (2) (1).png")));
        lblNewLabel.setBounds(0, 0, 1050, 600);
        frame.getContentPane().add(lblNewLabel);
    }

    public void exibirValores(String modeloTenis, String valorTenis, ImageIcon imagemDoTenis) {
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
