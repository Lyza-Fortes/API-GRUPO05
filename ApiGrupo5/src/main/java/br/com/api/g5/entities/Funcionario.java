package br.com.api.g5.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="funcionario")
public class Funcionario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_funcionario")
	private Integer id;
	
	@NotNull
	@Column(name="nome_funcionario")
	private String nome;
	
	
	@NotNull
	@Size(max=13)
	@Column(name="cpf_funcionario")
	private String cpf;
	
	@NotNull
	@Size(max=10)
	@Column(name="senha_funcionario")
	private String senha;

	@NotNull
	@Column(name = "ativo_funcionario")
	private Boolean ativo = true;

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Funcionario() {
		super();
	}

	public Funcionario(Integer id, @NotNull String nome, @NotNull @Size(max = 13) String cpf,
			@NotNull @Size(max = 10) String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", senha=" + senha + "]";
	}	
}
