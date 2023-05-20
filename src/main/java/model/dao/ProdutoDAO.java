package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.ProdutoVO;

public class ProdutoDAO {
	
	public ProdutoVO inserir(ProdutoVO novoProduto) {
		
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO PRODUTO (DESCRICAO, MARCA) VALUES (?,?)";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, sql);
		try {
			stmt.setString(1,novoProduto.getDescricao());
			stmt.setString(2, novoProduto.getMarca());
			stmt.execute();
			
			ResultSet resultado = stmt.getGeneratedKeys();
			if(resultado.next()) {
				novoProduto.setId(resultado.getInt(1));
			}
		} catch(SQLException e){
			System.out.println("Erro ao inserir um novo produto!" + e.getMessage());
		} finally {
			Banco.closeConnection(conn);
			Banco.closePreparedStatement(stmt);
		}
		
		return novoProduto;
	}

}
