package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.ProdutoVO;

public class ProdutoDAO {
	
	public ProdutoVO inserir(ProdutoVO novoProduto) {
		
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO PRODUTO (DESCRICAO, MARCA) VALUES (?,?)";
		PreparedStatement query = Banco.getPreparedStatementWithPk(conn, sql);
		try {
			query.setString(1,novoProduto.getDescricao());
			query.setString(2, novoProduto.getMarca());
			query.execute();
			
			ResultSet resultado = query.getGeneratedKeys();
			if(resultado.next()) {
				novoProduto.setId(resultado.getInt(1));
			}
		} catch(SQLException e){
			System.out.println("Erro ao inserir um novo produto!" 
								+ "\n Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return novoProduto;
	}
	
	public boolean excluir(int id) {
		boolean excluiu = false;
		
		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM PRODUTO WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			
			int linhasAtualizadas = query.executeUpdate();
			excluiu = linhasAtualizadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao excluir produto! " 
							+ "\n Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return excluiu;
	}
	
	public boolean atualizar(ProdutoVO produtoEditado) {
		boolean atualizou = false;
		
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE PRODUTO "
				   + " SET DESCRICAO = ?, MARCA = ? "
				   + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setString(1, produtoEditado.getDescricao());
			query.setString(2, produtoEditado.getMarca());
			query.setInt(3, produtoEditado.getId());
			
			int linhasAtualizadas = query.executeUpdate();
			atualizou = linhasAtualizadas > 0;
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar produto! "
								+ "\n Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return atualizou;
	}
	
	public List<ProdutoVO> consultarTodos(){
		List<ProdutoVO> produtos = new ArrayList<ProdutoVO>();
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PRODUTO ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		try {
			ResultSet resultado = query.executeQuery();
			while(resultado.next()) {
				ProdutoVO produtoConsultado = converterResultSetParaEntidade(resultado);
				produtos.add(produtoConsultado);
			}
		} catch(SQLException e) {
			System.out.println("Erro ao buscar todos produtos!"
							  +"\n Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return produtos;
	}
	
	public ProdutoVO consultarPorId(int id) {
		ProdutoVO produtoConsultado = null;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PRODUTO "
				   + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();
			
			if(resultado.next()) {
				produtoConsultado = converterResultSetParaEntidade(resultado);
			}
		}catch(SQLException e) {
			System.out.println("Erro ao buscar item com id: " + id
								+"\n Causa: " +e.getMessage());
		}finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return produtoConsultado;
	}

	private ProdutoVO converterResultSetParaEntidade(ResultSet resultado) throws SQLException{
		ProdutoVO produtoConsultado = new ProdutoVO();
		produtoConsultado.setId(resultado.getInt("id"));
		produtoConsultado.setMarca(resultado.getString("marca"));
		produtoConsultado.setDescricao(resultado.getString("descricao"));
		
		return produtoConsultado;
	}

}
