package model.vo;

public class CarrinhoVO {

	private int idcarrinho;
	private UsuarioVO usuario;
	private boolean status;
	
	public CarrinhoVO() {
		super();
	}

	
	
	public CarrinhoVO(UsuarioVO usuario, boolean status) {
		super();
		this.usuario = usuario;
		this.status = status;
	}



	public CarrinhoVO(int idcarrinho, UsuarioVO usuario, boolean status) {
		super();
		this.idcarrinho = idcarrinho;
		this.usuario = usuario;
		this.status = status;
	}

	public int getIdcarrinho() {
		return idcarrinho;
	}

	public void setIdcarrinho(int idcarrinho) {
		this.idcarrinho = idcarrinho;
	}

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return idcarrinho + ", " + usuario + ", " + status;
	}
	
	
	
}