package controller;

import java.io.IOException;
import java.util.List;

import model.ExceptionVGA;
import model.GeradorPlanilha;
import model.bo.ItemBO;
import model.seletor.SeletorItem;
import model.seletor.SeletorProduto;
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
		
		if(i.getProduto() == null) {
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

		if(i.getImagem() == null) {
			validacao += "Insira uma imagem! \n";
		}
		
		if(!validacao.isEmpty()) {
			throw new ExceptionVGA(validacao);
		}
	}
	
	public boolean excluirItemController(int id) throws ExceptionVGA {
		return bo.excluirItemBO(id);
	}
	
	public List<ItemVO> consultarComFiltros(SeletorItem seletor, SeletorProduto seletorProduto){
		return bo.consultarComFiltros(seletor, seletorProduto);
	}
	
	public List<ItemVO> consultarTodosComImagensController() throws IOException {
		return bo.consultarTodosComImagensBO();
	}
	
	public String gerarPlanilha(List<ItemVO> item, String caminho) throws ExceptionVGA {
		
		if(item == null || caminho == null || caminho.trim().isEmpty()) {
			throw new ExceptionVGA("Preencha todos os campos");
		}
		
		GeradorPlanilha gerador = new GeradorPlanilha();
		return gerador.gerarPlanilhaProdutos(item, caminho);
	}

	public boolean atualizarItem(ItemVO item) {
		return bo.atualizarItemBO(item);
		
	}

	public boolean diminuirtQuantidadeController(int idItem, int quantidade) {
		return bo.diminuirtQuantidadeBO(idItem, quantidade);
	}

	public int verificaQuantidadePorIdItem(int idDoItemSelecionado) {
		return bo.verificaQuantidadePorIdItem(idDoItemSelecionado);

	}
	
}
