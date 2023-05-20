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
			System.out.println("Erro ao inserir um novo item!" + e.getMessage());
		} finally {
			Banco.closeConnection(conn);
			Banco.closePreparedStatement(stmt);
		}
		return novoItem;
	}
}
