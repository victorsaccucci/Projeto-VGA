package controller;

import java.util.List;

import model.ExceptionVGA;
import model.bo.ItemBO;
import model.seletor.SeletorItem;
import model.vo.ItemVO;

public class ItemController {

	ItemBO bo = new ItemBO();
	
	public List<ItemVO> consultarTodos() {
		return bo.consultarTodos();
	}
	
	public ItemVO inserirItem(ItemVO novoItem) throws ExceptionVGA {
		this.validarCamposObrigatorios(novoItem);
		return bo.inserirItem(novoItem);
	}

	private void validarCamposObrigatorios(ItemVO i) throws ExceptionVGA {
		String validacao = "";
		
		if(i.getIdProduto() == 0) {
			validacao += "Informe um produto! \n";
		}
		
		if(i.getTamanho() == null) {
			validacao += "Informe um tamanho! \n";
		}
		
		if(i.getCor() == null) {
			validacao += "Informe uma cor! \n";
		}
		
		if(i.getQuantidade() == 0) {
			validacao += "Informe uma quantidade! \n";
		}
		
		if(i.getPrecoUnitario() == 0) {
			validacao += "Informe um preço! \n";
		}
		
		if(!validacao.isEmpty()) {
			throw new ExceptionVGA(validacao);
		}
	}
	
	public List<ItemVO> consultarComFiltros(SeletorItem seletor){
		return bo.consultarComFiltros(seletor);
	}
	
}
