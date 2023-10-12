package br.com.api.g5.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "produto")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Integer id;

	@NotNull
	@Column(name = "nome_produto")
	private String nome;

	@NotNull
	@Column(name = "descricao_produto")
	private String descricao;

	@NotNull
	@Column(name = "data_fab")
	private LocalDate dataFab;

	@Column(name = "qtd_estoque")
	private Integer qtdEstoque;

	@NotNull
	@Column(name = "valor_unit")
	private Double valorUnit;

	public Produto() {
		super();
	}

	public Produto(Integer id, @NotNull String nome, @NotNull String descricao, @NotNull LocalDate dataFab,
			Integer qtdEstoque, @NotNull Double valorUnit) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.dataFab = dataFab;
		this.qtdEstoque = qtdEstoque;
		this.valorUnit = valorUnit;
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

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", dataFab=" + dataFab
				+ ", qtdEstoque=" + qtdEstoque + ", valorUnit=" + valorUnit + "]";
	}

}
