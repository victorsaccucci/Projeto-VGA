package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.VendaVO;

public class VendaoDAO {

	
	public VendaVO inserirVenda(VendaVO vendaVO, int idUsuario) {
		Connection conn = Banco.getConnection();
		String sql = " INSERT INTO VENDA(DATA_VENDA, IDITEM, IDUSUARIO) VALUES(?,?,?) ";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, sql);
		try {
			stmt.setDate(1, java.sql.Date.valueOf(vendaVO.getDataVenda()));
			stmt.setInt(2, vendaVO.getIdItem());
			stmt.setInt(3, idUsuario);
			
			ResultSet resultado = stmt.getGeneratedKeys();
			if(resultado.next()) {
				vendaVO.setId(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir nova venda.");
			System.out.println("Erro: " + e.getMessage());
		}
		return null;
	}

}
