package model.bo;

import java.io.IOException;
import java.util.List;

import model.ExceptionVGA;
import model.dao.ItemDAO;
import model.seletor.SeletorItem;
import model.seletor.SeletorProduto;
import model.vo.ItemVO;

public class ItemBO {

	ItemDAO itemDAO = new ItemDAO();
	
	public List<ItemVO> consultarTodos() {		
		return itemDAO.consultarTodos();
	}

	public ItemVO inserirItem(ItemVO novoItem) {
		return itemDAO.inserir(novoItem);
	}
	
	public List<ItemVO> consultarComFiltros(SeletorItem seletor, SeletorProduto seletorProduto){
		return itemDAO.consultarComFiltros(seletor, seletorProduto);
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
