package model.vo;

public class CarrinhoItensVO {

	private int idCarrinhoItens;
	private ItemVO item;
	private CarrinhoVO carrinho;
	private int quantidade;
	
	
	public CarrinhoItensVO() {
		super();
	}


	public CarrinhoItensVO(int idCarrinhoItens, ItemVO item, CarrinhoVO carrinho, int quantidade) {
		super();
		this.idCarrinhoItens = idCarrinhoItens;
		this.item = item;
		this.carrinho = carrinho;
		this.quantidade = quantidade;
	}


	public int getIdCarrinhoItens() {
		return idCarrinhoItens;
	}


	public void setIdCarrinhoItens(int idCarrinhoItens) {
		this.idCarrinhoItens = idCarrinhoItens;
	}


	public ItemVO getItem() {
		return item;
	}


	public void setItem(ItemVO item) {
		this.item = item;
	}


	public CarrinhoVO getCarrinho() {
		return carrinho;
	}


	public void setCarrinho(CarrinhoVO carrinho) {
		this.carrinho = carrinho;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	@Override
	public String toString() {
		return idCarrinhoItens + ", " + item + ", " + carrinho + ", " + quantidade;
	}
	
	
}
