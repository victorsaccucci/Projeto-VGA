package view;

import java.awt.EventQueue;
import java.awt.Point;

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
import java.awt.Color;

public class TelaMenuAdm {

	private JFrame frame;
	private PainelListagemClientes painelListagemClientes;
	private PainelAdmCadastroUsuarios painelAdmCadastroUsuarios;
	
	private PainelListagemItens listagemItens;
	private PainelCadastroItem painelCadastroItem;
	protected PainelCadastroDeProduto painelCadastroProduto;
	private PainelAdmCadastroUsuarios painelCadastroUsuarios;

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

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		frame.setJMenuBar(menuBar);

		JMenu menuUsuarios = new JMenu("Usuários");
		menuUsuarios.setIcon(new ImageIcon(TelaMenuAdm.class.getResource("/icones/icons8-usuario-30 (1).png")));
		menuBar.add(menuUsuarios);

		JMenuItem menuItemCadastrarUsuarios = new JMenuItem("Cadastrar");
		menuItemCadastrarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					painelAdmCadastroUsuarios = new PainelAdmCadastroUsuarios(null);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				painelAdmCadastroUsuarios.setVisible(true);
				frame.setContentPane(painelAdmCadastroUsuarios);
				frame.revalidate();
			}
		});
		menuUsuarios.add(menuItemCadastrarUsuarios);
		
				JMenuItem menuItemConsultarUsuarios = new JMenuItem("Consultar");
				menuItemConsultarUsuarios.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						painelListagemClientes = new PainelListagemClientes();
						painelListagemClientes.getBtnEditar().addActionListener(new ActionListener() {
							

							public void actionPerformed(ActionEvent e) {
								try {
									painelCadastroUsuarios = new PainelAdmCadastroUsuarios(painelListagemClientes.getUsuarioSelecionado());
									painelCadastroUsuarios.setVisible(true);
									frame.setContentPane(painelCadastroUsuarios);
									frame.revalidate();
									registrarCliqueBotaoEditarDoPainelListagemCliente();
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
						});
						painelListagemClientes.setVisible(true);
						frame.setContentPane(painelListagemClientes);
						frame.revalidate();
					}
				});
				menuUsuarios.add(menuItemConsultarUsuarios);

		JMenu menuEstoque = new JMenu("Estoque");
		menuEstoque.setIcon(new ImageIcon(TelaMenuAdm.class.getResource("/icones/icons8-vender-estoque-30.png")));
		menuBar.add(menuEstoque);
		
		JMenuItem menuItemCadastrarItem = new JMenuItem("Cadastrar Item");
		menuItemCadastrarItem.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				painelCadastroItem = new PainelCadastroItem();
				painelCadastroItem.setVisible(true);
				frame.setContentPane(painelCadastroItem);
				frame.revalidate();
			}
		});
		menuEstoque.add(menuItemCadastrarItem);
		
		JMenuItem menuItemCadastrarProduto = new JMenuItem("Cadastrar Produto");
		menuItemCadastrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelCadastroProduto = new PainelCadastroDeProduto();
				painelCadastroProduto.setVisible(true);
				frame.setContentPane(painelCadastroProduto);
				frame.revalidate();
			}
		});
		menuEstoque.add(menuItemCadastrarProduto);
		
				JMenuItem menuItemConsultarEstoque = new JMenuItem("Consultar");
				menuItemConsultarEstoque.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						listagemItens = new PainelListagemItens();
						listagemItens.setVisible(true);
						frame.setContentPane(listagemItens);
						frame.revalidate();
					}
				});
				menuEstoque.add(menuItemConsultarEstoque);

		JMenu mnNewMenu_2 = new JMenu("");
		menuBar.add(mnNewMenu_2);

		JMenu mnNewMenu_3 = new JMenu(
				"                                                                                                                                                                                                                               ");
		mnNewMenu_3.setEnabled(false);
		menuBar.add(mnNewMenu_3);

		JMenu mnNewMenu_4 = new JMenu("  X  ");
		mnNewMenu_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		menuBar.add(mnNewMenu_4);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 139, 139));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE));
		panel.setLayout(new BorderLayout(0, 0));

	}
	
	protected void registrarCliqueBotaoEditarDoPainelListagemCliente() {
		
		painelListagemClientes.getBtnEditar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					painelAdmCadastroUsuarios =  new PainelAdmCadastroUsuarios(painelListagemClientes.getUsuarioSelecionado());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				painelAdmCadastroUsuarios.setVisible(true);
				frame.setContentPane(painelAdmCadastroUsuarios);
				frame.revalidate();
			}
		});
		
	}

	public void tornarVisivelForaDoFrame() {
		frame.setVisible(true);		
	}
}
