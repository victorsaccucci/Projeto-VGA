package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Animacion.Animacion;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TelaDeProdutos {

	private JFrame frame;
	private JLabel lblExemplares;
	private JLabel lblVoltar;
	private JLabel lblAvancar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeProdutos window = new TelaDeProdutos();
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
	public TelaDeProdutos() {
		initialize();
		show(posicao);
	}

	int posicao = 0;
	private JLabel lblNewLabel;
	private JLabel lblComprar;
	private JLabel lblValorfalso;
	private JLabel lblValorVerdadeiro;
	private JLabel lblSair;
	private JLabel lblMaximizar;
	private JLabel lblMinizar;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;

	public String[] litarImagens() {
		File f = new File(getClass().getResource("/imagensTenis").getFile());
		String[] imagensListadas = f.list();
		return imagensListadas;
	}

	private void show(int indice) {
		String[] imagens = litarImagens();
		String imagem = imagens[indice];

		ImageIcon icone = new ImageIcon(getClass().getResource("/imagensTenis/" + imagem));

		Image img = icone.getImage().getScaledInstance(lblExemplares.getWidth(), lblExemplares.getHeight(),
				Image.SCALE_SMOOTH);
		lblExemplares.setIcon(new ImageIcon(img));
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 139, 139));
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 946, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblExemplares = new JLabel("                      ");
		lblExemplares.setBounds(287, 100, 400, 375);
		frame.getContentPane().add(lblExemplares);

		lblVoltar = new JLabel("");
		lblVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new Thread();
				try {
					Thread.sleep(300);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				int p = lblExemplares.getX();

				if (p > -1) {
					Animacion.mover_izquierda(900, 200, 1, 2, lblExemplares);
				}
				posicao = posicao - 1;
				if (posicao < 0) {
					posicao = 0;
				}
				show(posicao);
			}
		});
		lblVoltar.setBackground(new Color(255, 255, 255));
		lblVoltar.setIcon(new ImageIcon(TelaDeProdutos.class.getResource("/icones/icons8-voltar-50 (1).png")));
		lblVoltar.setBounds(32, 208, 69, 181);
		frame.getContentPane().add(lblVoltar);

		lblAvancar = new JLabel("");
		lblAvancar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new Thread();
				try {
					Thread.sleep(300);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				int p = lblExemplares.getX();

				if (p > -1) {
					Animacion.mover_izquierda(900, 200, 1, 2, lblExemplares);
				}
				posicao = posicao + 1;
				if (posicao >= litarImagens().length) {
					posicao = litarImagens().length - 1;
				}
				show(posicao);
			}

		});
		lblAvancar.setIcon(new ImageIcon(TelaDeProdutos.class.getResource("/icones/icons8-avançar-50 (1).png")));
		lblAvancar.setBounds(867, 208, 69, 181);
		frame.getContentPane().add(lblAvancar);

		JTextArea txtrTenisTeste = new JTextArea();
		txtrTenisTeste.setBackground(null); // Define o fundo como transparente
		txtrTenisTeste.setOpaque(false); // Define a opacidade como false
		txtrTenisTeste.setBackground(new Color(0, 139, 139));
		txtrTenisTeste.setForeground(new Color(255, 255, 255));
		txtrTenisTeste.setFont(new Font("Bookman Old Style", Font.BOLD, 30));
		txtrTenisTeste.setText("tenis teste");
		txtrTenisTeste.setBounds(403, 51, 196, 40);
		frame.getContentPane().add(txtrTenisTeste);

		lblComprar = new JLabel("Comprar");
		lblComprar.setForeground(new Color(255, 255, 255));
		lblComprar.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblComprar.setBounds(483, 485, 82, 27);
		frame.getContentPane().add(lblComprar);

		lblValorfalso = new JLabel("Valor:");
		lblValorfalso.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblValorfalso.setForeground(new Color(255, 255, 255));
		lblValorfalso.setBounds(297, 485, 55, 27);
		frame.getContentPane().add(lblValorfalso);

		lblValorVerdadeiro = new JLabel("");
		lblValorVerdadeiro.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblValorVerdadeiro.setForeground(new Color(255, 255, 255));
		lblValorVerdadeiro.setBounds(362, 485, 93, 40);
		frame.getContentPane().add(lblValorVerdadeiro);

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

		lblMinizar = new JLabel("  ");
		lblMinizar.setForeground(new Color(255, 255, 255));
		lblMinizar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblMinizar.setBackground(new Color(255, 255, 255));
		lblMinizar.setBounds(700, 522, 8, 20);
		frame.getContentPane().add(lblMinizar);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaDeProdutos.class.getResource("/icones/icons8-maximizar-15.png")));
		lblNewLabel_1.setBounds(896, 6, 15, 15);
		frame.getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(TelaDeProdutos.class.getResource("/icones/icons8-menos-30.png")));
		lblNewLabel_2.setBounds(867, 0, 16, 27);
		frame.getContentPane().add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaCarrinho telaCarrinho = new TelaCarrinho();
				telaCarrinho.tornarVisivelForaDoFrame();
				frame.setVisible(false);
			}
		});
		lblNewLabel_3.setIcon(new ImageIcon(TelaDeProdutos.class.getResource("/icones/icons8-sacola-de-compras-50.png")));
		lblNewLabel_3.setBounds(32, 24, 50, 50);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_5 = new JLabel("add");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    JFileChooser fileChooser = new JFileChooser();
		        int result = fileChooser.showOpenDialog(frame);
		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fileChooser.getSelectedFile();
		            String destinationPath = "src/main/java/imagensTenis/";

		            try {
		                // Copia o arquivo para o diretório de destino
		                Path destination = Paths.get(destinationPath, selectedFile.getName());
		                Files.copy(selectedFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
			}
		});
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_5.setBounds(801, 507, 82, 40);
		frame.getContentPane().add(lblNewLabel_5);
		
			
		
				lblNewLabel = new JLabel("gxcghjhg");
				lblNewLabel.setIcon(new ImageIcon(
						TelaDeProdutos.class.getResource("/icones/textura-de-cor-ciano-escuro-grunge_469558-34227 (2).png")));
				lblNewLabel.setBounds(0, 0, 946, 585);
				frame.getContentPane().add(lblNewLabel);
	}

	public void tornarVisivelForaDoFrame() {
		frame.setVisible(true);
		
	}
}