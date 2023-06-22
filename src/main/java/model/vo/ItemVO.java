package model.vo;

public class ItemVO {

	private int id;
	private ProdutoVO produto;
	private String cor;
	private String tamanho;
	private int quantidade;
	private double precoUnitario;
	private byte[] imagem; 
	
	
	public ItemVO() {
		super();
	}

	public ItemVO(String cor, String tamanho, int quantidade, double precoUnitario, byte[] imagem) {
		super();
		this.cor = cor;
		this.tamanho = tamanho;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
	}
	
	public ItemVO(int id, ProdutoVO produto, String cor, String tamanho, int quantidade, double precoUnitario, byte[] imagem) {
		super();
		this.id = id;
		this.produto = produto;
		this.cor = cor;
		this.tamanho = tamanho;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
		this.imagem = imagem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProdutoVO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoVO produto) {
		this.produto = produto;
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

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}	

	@Override
	public String toString() {
		return id + " " + produto.getId() + " " + cor + " " + tamanho + " " + quantidade + " " + precoUnitario;
	}

	
}
