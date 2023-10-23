package br.com.api.g5.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoDTO {
	
	private Integer idProdutos;
	private List<Integer> itemQuantidade;
	private LocalDateTime dataPedido;
	public LocalDateTime getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}
	public Integer getIdProdutos() {
		return idProdutos;
	}
	public void setIdProdutos(Integer idProdutos) {
		this.idProdutos = idProdutos;
	}
	public List<Integer> getItemQuantidade() {
		return itemQuantidade;
	}
	public void setItemQuantidade(List<Integer> itemQuantidade) {
		this.itemQuantidade = itemQuantidade;
	}
	public PedidoDTO() {
	}
    public PedidoDTO(Integer idProdutos, List<Integer> itemQuantidade) {
        this.idProdutos = idProdutos;
        this.itemQuantidade = itemQuantidade;
    }
}