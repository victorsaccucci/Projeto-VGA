package model.vo;

public class UsuarioVO {

	private int id;
	private String nome;
	private String senha;
	private String email;
	private String cpf;
	
	
	public UsuarioVO() {
		super();
	}
	
	public UsuarioVO(String nome, String senha, String email, String cpf) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.cpf = cpf;
	}
	
	public UsuarioVO(int id, String nome, String senha, String email, String cpf) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.cpf = cpf;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return  id + " " + nome + " " + senha + " " + email + " " + cpf;
	}
	
}
