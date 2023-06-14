package model.seletor;

public class SeletorItem extends SeletorBase{
	
	private int tamanho;
	private int quantidade;
	private double precoInicial;
	private double precoFinal;
	
	@Override
	public boolean temFiltro() {
		return (this.tamanho > 0)
				|| (this.quantidade > 0)
				|| this.precoInicial != 0
				|| this.precoFinal != 0;
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
	public double getPrecoInicial() {
		return precoInicial;
	}
	public void setPrecoInicial(double precoInicial) {
		this.precoInicial = precoInicial;
	}
	public double getPrecoFinal() {
		return precoFinal;
	}
	public void setPrecoFinal(double precoFinal) {
		this.precoFinal = precoFinal;
	}
	
	

}
