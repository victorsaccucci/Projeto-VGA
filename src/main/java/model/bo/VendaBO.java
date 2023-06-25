package model.bo;

import java.util.List;

import model.dao.VendaoDAO;
import model.vo.VendaVO;

public class VendaBO {

	VendaoDAO dao = new VendaoDAO();

	public VendaVO inserirVenda(VendaVO vendaVO) {
		return dao.inserirVenda(vendaVO);
	}

	public List<VendaVO> consultarTodos() {
		// TODO Auto-generated method stub
		return dao.consultarTodos();
	}

}
