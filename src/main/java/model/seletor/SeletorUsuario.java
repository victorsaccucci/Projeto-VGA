package model.seletor;

public class SeletorUsuario extends SeletorBase{
	
	private String email;
	private String nome;
	private String cpf;
	
	@Override
	public boolean temFiltro() {
		return  (this.email != null)
				|| (this.nome != null)
				|| this.cpf != null;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
