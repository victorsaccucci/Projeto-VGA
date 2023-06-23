package model.bo;

import java.io.IOException;
import java.util.List;

import model.dao.ItemDAO;
import model.seletor.SeletorItem;
import model.vo.ItemVO;

public class ItemBO {

	ItemDAO itemDAO = new ItemDAO();
	
	public List<ItemVO> consultarTodos() {		
		return itemDAO.consultarTodos();
	}

	public ItemVO inserirItem(ItemVO novoItem) {
		return itemDAO.inserir(novoItem);
	}
	
	public List<ItemVO> consultarComFiltros(SeletorItem seletor){
		return itemDAO.consultarComFiltros(seletor);
	}
	


	public List<ItemVO> consultarTodosComImagensBO() throws IOException {	
		return itemDAO.consultarItensComImagensDAO();
	}
	
}
