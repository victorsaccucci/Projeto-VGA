package model.bo;

import model.ExceptionVGA;
import model.dao.ProdutoDAO;
import model.vo.ProdutoVO;

public class ProdutoBO {
	
	ProdutoDAO dao = new ProdutoDAO();
	
	public ProdutoVO inserir(ProdutoVO novoProduto) throws ExceptionVGA{
		
		return dao.inserir(novoProduto);
	}

}
