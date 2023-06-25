package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.ItemVO;
import model.vo.UsuarioVO;
import model.vo.VendaVO;

public class VendaoDAO {

	
	public VendaVO inserirVenda(VendaVO vendaVO) {
		Connection conn = Banco.getConnection();
		String sql = " INSERT INTO VENDA(DATA_VENDA, IDITEM, IDUSUARIO) VALUES(?,?,?) ";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, sql);
		try {
			stmt.setString(1, vendaVO.getDataVenda());
			stmt.setInt(2, vendaVO.getIdItem());
			stmt.setInt(3, vendaVO.getIdUsuario());
			stmt.execute();
			
			
			ResultSet resultado = stmt.getGeneratedKeys();
			if(resultado.next()) {
				vendaVO.setId(resultado.getInt(1));
				
			}
			
		} catch (SQLException e) {
			System.out.println(vendaVO.getIdItem());
			System.out.println("Erro ao inserir nova venda.");
			System.out.println("Erro: " + e.getMessage());
		}
		return vendaVO;
	}
	
	public List<VendaVO> consultarTodos() {
		List<VendaVO> consultados = new ArrayList<VendaVO>();

		Connection conn = Banco.getConnection();
		String sql = " SELECT * FROM VENDA ";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		try {
			ResultSet resultado = stmt.executeQuery();
			while(resultado.next()) {
				VendaVO vendaConsultada = converterResultSetParaEntidadeComId(resultado);
				consultados.add(vendaConsultada);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todas vendas! \nCausa: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Banco.closeConnection(conn);
			Banco.closePreparedStatement(stmt);
		}
		return consultados;
	}

	private VendaVO converterResultSetParaEntidadeComId(ResultSet resultado) throws SQLException {
		VendaVO vendaBuscada = new VendaVO();
		vendaBuscada.setId(resultado.getInt("idvenda"));
		vendaBuscada.setDataVenda(resultado.getString("data_venda"));
		vendaBuscada.setIdItem(resultado.getInt("iditem"));
		vendaBuscada.setIdUsuario(resultado.getInt("idusuario"));
		
		
		return vendaBuscada;
	}
 
}
