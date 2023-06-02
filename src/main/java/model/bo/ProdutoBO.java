package model.bo;

import java.util.List;

import model.ExceptionVGA;
import model.dao.ProdutoDAO;
import model.vo.ProdutoVO;

public class ProdutoBO {
	
	ProdutoDAO dao = new ProdutoDAO();
	
	public ProdutoVO inserir(ProdutoVO novoProduto) throws ExceptionVGA{
		
		return dao.inserir(novoProduto);
	}
	
	public boolean atualizar(ProdutoVO produtoAlterado) {
		return dao.atualizar(produtoAlterado);
	}
	
	public boolean excluir(int id) {
		return dao.excluir(id);
	}
	
	public ProdutoVO consultarPorId(int id) {
		return dao.consultarPorId(id);
	}
	
	public List<ProdutoVO> consultarTodos(){
		return dao.consultarTodos();
	}

}
