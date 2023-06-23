package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.CarrinhoVO;

public class CarrinhoDAO {

	public CarrinhoVO inserir(Integer idUsuario) {
		CarrinhoVO novoCarrinho = new CarrinhoVO();
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO CARRINHO (IDUSUARIO, STATUS) VALUES (?,?)";
		PreparedStatement query = Banco.getPreparedStatementWithPk(conn, sql);
		try {
			query.setInt(1, idUsuario);
			query.setBoolean(2, novoCarrinho.isStatus());
			query.execute();
			
			ResultSet resultado = query.getGeneratedKeys();
			if(resultado.next()) {
				novoCarrinho.setIdcarrinho(resultado.getInt(1));
			}
		} catch(SQLException e){
			System.out.println("Erro ao inserir um novo carrinho!" 
								+ "\n Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		return novoCarrinho;
	}
	
	public boolean verificarExistenciaCarrinho(String cpf) {
		boolean carrinhoJaAberto = false;
		Connection conexao = Banco.getConnection();
		String sql = " select count(*) from carrinho c "
				+ " left join usuario u on u.idusuario = c.idusuario "
				+ " where u.cpf = ? and c.status = 1  ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setString(1, cpf);
			ResultSet resultado = query.executeQuery();
			
			if(resultado.next()) {
				carrinhoJaAberto = resultado.getInt(1) > 0;
				System.out.println("verificação ok!");
			}
		}catch (Exception e) {
			System.out.println("Erro ao verificar uso do carrinho " 
					+ "\n Causa:" + e.getMessage());
		}finally {
			
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return carrinhoJaAberto;
	}
	
}
