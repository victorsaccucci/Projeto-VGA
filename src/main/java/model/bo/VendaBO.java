package model.bo;

import model.dao.VendaoDAO;
import model.vo.VendaVO;

public class VendaBO {

	VendaoDAO dao = new VendaoDAO();
	
	public VendaVO inserirVenda(VendaVO vendaVO) {
		return dao.inserirVenda(vendaVO);
	}

}
