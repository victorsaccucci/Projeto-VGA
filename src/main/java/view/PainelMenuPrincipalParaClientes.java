package view;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ItemController;
import model.vo.ItemVO;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class PainelMenuPrincipalParaClientes extends JPanel {

	private static final long serialVersionUID = 6621453475759824429L;

	private List<ItemVO> itensComImagens;
	private JLabel label;
	private ItemController controller;
	private TelaDetalhes telaDetalhes;

	private String valorDoTenisClicado = "";
	private String modeloDoTenisClicado = "";

	private ImageIcon imagemDoTenisClicado = null;


	public PainelMenuPrincipalParaClientes() {
		setBackground(new Color(0, 139, 139));
		setLayout(null);

		controller = new ItemController();

		try {
			itensComImagens = controller.consultarTodosComImagensController();

			if (itensComImagens.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Nenhuma imagem disponível.");
			} else {
				int numColunas = (int) Math.ceil(Math.sqrt(itensComImagens.size()));
				setLayout(new GridLayout((int) Math.ceil(itensComImagens.size() / (double) numColunas), numColunas));

				int espacamento = 10;
				EmptyBorder borda = new EmptyBorder(espacamento, espacamento, espacamento, espacamento);

				for (final ItemVO item : itensComImagens) {
					label = new JLabel();

					int tamanhoX = 325;
					int tamanhoY = 350;
					label.setSize(tamanhoX, tamanhoY);

					label.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {

							System.out.println("Label clicado: " + item.getProduto().getModelo());

							// guardar um valor para exibir em um label em outra tela
							modeloDoTenisClicado = item.getProduto().getModelo();
							valorDoTenisClicado = String.valueOf(item.getPrecoUnitario());
							
							imagemDoTenisClicado = new ImageIcon(item.getImagem());
						
							
							telaDetalhes = new TelaDetalhes(modeloDoTenisClicado, valorDoTenisClicado, imagemDoTenisClicado);

							telaDetalhes.tornarVisivelForaDoFrame();

						}
					});

					ImageIcon icon = new ImageIcon(item.getImagem());
					label.setText("Valor: " + item.getPrecoUnitario());
					label.setIcon(icon);
					label.setBorder(borda);
					label.setForeground(new Color(255, 255, 255));
					label.setFont(new Font("Segoe UI", Font.BOLD, 14));
					add(label);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getModeloSelecionado() {
		return modeloDoTenisClicado;
	}

	public String getValorSelecionado() {
		return valorDoTenisClicado;
	}
	public ImageIcon getImagemDoTenisClicado() {
	    return imagemDoTenisClicado;
	}
}
