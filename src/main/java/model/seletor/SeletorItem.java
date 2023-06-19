package model.seletor;

public class SeletorItem extends SeletorBase{
	
	//TODO incluir filtro para o carrinho
	private int tamanho;
	private int quantidade;
	private String cor;
	private String precoInicial;
	private String precoFinal;
	
	@Override
	public boolean temFiltro() {
		return (this.tamanho > 0)
				|| (this.quantidade > 0)
				|| this.precoInicial != null
				|| this.precoFinal != null
				|| this.cor != null;
	}
	
	
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getPrecoInicial() {
		return precoInicial;
	}
	public void setPrecoInicial(String string) {
		this.precoInicial = string;
	}
	public String getPrecoFinal() {
		return precoFinal;
	}
	public void setPrecoFinal(String precoFinal) {
		this.precoFinal = precoFinal;
	}


	public String getCor() {
		return cor;
	}


	public void setCor(String cor) {
		this.cor = cor;
	}
	
	

}
