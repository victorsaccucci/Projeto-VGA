package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.plaf.basic.BasicMenuBarUI;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.ItemController;
import model.vo.ItemVO;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class TelaMenuPrincipal {

	private JFrame frame;
	private JLabel lblSair;
	private JLabel labelImagem;

	private ItemController controller;

	private List<ItemVO> itensComImagens;
	private JLabel label;

	private PainelMenuPrincipalParaClientes painelMenu;
	protected Point initialClick;
	private JMenuBar menuBar;
	private JMenu menuCarrinho;
	private JMenu mnNewMenu_1;
	private JMenu menuMinimizar;
	private JMenu menuMaximizar;
	private JMenu menuSair;
	protected TelaCarrinho telaCarrinho;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenuPrincipal window = new TelaMenuPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaMenuPrincipal() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 1050, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int frameWidth = frame.getWidth();
				menuBar.setPreferredSize(new Dimension(frameWidth, menuBar.getPreferredSize().height));
				frame.revalidate();
			}
		});

		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				initialClick = e.getPoint();
				frame.getComponentAt(initialClick);
			}
		});

		frame.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				int thisX = frame.getLocation().x;
				int thisY = frame.getLocation().y;

				int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
				int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

				int X = thisX + xMoved;
				int Y = thisY + yMoved;

				frame.setLocation(X, Y);
			}
		});

		painelMenu = new PainelMenuPrincipalParaClientes();

		JScrollPane scrollPane = new JScrollPane(painelMenu);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		frame.getContentPane().setLayout(new BorderLayout());

		scrollPane.setViewportView(painelMenu);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		menuBar = new JMenuBar();
		menuBar.setUI(new CustomMenuBarUI());
		scrollPane.setColumnHeaderView(menuBar);

		menuCarrinho = new JMenu("");
		menuCarrinho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				telaCarrinho = new TelaCarrinho();
				telaCarrinho.tornarVisivelForaDoFrame();
				frame.setVisible(false);
			}
		});

		menuCarrinho
				.setIcon(new ImageIcon(TelaMenuPrincipal.class.getResource("/icones/icons8-sacola-de-compras-30.png")));
		menuCarrinho.setForeground(new Color(0, 139, 139)); // Define a cor do texto do JMenu
		menuBar.add(menuCarrinho);

		mnNewMenu_1 = new JMenu(
				"                                                                                                                                                                                                                                                                                        ");
		mnNewMenu_1.setEnabled(false);
		menuBar.add(mnNewMenu_1);

		menuMinimizar = new JMenu("");
		menuMinimizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(Frame.ICONIFIED); // Minimiza o JFrame
			}
		});
		menuMinimizar.setIcon(new ImageIcon(TelaMenuPrincipal.class.getResource("/icones/icons8-minimizar-15.png")));
		menuMinimizar.setForeground(new Color(255, 255, 255));
		menuMinimizar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(menuMinimizar);

		menuMaximizar = new JMenu("");
		menuMaximizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
		});
		menuMaximizar.setIcon(new ImageIcon(TelaMenuPrincipal.class.getResource("/icones/icons8-maximizar-10.png")));
		menuMaximizar.setForeground(Color.WHITE);
		menuMaximizar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(menuMaximizar);

		menuSair = new JMenu("X");
		menuSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		menuSair.setForeground(Color.WHITE);
		menuSair.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(menuSair);

		frame.validate();
	}

	class CustomMenuBarUI extends BasicMenuBarUI {
		@Override
		public void paint(Graphics g, JComponent c) {
			g.setColor(new Color(0, 139, 139));
			g.fillRect(0, 0, c.getWidth(), c.getHeight());
		}
	}

	public void tornarVisivelForaDoFrame() {
		frame.setVisible(true);

	}
}
