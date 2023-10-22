package br.com.api.g5.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.api.g5.entities.Pedido;


public class PedidoDTO {
	
	private LocalDateTime dataPedido;
	private List<ProdutoDTO> produtosDTO;
	private Map<Pedido, Integer> itemQuantidade;
	
	public PedidoDTO(LocalDateTime dataPedido, List<ProdutoDTO> produtosDTO, Map<Pedido, Integer> itemQuantidade) {
		super();
		this.dataPedido = dataPedido;
		this.produtosDTO = produtosDTO;
		this.itemQuantidade = itemQuantidade;
	}

	public PedidoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

	public List<ProdutoDTO> getProdutosDTO() {
		return produtosDTO;
	}

	public void setProdutosDTO(List<ProdutoDTO> produtosDTO) {
		this.produtosDTO = produtosDTO;
	}

	public Map<Pedido, Integer> getItemQuantidade() {
		return itemQuantidade;
	}

	public void setItemQuantidade(Map<Pedido, Integer> itemQuantidade) {
		this.itemQuantidade = itemQuantidade;
	}
		
}
