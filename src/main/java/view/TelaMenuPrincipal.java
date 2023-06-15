package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.ImageIcon;

public class TelaMenuPrincipal {

	private JFrame frame;
	private JLabel lblSair;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public TelaMenuPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
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
		frame.setBounds(100, 100, 946, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel_1 = new JLabel("");
		
		JLabel lblSair = new JLabel(" X");
		lblSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblSair.setForeground(new Color(0, 139, 139));
		lblSair.setFont(new Font("Tahoma", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(126)
					.addComponent(lblNewLabel_1)
					.addContainerGap(820, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(921, Short.MAX_VALUE)
					.addComponent(lblSair)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSair)
					.addGap(124)
					.addComponent(lblNewLabel_1)
					.addContainerGap(432, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	public void tornarVisivelForaDoFrame() {
		frame.setVisible(true);
	}
}
