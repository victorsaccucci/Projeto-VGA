package model.bo;

import java.io.IOException;
import java.util.List;

import model.ExceptionVGA;
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

	public boolean excluirItemBO(int id) throws ExceptionVGA{
		return itemDAO.excluir(id);
	}

	public boolean atualizarItemBO(ItemVO item) {
		return itemDAO.atualizar(item);
	}

	public boolean diminuirtQuantidadeBO(int idItem, int quantidade) {
		return itemDAO.diminuirQuantidade(idItem, quantidade);
	}
	
}
