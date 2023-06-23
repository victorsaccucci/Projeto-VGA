package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.CarrinhoItensVO;

public class CarrinhoItensDAO {

	
	public CarrinhoItensVO inserir(CarrinhoItensVO novoCarrinhoItem) {
		
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO CARRINHO_ITENS (IDTEM, IDCARRINHO, QUANTIDADE) VALUES (?,?,?)";
		PreparedStatement query = Banco.getPreparedStatementWithPk(conn, sql);
		try {
			query.setInt(1, novoCarrinhoItem.getItem().getId());
			query.setInt(2, novoCarrinhoItem.getCarrinho().getIdcarrinho());
			query.setInt(3, novoCarrinhoItem.getQuantidade());
			query.execute();
			
			ResultSet resultado = query.getGeneratedKeys();
			if(resultado.next()) {
				novoCarrinhoItem.setIdCarrinhoItens(resultado.getInt(1));
			}
		} catch(SQLException e){
			System.out.println("Erro ao inserir um novo carrinho de itens!" 
								+ "\n Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		return novoCarrinhoItem;
	}
	
	
	
}
