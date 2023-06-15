package model.bo;

import java.util.List;

import model.dao.ItemDAO;
import model.seletor.SeletorItem;
import model.vo.ItemVO;

public class ItemBO {

	ItemDAO dao = new ItemDAO();
	
	public List<ItemVO> consultarTodos() {		
		return dao.consultarTodos();
	}

	public ItemVO inserirItem(ItemVO novoItem) {
		return dao.inserir(novoItem);
	}
	
	public List<ItemVO> consultarComFiltros(SeletorItem seletor){
		return dao.consultarComFiltros(seletor);
	}
	
}
