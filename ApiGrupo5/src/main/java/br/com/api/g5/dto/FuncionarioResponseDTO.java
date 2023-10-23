package br.com.api.g5.dto;

public class FuncionarioResponseDTO {
	
	private String nome;
	private String nomeUsuario;
	private String email;
	
	public FuncionarioResponseDTO() {
	}

	public FuncionarioResponseDTO(String nome, String nomeUsuario, String email) {
		this.nome = nome;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
	