package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class TelaMenuAdm {

	private JFrame frame;
	private PainelListagemClientes painelListagemClientes;
	private PainelAdmCadastroUsuarios painelAdmCadastroUsuarios;
	private PainelListagemItens listagemItens;
	private PainelCadastroItem painelCadastroItem;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenuAdm window = new TelaMenuAdm();
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
	public TelaMenuAdm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 946, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menuUsuarios = new JMenu("Usuários");
		menuUsuarios.setIcon(new ImageIcon(TelaMenuAdm.class.getResource("/icones/icons8-usuario-30 (1).png")));
		menuBar.add(menuUsuarios);

		JMenuItem menuItemConsultarUsuarios = new JMenuItem("Listar");
		menuItemConsultarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelListagemClientes = new PainelListagemClientes();
				painelListagemClientes.setVisible(true);
				frame.setContentPane(painelListagemClientes);
				frame.revalidate();
			}
		});
		menuUsuarios.add(menuItemConsultarUsuarios);

		JMenuItem menuItemCadastrarUsuarios = new JMenuItem("Cadastrar");
		menuItemCadastrarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					painelAdmCadastroUsuarios = new PainelAdmCadastroUsuarios();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				painelAdmCadastroUsuarios.setVisible(true);
				frame.setContentPane(painelAdmCadastroUsuarios);
				frame.revalidate();
			}
		});
		menuUsuarios.add(menuItemCadastrarUsuarios);

		JMenu menuEstoque = new JMenu("Estoque");
		menuEstoque.setIcon(new ImageIcon(TelaMenuAdm.class.getResource("/icones/icons8-vender-estoque-30.png")));
		menuBar.add(menuEstoque);

		JMenuItem menuItemConsultarEstoque = new JMenuItem("Consultar");
		menuItemConsultarEstoque.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				listagemItens = new PainelListagemItens();
				listagemItens.setVisible(true);
				frame.setContentPane(listagemItens);
				frame.revalidate();
			}
		});
		
		JMenuItem CadastrarItem = new JMenuItem("Cadastrar Item");
		CadastrarItem.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				painelCadastroItem = new PainelCadastroItem();
				painelCadastroItem.setVisible(true);
				frame.setContentPane(painelCadastroItem);
				frame.revalidate();
			}
		});
		menuEstoque.add(CadastrarItem);
		menuEstoque.add(menuItemConsultarEstoque);

		JMenu mnNewMenu_2 = new JMenu("");
		menuBar.add(mnNewMenu_2);

		JMenu mnNewMenu_3 = new JMenu(
				"                                                                                                                                                                                                                               ");
		mnNewMenu_3.setEnabled(false);
		menuBar.add(mnNewMenu_3);

		JMenu mnNewMenu_4 = new JMenu("X   ");
		mnNewMenu_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		menuBar.add(mnNewMenu_4);

		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE));
		panel.setLayout(new BorderLayout(0, 0));

	}

	public void tornarVisivelForaDoFrame() {
		frame.setVisible(true);		
	}
}
