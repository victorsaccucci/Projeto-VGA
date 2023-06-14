package model.vo;

public class ProdutoVO {

	private int id;
	private String modelo;
	private String marca;

	public ProdutoVO() {
		super();
	}

	public ProdutoVO(String modelo, String marca) {
		super();
		this.modelo = modelo;
		this.marca = marca;
	}

	public ProdutoVO(int id, String modelo, String marca) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.marca = marca;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "Modelo: " + this.getModelo() + " Marca: " + this.getMarca();
	}

}
