package br.com.api.g5.entities;

import java.time.LocalDate; 
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cliente")
	private Integer id;

	@NotNull
	@Column(name = "nome_cliente")
	private String nome;

	@Column(name = "telefone_fixo_cliente")
	private String telefoneFixo;

	@Column(name = "celular_cliente")
	private String celular;

	@NotNull
	@Column(name = "nome_usuario_cliente")
	private String nomeUsuario;

	@NotNull
	@Size(max = 11)
	@Column(name = "cpf_cliente")
	private String cpf;

	@NotNull
	@Column(name = "email_cliente")
	private String email;

	@NotNull
	@Column(name = "data_nascimento_cliente")
	private LocalDate dataNascimento;

	@NotNull
	@Size(max=10)
	@Column(name = "password_cliente")
	private String password;

	@Column(name = "ativo_cliente")
	private Boolean ativo = true;
	
	@OneToOne
	@JoinColumn(name="endereco_id")
	private Endereco endereco;

	@OneToMany
	@JoinColumn(name="pedido_id")
	private List<Pedido> pedido;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Cliente() {
		super();
	}

	public Cliente(Integer id, @NotNull String nome, String telefoneFixo, String celular, @NotNull String nomeUsuario,
			@NotNull @Size(max = 11) String cpf, @NotNull String email, @NotNull LocalDate dataNascimento,
			@NotNull @Size(max = 10) String password, Boolean ativo, Endereco endereco, List<Pedido> pedido,
			User user) {
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
		this.pedido = pedido;
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

	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", telefoneFixo=" + telefoneFixo + ", celular=" + celular
				+ ", nomeUsuario=" + nomeUsuario + ", cpf=" + cpf + ", email=" + email + ", dataNascimento="
				+ dataNascimento + ", password=" + password + ", ativo=" + ativo + ", endereco=" + endereco
				+ ", pedido=" + pedido + ", user=" + user + "]";
	}

}
