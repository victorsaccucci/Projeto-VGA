package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.Point;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.ItemController;
import model.vo.ItemVO;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
		frame.setBounds(100, 100, 946, 585);
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
		


		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem);

		painelMenu = new PainelMenuPrincipalParaClientes();

		JScrollPane scrollPane = new JScrollPane(painelMenu);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(scrollPane, BorderLayout.EAST);

		frame.validate();
	}

	public void tornarVisivelForaDoFrame() {
		frame.setVisible(true);
	}

}
