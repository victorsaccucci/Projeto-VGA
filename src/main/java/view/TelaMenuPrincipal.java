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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import model.vo.ItemVO;

import com.jgoodies.forms.layout.FormSpecs;

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
		frame.getContentPane().setBackground(new Color(0, 139, 139));
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
		lblSair.setForeground(new Color(255, 255, 255));
		lblSair.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("200px"),
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("200px"),
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("200px"),
						ColumnSpec.decode("right:15px"), },
				new RowSpec[] { FormSpecs.UNRELATED_GAP_ROWSPEC, RowSpec.decode("19px"), RowSpec.decode("150px"),
						RowSpec.decode("1px"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("150px"), FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("150px"), }));

		JLabel lblNewLabel_4_2 = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel_4_2, "3, 3, center, default");

		JLabel lblNewLabel_4_3 = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel_4_3, "6, 3, center, default");

		JLabel lblNewLabel_4_4 = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel_4_4, "9, 3, center, default");
		frame.getContentPane().add(lblNewLabel_1, "6, 4, left, top");
		frame.getContentPane().add(lblSair, "10, 2, fill, top");

		JLabel lblNewLabel_4 = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel_4, "3, 8, center, default");

		JLabel lblNewLabel_4_1 = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel_4_1, "6, 8, center, default");

		JLabel lblNewLabel_4_1_1 = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel_4_1_1, "9, 8, center, default");

		JLabel lblNewLabel = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel, "3, 12, center, default");

		JLabel lblNewLabel_2 = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel_2, "6, 12, center, default");

		JLabel lblNewLabel_3 = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel_3, "9, 12, center, default");
	}

	public void tornarVisivelForaDoFrame() {
		frame.setVisible(true);
	}

	public void inserirLabel(ItemVO novoItem) {
		// TODO Auto-generated method stub
		
	}
}