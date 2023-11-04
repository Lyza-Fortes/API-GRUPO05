package br.com.api.g5.entities;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Integer id;

	@NotNull(message = "Campo nome do produto não pode ser nulo")
	@Column(name = "nome_produto")
	private String nome;

	@NotNull(message = "Campo descrição do produto não pode ser nulo")
	@Column(name = "descricao_produto")
	private String descricao;

	@NotNull(message = "Campo data de fabricação não pode ser nulo")
	@Column(name = "data_fab")
	private LocalDate dataFab;

	@Column(name = "qtd_estoque")
	private Integer qtdEstoque;

	@NotNull(message = "Campo valor unitário não pode ser nulo")
	@Column(name = "valor_unit")
	private Double valorUnit;

	@Column(name = "ativo_produto")
	private Boolean ativo = true;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;
	
	@ElementCollection
	@CollectionTable(name = "pedido_produto", joinColumns = @JoinColumn(name = "produto_id"))
	@MapKeyJoinColumn(name = "pedido_id")
	private Map<Pedido, PedidoProduto> itemQuantidade = new HashMap<>();

	// @ElementCollection
	// @CollectionTable(
	// name = "pedido_produto",
	// joinColumns = @JoinColumn(name = "produto_id")
	// )
	// @MapKeyJoinColumn(name = "pedido_id")
	// @NotNull(message="É necessário definir uma quantidade para comprar.")
	// @Column(name = "quantidade")
	// private List<Integer> itemQuantidade;

	public Produto() {
	}

	public Produto(Integer id, @NotNull(message = "Campo nome do produto não pode ser nulo") String nome,
			@NotNull(message = "Campo descrição do produto não pode ser nulo") String descricao,
			@NotNull(message = "Campo data de fabricação não pode ser nulo") LocalDate dataFab, Integer qtdEstoque,
			@NotNull(message = "Campo valor unitário não pode ser nulo") Double valorUnit, Boolean ativo,
			Categoria categoria, Funcionario funcionario, Map<Pedido, PedidoProduto> itemQuantidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.dataFab = dataFab;
		this.qtdEstoque = qtdEstoque;
		this.valorUnit = valorUnit;
		this.ativo = ativo;
		this.categoria = categoria;
		this.funcionario = funcionario;
		this.itemQuantidade = itemQuantidade;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataFab() {
		return dataFab;
	}

	public void setDataFab(LocalDate dataFab) {
		this.dataFab = dataFab;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public Double getValorUnit() {
		return valorUnit;
	}

	public void setValorUnit(Double valorUnit) {
		this.valorUnit = valorUnit;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Map<Pedido, PedidoProduto> getItemQuantidade() {
		return itemQuantidade;
	}

	public void setItemQuantidade(Map<Pedido, PedidoProduto> itemQuantidade) {
		this.itemQuantidade = itemQuantidade;
	}

	

}
