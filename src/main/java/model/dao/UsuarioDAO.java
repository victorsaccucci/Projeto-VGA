package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.UsuarioVO;


public class UsuarioDAO {

	public UsuarioVO inserir(UsuarioVO novoUsuario) {
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO USUARIO (NOME, SENHA, EMAIL, CPF) VALUES (?, ?, ?, ?)";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, sql);
		try {
			stmt.setString(1, novoUsuario.getNome());
			stmt.setString(2, novoUsuario.getSenha());
			stmt.setString(3, novoUsuario.getEmail());
			stmt.setString(4, novoUsuario.getCpf());
			stmt.execute();
			
			ResultSet resultado = stmt.getGeneratedKeys();
			
			if(resultado.next()) {
				novoUsuario.setId(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir um novo usu�rio ");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return novoUsuario;
	}
	
	public boolean atualizar(UsuarioVO usuarioAtualizado) {
		Connection conn = Banco.getConnection();
		String sql = "UPDATE USUARIO SET NOME=?, SENHA=?, EMAIL=?, CPF=? WHERE IDUSUARIO=?";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		int registrosAlterados = 0;
		try {
			stmt.setString(1, usuarioAtualizado.getNome());
			stmt.setString(2, usuarioAtualizado.getSenha());
			stmt.setString(3, usuarioAtualizado.getEmail());
			stmt.setString(4, usuarioAtualizado.getCpf());
			registrosAlterados = stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar usuario ");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		return registrosAlterados > 0;
	}
	
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		String sql = "DELETE FROM USUARIO WHERE IDUSUARIO= " + id;
		Statement stmt = Banco.getStatement(conn);
		
		int quantidadesLinhasAfetadas = 0;
		try {
			quantidadesLinhasAfetadas = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir usu�rio ");
			System.out.println("Erro: " + e.getMessage());
		}
		
		boolean excluiu = quantidadesLinhasAfetadas > 0;
		
		return excluiu;
	}
	
	public UsuarioVO consultarClientePorId(int id) {
		UsuarioVO usuarioBuscado = null;
		Connection conn = Banco.getConnection();
		String sql = "SELECT * FROM USUARIO WHERE IDUSUARIO = ?";
		
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();
			
			if(resultado.next()) {
				usuarioBuscado = montarUsuarioComResultadoDoBanco(resultado);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar cliente por id: " + id + " Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		return usuarioBuscado;
	}

	
	public List<UsuarioVO> consultarTodos() {
		List<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();
		Connection conn = Banco.getConnection();
		String sql = " select * from usuario ";
		
		PreparedStatement query = Banco.getPreparedStatement(conn, sql);
		try {
			ResultSet resultado = query.executeQuery();
			
			while(resultado.next()) {
				UsuarioVO usuarioBuscado = montarUsuarioComResultadoDoBanco(resultado);
				usuarios.add(usuarioBuscado);
			}
			
		}catch (Exception e) {
			System.out.println("Erro ao buscar todos os usuarios. \n Causa:" + e.getMessage());
		}finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conn);
		}
		
		return usuarios;
	}
	
	private UsuarioVO montarUsuarioComResultadoDoBanco(ResultSet resultado) throws SQLException {
		UsuarioVO usuarioBuscado = new UsuarioVO();
		usuarioBuscado.setId(resultado.getInt("idusuario"));
		usuarioBuscado.setNome(resultado.getString("nome"));
		usuarioBuscado.setSenha(resultado.getString("senha"));
		usuarioBuscado.setEmail(resultado.getString("email"));
		usuarioBuscado.setCpf(resultado.getString("cpf"));
		
		return usuarioBuscado;
	}
	
	public UsuarioVO realizarLogin(UsuarioVO usuarioVO){
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		
		String sql = " SELECT U.IDUSUARIO, U.NOME, U.SENHA, U.EMAIL, U.CPF "
				+ " FROM	USUARIO U "
				+ " WHERE	U.LOGIN LIKE '" + usuarioVO.getLogin() + "' "
				+ " AND U.SENHA LIKE '" + usuarioVO.getSenha() + "' ";	
		try {
			resultado = stmt.executeQuery(sql);
			if(resultado.next()) {
				
				usuarioVO.setId(Integer.parseInt(resultado.getString(1)));
				usuarioVO.setNome(resultado.getString(2));
				usuarioVO.setEmail(resultado.getString(3));
				usuarioVO.setCpf(resultado.getString(4));
				usuarioVO.setLogin(resultado.getString(5));
				usuarioVO.setSenha(resultado.getString(6));
			}
		}catch(SQLException e) {
			System.out.println("Erro ao realizar login! \nCausa: " + e.getMessage());
		}finally {
			Banco.closeResultSet(resultado);
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
		}		
		return usuarioVO;
	}
	public UsuarioVO cadastrarUsuarioDAO(UsuarioVO usuarioVO) {
		String query = "INSERT INTO usuario (nome, cpf, email, senha, "
				+ " login) VALUES (?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setString(1, usuarioVO.getNome());
			pstmt.setString(2, usuarioVO.getCpf());
			pstmt.setString(3, usuarioVO.getEmail());
			pstmt.setString(4, usuarioVO.getSenha());
			pstmt.setString(5, usuarioVO.getLogin());			
			pstmt.execute();
			
			ResultSet resultado = pstmt.getGeneratedKeys();
			if(resultado.next()) {
				usuarioVO.setId(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar usuário! \nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return usuarioVO;
	}
	public boolean verificarLogin (int idUsuario) {
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		ResultSet resultado = null;
		
		String query = "SELECT idUsuario FROM usuario WHERE idUsuario = " + idUsuario;
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()){
				retorno = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao verificar existência do longin! \nCausa: "  + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}
	
}

