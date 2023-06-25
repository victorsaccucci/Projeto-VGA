package controller;

import model.bo.VendaBO;
import model.vo.VendaVO;

public class vendaController {

	VendaBO bo = new VendaBO();
	
	public VendaVO inserirVenda(VendaVO vendaVO, int idUsuario) {
		
		return bo.inserirVenda(vendaVO, idUsuario);
	}

}
