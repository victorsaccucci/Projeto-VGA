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
import model.vo.UsuarioVO;

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
import javax.swing.SwingConstants;

public class TelaMenuPrincipal {

	private JFrame frame;
	private JLabel lblSair;
	private JLabel labelImagem;

	private ItemController controller;

	private List<ItemVO> itensComImagens;
	private JLabel label;

	private PainelMenuPrincipalParaClientes painelMenu;
	protected Point initialClick;
	protected TelaCarrinho telaCarrinho;

	private UsuarioVO usuarioVO;

	private TelaLoginUsuario telaLogin;
	private TelaMenuAdm telaMenuAdm;
	private JLabel label_1;
	private JLabel lblNewLabel;

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
		frame.setUndecorated(false);
		frame.setBounds(100, 100, 1050, 600);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

		label_1 = new JLabel("");
		painelMenu.add(label_1);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		lblNewLabel = new JLabel("VGA");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(0, 139, 139));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblNewLabel);

		telaLogin = new TelaLoginUsuario();

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
