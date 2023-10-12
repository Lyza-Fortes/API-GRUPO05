package br.com.api.g5.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cliente")
	private Integer id;

	@NotNull
	@Column(name = "nome_cliente")
	private String nome;

	@NotNull
	@Column(name = "endereco")
	private String endereco;

	@Column(name = "telefone_fixo")
	private String telefoneFixo;

	@Column(name = "celular")
	private String celular;

	@NotNull
	@Column(name = "nome_usuario")
	private String nomeUsuario;

	@NotNull
	@Size(max = 13)
	@Column(name = "cpf_cliente")
	private String cpf;

	@NotNull
	@Column(name = "email")
	private String email;

	@NotNull
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	public Cliente() {
		super();
	}

	public Cliente(Integer id, @NotNull String nome, @NotNull String endereco, String telefoneFixo, String celular,
			@NotNull String nomeUsuario, @NotNull @Size(max = 13) String cpf, @NotNull String email,
			@NotNull LocalDate dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.telefoneFixo = telefoneFixo;
		this.celular = celular;
		this.nomeUsuario = nomeUsuario;
		this.cpf = cpf;
		this.email = email;
		this.dataNascimento = dataNascimento;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", telefoneFixo=" + telefoneFixo
				+ ", celular=" + celular + ", nomeUsuario=" + nomeUsuario + ", cpf=" + cpf + ", email=" + email
				+ ", dataNascimento=" + dataNascimento + "]";
	}

}