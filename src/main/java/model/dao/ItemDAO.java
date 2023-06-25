package model.dao;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.Blob;

import model.seletor.SeletorItem;
import model.seletor.SeletorProduto;
import model.vo.ItemVO;
import model.vo.ProdutoVO;
import view.TelaMenuPrincipal;

public class ItemDAO {

	public ItemVO inserir(ItemVO novoItem) {
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO ITEM (TAMANHO, COR, QUANTIDADE, PRECOUNITARIO, IDPRODUTO, IMAGEM) VALUES (?,?,?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, sql);
		try {
			stmt.setString(1, novoItem.getTamanho());
			stmt.setString(2, novoItem.getCor());
			stmt.setInt(3, novoItem.getQuantidade());
			stmt.setDouble(4, novoItem.getPrecoUnitario());
			stmt.setInt(5, novoItem.getProduto().getId());
			stmt.setBytes(6, novoItem.getImagem());
			stmt.execute();

			ResultSet resultado = stmt.getGeneratedKeys();
			if (resultado.next()) {
				novoItem.setId(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir um novo item!" + "\n Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		return novoItem;
	}

	public boolean diminuirQuantidade(int idItem, int quantidade) {
		boolean atualizou = false;
		Connection conn = Banco.getConnection();
		String sql = "UPDATE ITEM SET QUANTIDADE = QUANTIDADE - ? WHERE IDITEM = ?";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);

		try {
			stmt.setInt(1, quantidade);
			stmt.setInt(2, idItem);

			int atualizados = stmt.executeUpdate();
			atualizou = atualizados > 0;

		} catch (SQLException e) {
			System.out.println("Erro ao diminuir quantidade do item!" + "\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		return atualizou;
	}

	public boolean atualizar(ItemVO itemVO) {
		boolean atualizou = false;
		Connection conn = Banco.getConnection();
		String slq = " UPDATE ITEM SET TAMANHO=?, COR=?, QUANTIDADE=?, PRECOUNITARIO=?, IDPRODUTO=?, IMAGEM=? WHERE IDITEM= "
				+ itemVO.getId();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, slq);

		try {
			stmt.setString(1, itemVO.getTamanho());
			stmt.setString(2, itemVO.getCor());
			stmt.setInt(3, itemVO.getQuantidade());
			stmt.setDouble(4, itemVO.getPrecoUnitario());
			stmt.setInt(5, itemVO.getProduto().getId());
			stmt.setBytes(6, itemVO.getImagem());

			int atualizados = stmt.executeUpdate();
			atualizou = atualizados > 0;

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar item!" + "\n Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		return atualizou;
	}

	public boolean excluir(int idItem) {
		boolean excluiu = false;
		Connection conn = Banco.getConnection();
		String sql = " DELETE FROM ITEM WHERE IDITEM = " + idItem;
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		try {
			int atualizadas = stmt.executeUpdate();
			excluiu = atualizadas > 0;

		} catch (SQLException e) {
			System.out.println("Erro ao excluir item!" + "\n Causa: " + e.getMessage());
		}
		return excluiu;
	}

	public boolean excluirItemPorIdProduto(int idProduto) {
		boolean excluiu = false;
		Connection conn = Banco.getConnection();
		String sql = " DELETE * FROM ITEM " + " WHERE IDPRODUTO = ? ";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);

		try {
			stmt.setInt(1, idProduto);
			int atualizadas = stmt.executeUpdate();
			excluiu = atualizadas > 0;

		} catch (SQLException e) {
			System.out.println("Erro ao excluir item por id do produto!" + "\n Causa: " + e.getMessage());
		}
		return excluiu;
	}

	public List<ItemVO> consultarTodos() {
		List<ItemVO> consultados = new ArrayList<ItemVO>();

		Connection conn = Banco.getConnection();
		String sql = " SELECT * FROM ITEM";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);

		try {
			ResultSet resultado = stmt.executeQuery();
			while (resultado.next()) {
				ItemVO itemConsultado = converterResultSetParaEntidadeComId(resultado);
				consultados.add(itemConsultado);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todos os itens! \nCausa: " + e.getMessage());
		} finally {
			Banco.closeConnection(conn);
			Banco.closePreparedStatement(stmt);
		}
		return consultados;
	}

	// pega a imagem
	public List<ItemVO> consultarItensComImagensDAO() throws IOException {
		List<ItemVO> itens = new ArrayList<ItemVO>();

		Connection conn = Banco.getConnection();
		String sql = "SELECT * FROM ITEM";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);

		try {
			ResultSet resultado = stmt.executeQuery();
			while (resultado.next()) {

				ItemVO itemConsultado = converterResultSetParaEntidadeComIdParaMetodoComImagem(resultado);

				Blob blob = (Blob) resultado.getBlob("imagem");
				InputStream inputStream = blob.getBinaryStream();
				BufferedImage imagemBanco = ImageIO.read(inputStream);

				byte[] imagemBytes = converterParaArrayDeBytes(imagemBanco);

				itemConsultado.setImagem(imagemBytes);

				itens.add(itemConsultado);

			}
		} catch (SQLException e1) {
			System.out.println("Erro ao consultar todos os itens com imagens! \nCausa: " + e1.getMessage());
		}

		return itens;
	}

	// converte para byte
	private byte[] converterParaArrayDeBytes(BufferedImage bufferedImage) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "png", baos);
		baos.flush();
		byte[] imageBytes = baos.toByteArray();
		baos.close();
		return imageBytes;
	}

	// para o metodo de buscar imagem
	private ItemVO converterResultSetParaEntidadeComIdParaMetodoComImagem(ResultSet resultado) throws SQLException {
		ItemVO itemConsultado = new ItemVO();
		itemConsultado.setId(resultado.getInt("iditem"));
		itemConsultado.setTamanho(resultado.getString("tamanho"));
		itemConsultado.setCor(resultado.getString("cor"));
		itemConsultado.setQuantidade(resultado.getInt("quantidade"));
		itemConsultado.setPrecoUnitario(resultado.getDouble("precoUnitario"));

		byte[] imagemBytes = resultado.getBytes("imagem");
		itemConsultado.setImagem(imagemBytes);

		ProdutoDAO produtoDAO = new ProdutoDAO();
		ProdutoVO produto = produtoDAO.consultarPorId(resultado.getInt("idProduto"));
		itemConsultado.setProduto(produto);

		return itemConsultado;
	}

	public ItemVO consultarPorId(int id) {
		ItemVO itemConsultado = null;
		Connection conn = Banco.getConnection();
		String sql = " SELECT * FROM ITEM WHERE IDITEM = ? ";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		try {
			stmt.setInt(1, id);
			ResultSet resultado = stmt.executeQuery();

			if (resultado.next()) {
				itemConsultado = converterResultSetParaEntidadeComId(resultado);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todos os itens! \nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		return itemConsultado;
	}

	private ItemVO converterResultSetParaEntidadeComId(ResultSet resultado) throws SQLException {
		ItemVO itemConsultado = new ItemVO();
		itemConsultado.setId(resultado.getInt("iditem"));
		itemConsultado.setTamanho(resultado.getString("tamanho"));
		itemConsultado.setCor(resultado.getString("cor"));
		itemConsultado.setQuantidade(resultado.getInt("quantidade"));
		itemConsultado.setPrecoUnitario(resultado.getDouble("precoUnitario"));

		ProdutoDAO produtoDAO = new ProdutoDAO();
		ProdutoVO produto = produtoDAO.consultarPorId(resultado.getInt("idProduto"));
		itemConsultado.setProduto(produto);

		return itemConsultado;
	}

	public List<ItemVO> consultarComFiltros(SeletorItem seletor, SeletorProduto seletorProduto) {
		List<ItemVO> itens = new ArrayList<ItemVO>();
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM ITEM ";

		if (seletor.temFiltro()) {
			sql = preencherFiltros(sql, seletor, seletorProduto);
		}

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();
			while (resultado.next()) {
				ItemVO itemBuscado = converterResultSetParaEntidadeComId(resultado);
				itens.add(itemBuscado);
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar os itens! \n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return itens;
	}

	private String preencherFiltros(String sql, SeletorItem seletor, SeletorProduto seletorProduto) {
		boolean primeiro = true;

		if (seletorProduto != null) {
			if (seletorProduto.getMarca() != null && !seletorProduto.getMarca().trim().isEmpty()) {
				if (primeiro) {
					sql += " INNER JOIN PRODUTO ON ITEM.IDPRODUTO = PRODUTO.IDPRODUTO ";
					sql += " WHERE ";
				} else {
					sql += " AND ";
				}
				sql += " PRODUTO.MARCA LIKE '%" + seletorProduto.getMarca() + "%'";
				primeiro = false;
			}

			if (seletorProduto.getModelo() != null && !seletorProduto.getModelo().trim().isEmpty()) {
				if (primeiro) {
					sql += " INNER JOIN PRODUTO ON ITEM.IDPRODUTO = PRODUTO.IDPRODUTO ";
					sql += " WHERE ";
				} else {
					sql += " AND ";
				}
				sql += " PRODUTO.MODELO LIKE '%" + seletorProduto.getModelo() + "%'";
				primeiro = false;
			}
		}

		if (seletor.getCor() != null && !seletor.getCor().trim().isEmpty()) {
			if (primeiro) {
				sql += " WHERE ";
			} else {
				sql += " AND ";
			}

			sql += " cor LIKE '%" + seletor.getCor() + "%'";
			primeiro = false;
		}

		if (seletor.getQuantidade() != null) {
			if (primeiro) {
				sql += " WHERE ";
			} else {
				sql += " AND ";
			}

			sql += " quantidade LIKE '%" + seletor.getQuantidade() + "%'";
			primeiro = false;
		}

		if (seletor.getTamanho() != null) {
			if (primeiro) {
				sql += " WHERE ";
			} else {
				sql += " AND ";
			}

			sql += " tamanho LIKE '%" + seletor.getTamanho() + "%'";
			primeiro = false;
		}

		if (seletor.getPrecoInicial() == null && seletor.getPrecoFinal() == null) {
			return sql;
		}

		if (seletor.getPrecoInicial() != null && !seletor.getPrecoInicial().isEmpty() && seletor.getPrecoFinal() != null
				&& !seletor.getPrecoFinal().isEmpty()) {
			double precoInicial = Double.parseDouble(seletor.getPrecoInicial());
			double precoFinal = Double.parseDouble(seletor.getPrecoFinal());
			if (precoInicial > 0 && precoFinal > 0) {
				if (primeiro) {
					sql += " WHERE ";
				} else {
					sql += " AND ";
				}
				sql += " precoUnitario BETWEEN '" + precoInicial + "' " + " AND '" + precoFinal + "' ";
				primeiro = false;
			} else {
				if (precoInicial > 0) {
					if (primeiro) {
						sql += " WHERE ";
					} else {
						sql += " AND ";
					}
					sql += " precoUnitario >= '" + precoInicial + "' ";
					primeiro = false;
				}

				if (precoFinal > 0) {
					if (primeiro) {
						sql += " WHERE ";
					} else {
						sql += " AND ";
					}
					sql += " precoUnitario <= '" + precoFinal + "' ";
					primeiro = false;
				}
			}
		}
		return sql;
	}

	public int verificaQuantidadePorIdItem(int idDoItemSelecionado) {
		int quantidade = 0;
		
		Connection conn = Banco.getConnection();
		String sql = "SELECT ITEM.QUANTIDADE FROM ITEM WHERE IDITEM = ?";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		return quantidade;
	}

}
