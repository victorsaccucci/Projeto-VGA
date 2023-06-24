package model.seletor;

public class SeletorProduto extends SeletorBase{
	
	private String marca;
	private String modelo;
	
	@Override
	public boolean temFiltro() {
		return (this.marca != null)
				|| (this.modelo != null);
	}
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
}
