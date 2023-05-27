package model.vo;

public class UsuarioVO {

	private int id;
	private String nome;
	private String email;
	private String cpf;
	
	//login 
	
	private String senha;
	private String login;
	
	
	
	public UsuarioVO() {
		super();
	}
	
	public UsuarioVO(String nome, String senha, String email, String cpf, String login) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.cpf = cpf;
		this.login = login;
	}
	
	public UsuarioVO(int id, String nome, String senha, String email, String cpf, String login) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.cpf = cpf;
		this.login = login;
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
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return  id + " " + nome + " " + senha + " " + email + " " + cpf + " " + login;
	}
	
}
