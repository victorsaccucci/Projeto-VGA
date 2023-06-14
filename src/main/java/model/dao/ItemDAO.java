package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.ItemVO;


public class ItemDAO {

	public ItemVO inserir(ItemVO novoItem) {
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO ITEM (TAMANHO, COR, QUANTIDADE, PRECOUNITARIO, IDPRODUTO) VALUES (?,?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, sql);
		try {
			stmt.setString(1, novoItem.getTamanho());
			stmt.setString(2, novoItem.getCor());
			stmt.setInt(3, novoItem.getQuantidade());
			stmt.setDouble(4, novoItem.getPrecoUnitario());
			stmt.setInt(5, novoItem.getIdProduto());
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

	public boolean atualizar(ItemVO itemVO) {
		boolean atualizou = false;
		Connection conn = Banco.getConnection();
		String slq = " UPDATE ITEM SET TAMANHO=?, COR=?, QUANTIDADE=?, PRECOUNITARIO=?, IDPRODUTO=? "
				+ " WHERE IDITEM=? ";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, slq);

		try {
			stmt.setString(1, itemVO.getTamanho());
			stmt.setString(2, itemVO.getCor());
			stmt.setInt(3, itemVO.getQuantidade());
			stmt.setDouble(4, itemVO.getPrecoUnitario());
			stmt.setInt(5, itemVO.getIdProduto());

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
		String sql = " DELETE * FROM ITEM " + " WHERE IDITEM = ? ";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		try {
			stmt.setInt(1, idItem);
			int atualizadas = stmt.executeUpdate();
			excluiu = atualizadas > 0;

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar item!" + "\n Causa: " + e.getMessage());
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

	public ItemVO consultarPorId(int id) {
		ItemVO itemConsultado = null;
		Connection conn = Banco.getConnection();
		String sql = " SELECT * FROM ITEM WHERE ID = ? ";
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
		itemConsultado.setId(resultado.getInt("IDITEM"));
		itemConsultado.setTamanho(resultado.getString("tamanho"));
		itemConsultado.setCor(resultado.getString("cor"));
		itemConsultado.setQuantidade(resultado.getInt("quantidade"));
		itemConsultado.setPrecoUnitario(resultado.getDouble("precoUnitario"));
		itemConsultado.setIdProduto(resultado.getInt("idProduto"));

		return itemConsultado;
	}
	
}
