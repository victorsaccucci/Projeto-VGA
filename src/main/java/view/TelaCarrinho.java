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
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TelaCarrinho {

	private JFrame frame;
	private TelaDeProdutos telaDeProdutos;
	private JLabel lblSair;
	private JLabel lblMaximizar;
	private JLabel lblMinimizar;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTable table;
	
	
	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public TelaCarrinho() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
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
				telaDeProdutos = new TelaDeProdutos();
				telaDeProdutos.tornarVisivelForaDoFrame();
				frame.setVisible(false);
				
			}
		});
		lblNewLabel.setIcon(new ImageIcon(TelaCarrinho.class.getResource("/icones/icons8-voltar-50 (1).png")));
		
		table = new JTable();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(139, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(144, Short.MAX_VALUE)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 684, GroupLayout.PREFERRED_SIZE)
					.addGap(118))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(35)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(85, Short.MAX_VALUE))
		);
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

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaDeProdutos.class.getResource("/icones/icons8-maximizar-10.png")));
		lblNewLabel_1.setBounds(896, 6, 15, 15);
		frame.getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 frame.setExtendedState(JFrame.ICONIFIED);
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon(TelaDeProdutos.class.getResource("/icones/icons8-minimizar-15.png")));
		lblNewLabel_2.setBounds(867, 0, 16, 27);
		frame.getContentPane().add(lblNewLabel_2);
	}
	
	

	public void tornarVisivelForaDoFrame() {
		frame.setVisible(true);
		
	}
}
