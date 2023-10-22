package br.com.api.g5.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name="tb_funcionario")
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_funcionario")
	private Integer id;
	
	@NotNull(message="Campo nome de funcionário não pode ser nulo")
	@Column(name="nome_funcionario")
	private String nome;
	
	@Pattern(regexp = "\\(\\d{2}\\) \\d{4}-\\d{4}")
	@Column(name = "telefone_fixo_funcionario")
	private String telefoneFixo;

	@Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}")
	@Column(name = "celular_funcionario")
	private String celular;
	
	@NotNull(message="Campo nome de usuário não pode ser nulo")
	@Column(name = "nome_usuario_funcionario")
	private String nomeUsuario;
	
	@NotNull(message="Campo CPF não pode ser nulo")
	@CPF
	@Size(max=14)
	@Column(name="cpf_funcionario")
	private String cpf;
	
	@NotNull(message="Campo e-mail não pode ser nulo")
	@Email
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
	@Column(name = "email_funcionario")
	private String email;

	@NotNull(message="Campo data de nascimento não pode ser nulo")
	@Column(name = "data_nascimento_funcionario")
	private LocalDate dataNascimento;
	
	@NotNull(message="Campo senha não pode ser nulo")
	@Size(max=10)
	@Column(name="password_funcionario")
	private String password;

	@Column(name = "ativo_funcionario")
	private Boolean ativo = true;
	
	@OneToOne
	@JoinColumn(name="endereco_id")
	private Endereco endereco;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Funcionario() {
	}

	public Funcionario(Integer id, @NotNull String nome, String telefoneFixo, String celular,
			@NotNull String nomeUsuario, @NotNull @Size(max = 11) String cpf, @NotNull String email,
			@NotNull LocalDate dataNascimento, @NotNull @Size(max = 10) String password, Boolean ativo,
			Endereco endereco, User user) {
		super();
		this.id = id;
		this.nome = nome;
		this.telefoneFixo = telefoneFixo;
		this.celular = celular;
		this.nomeUsuario = nomeUsuario;
		this.cpf = cpf;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.password = password;
		this.ativo = ativo;
		this.endereco = endereco;
		this.user = user;
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
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", telefoneFixo=" + telefoneFixo + ", celular=" + celular
				+ ", nomeUsuario=" + nomeUsuario + ", cpf=" + cpf + ", email=" + email + ", dataNascimento="
				+ dataNascimento + ", password=" + password + ", ativo=" + ativo + ", endereco=" + endereco + ", user="
				+ user + "]";
	}
	
}
