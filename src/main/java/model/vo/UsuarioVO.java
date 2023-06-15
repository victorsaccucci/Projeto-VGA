package model.vo;

public class UsuarioVO {

	private Integer id;
	private String nome;
	private String cpf;
	
	//login 
	private String email;
	private String senha;
	private boolean adm;
	
	
	
	public UsuarioVO() {
		super();
	}
	
	public UsuarioVO(String nome, String senha, String email, String cpf, boolean adm) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.cpf = cpf;
		this.adm = adm;
	}
	
	public UsuarioVO(Integer id, String nome, String senha, String email, String cpf, boolean adm) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.cpf = cpf;
		this.adm = adm;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public boolean isAdm() {
		return adm;
	}

	public void setAdm(boolean adm) {
		this.adm = adm;
	}
	
	@Override
	public String toString() {
		return  id + " " + nome + " " + senha + " " + email + " " + cpf + " " + adm;
	}

	
}
