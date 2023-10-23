package br.com.api.g5.dto;

import java.time.LocalDate;

public class ProdutoAtualizarDTO {

	private String nome;
	private String descricao;
	private LocalDate dataFab;
	private Integer qtdEstoque;
	private Double valorUnit;
	private CategoriaDTO categoriaDTO;
	private FuncionarioDTO funcionarioDTO;

    public ProdutoAtualizarDTO(String nome, String descricao, LocalDate dataFab, Integer qtdEstoque, Double valorUnit,
			CategoriaDTO categoriaDTO, FuncionarioDTO funcionarioDTO) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.dataFab = dataFab;
		this.qtdEstoque = qtdEstoque;
		this.valorUnit = valorUnit;
		this.categoriaDTO = categoriaDTO;
		this.funcionarioDTO = funcionarioDTO;
	}

	public ProdutoAtualizarDTO() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public FuncionarioDTO getFuncionarioDTO() {
		return funcionarioDTO;
	}

	public void setFuncionarioDTO(FuncionarioDTO funcionarioDTO) {
		this.funcionarioDTO = funcionarioDTO;
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

	public CategoriaDTO getCategoriaDTO() {
		return categoriaDTO;
	}

	public void setCategoriaDTO(CategoriaDTO categoriaDTO) {
		this.categoriaDTO = categoriaDTO;
	}

}
