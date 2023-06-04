package controller;

import java.util.List;

import model.ExceptionVGA;
import model.bo.ProdutoBO;
import model.vo.ProdutoVO;

public class ProdutoController {
	
	ProdutoBO produtoBO = new ProdutoBO();
	
	public ProdutoVO inserirProdutoController(ProdutoVO novoProduto) throws ExceptionVGA {
		this.validarCamposObrigatorios(novoProduto);
		return produtoBO.inserir(novoProduto);
	}
	
	public boolean atualizarProdutoController(ProdutoVO produtoVO) {
		return produtoBO.atualizar(produtoVO);
	}
	
	private void validarCamposObrigatorios(ProdutoVO p) throws ExceptionVGA {
		String validacao = "";
		
		if(p.getMarca() == null) {
			validacao += "Informe uma marca! \n";
		}
		
		if(p.getDescricao() == null) {
			validacao += "Informe um modelo! \n";
		}
		
		if(!validacao.isEmpty()) {
			throw new ExceptionVGA(validacao);
		}
	}
	
	public boolean excluirProdutoController(int produtoVO) {
		return produtoBO.excluir(produtoVO);
	}
	
	public List<ProdutoVO> consultarTodosProdutosController(){
		return produtoBO.consultarTodos();
	}
	
	public ProdutoVO consultarProdutoPorIdController(int id) {
		return produtoBO.consultarPorId(id);
	}

}
