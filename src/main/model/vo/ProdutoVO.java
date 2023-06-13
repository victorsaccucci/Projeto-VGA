package model.vo;

public class ProdutoVO {

	private int id;
	private String descricao;
	private String marca;
	
	
	public ProdutoVO() {
		super();
	}


	public ProdutoVO(String descricao, String marca) {
		super();
		this.descricao = descricao;
		this.marca = marca;
	}


	public ProdutoVO(int id, String descricao, String marca) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.marca = marca;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	@Override
	public String toString() {
		return id + " " + descricao + " " + marca;
	}
	
	
}
