package controller;

import java.util.List;

import model.ExceptionVGA;
import model.GeradorPlanilha;
import model.bo.VendaBO;
import model.vo.VendaVO;

public class VendaController {

	VendaBO bo = new VendaBO();

	public VendaVO inserirVenda(VendaVO vendaVO) {
		return bo.inserirVenda(vendaVO);

	}

	public List<VendaVO> consultarTodos() {
		return bo.consultarTodos();
		// TODO Auto-generated method stub
		
	}

	public String gerarPlanilha(List<VendaVO> vendas, String caminhoEscolhido) throws ExceptionVGA {

		if(vendas == null || caminhoEscolhido == null || caminhoEscolhido.trim().isEmpty()) {
			throw new ExceptionVGA("Preencha todos os campos");
		}
		
		GeradorPlanilha gerador = new GeradorPlanilha();
		return gerador.gerarPlanilhaVendas(vendas, caminhoEscolhido);
	}

}
