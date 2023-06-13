package model.vo;

public class ItemVO {

	private int id;
	private int idProduto;
	private String cor;
	private String tamanho;
	private int quantidade;
	private double precoUnitario;
	
	
	public ItemVO() {
		super();
	}

	public ItemVO(String cor, String tamanho, int quantidade, double precoUnitario) {
		super();
		this.cor = cor;
		this.tamanho = tamanho;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
	}
	
	public ItemVO(int id, int idProduto, String cor, String tamanho, int quantidade, double precoUnitario) {
		super();
		this.id = id;
		this.idProduto = idProduto;
		this.cor = cor;
		this.tamanho = tamanho;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	@Override
	public String toString() {
		return id + " " + idProduto + " " + cor + " " + tamanho + " " + quantidade + " " + precoUnitario;
	}


	
	
	

}
