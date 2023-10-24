package br.com.api.g5.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoDTO {
	
	private Integer cliente;
	private List<Integer> idProdutos;
	private Integer itemQuantidade;
	private LocalDateTime dataPedido;
	
	public Integer getCliente() {
		return cliente;
	}
	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	public LocalDateTime getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}
	public List<Integer> getIdProdutos() {
		return idProdutos;
	}
	public void setIdProdutos(List<Integer> idProdutos) {
		this.idProdutos = idProdutos;
	}
	public Integer getItemQuantidade() {
		return itemQuantidade;
	}
	public void setItemQuantidade(Integer itemQuantidade) {
		this.itemQuantidade = itemQuantidade;
	}
	public PedidoDTO() {
	}
	public PedidoDTO(Integer cliente, List<Integer> idProdutos, Integer itemQuantidade, LocalDateTime dataPedido) {
		super();
		this.cliente = cliente;
		this.idProdutos = idProdutos;
		this.itemQuantidade = itemQuantidade;
		this.dataPedido = dataPedido;
	}
	
	
}