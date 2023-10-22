package br.com.api.g5.dto;

public class ClienteAtualizarDTO {

	private String nome;
	private String telefoneFixo;
	private String celular;
	private String email;
	private String password;
	private String cep;
	private String complemento;
	private String numero;

	public ClienteAtualizarDTO(String nome, String telefoneFixo, String celular, String email, String password,
			String cep, String complemento, String numero) {
		super();
		this.nome = nome;
		this.telefoneFixo = telefoneFixo;
		this.celular = celular;
		this.email = email;
		this.password = password;
		this.cep = cep;
		this.complemento = complemento;
		this.numero = numero;
	}

	public ClienteAtualizarDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
