package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.ItemVO;

public class ItemDAO {

	public ItemVO inserir(ItemVO novoItem) {
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO ITEM (TAMANHO, COR, QUANTIDADE, PRECO_UNITARIO, IDPRODUTO) VALUES (?,?,?,?,?)";
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
		String slq = " UPDATE ITEM SET TAMANHO=?, COR=?, QUANTIDADE=?, PRECO_UNITARIO=?, IDPRODUTO=? "
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
		String sql = " DELET * FROM ITEM " + " WHERE IDITEM = ? ";
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
}
