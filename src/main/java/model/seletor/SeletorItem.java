package model.seletor;

public class SeletorItem extends SeletorBase{
	
	//TODO incluir filtro para o carrinho
	private String tamanho;
	private String quantidade;
	private String cor;
	private String precoInicial;
	private String precoFinal;
	
	@Override
	public boolean temFiltro() {
		return (this.tamanho != null)
				|| (this.quantidade != null)
				|| (this.precoInicial != null)
				|| (this.precoFinal != null)
				|| this.cor != null;
	}
	
	
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
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
