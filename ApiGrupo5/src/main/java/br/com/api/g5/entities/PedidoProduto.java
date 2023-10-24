package br.com.api.g5.entities;

import javax.persistence.Embeddable;

@Embeddable
public class PedidoProduto {

	private Double valorTotal;
	private Integer itemQuantidade;

	public PedidoProduto(Double valorTotal, Integer itemQuantidade) {
		this.valorTotal = valorTotal;
		this.itemQuantidade = itemQuantidade;
	}
	public PedidoProduto() {
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Integer getItemQuantidade() {
		return itemQuantidade;
	}
	public void setItemQuantidade(Integer itemQuantidade) {
		this.itemQuantidade = itemQuantidade;
	}
	
	
	
	
	
}
