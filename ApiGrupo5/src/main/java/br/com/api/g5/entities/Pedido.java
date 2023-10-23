package br.com.api.g5.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private Integer id;

	@NotNull(message="Campo data de pedido não pode ser nulo")
	@Column(name = "data_pedido")
	private LocalDateTime dataPedido;

	@ManyToMany
	@JoinTable(
			name="pedido_produto",
			joinColumns=@JoinColumn(name="pedido_id"),
			inverseJoinColumns=@JoinColumn(name="produto_id")
			)
	private List<Produto> produtos;

	@ElementCollection
	@CollectionTable(
	name = "pedido_produto",
	joinColumns = @JoinColumn(name = "pedido_id")
	)
	@MapKeyJoinColumn(name = "produto_id")
	@NotNull
	@Column(name = "valorTotal")
	private List<Double> valorTotal;

	@ElementCollection
	@CollectionTable(
	name = "pedido_produto",
	joinColumns = @JoinColumn(name = "produto_id")
	)
	@MapKeyJoinColumn(name = "pedido_id")
	@NotNull(message="É necessário definir uma quantidade para comprar.")
	@Column(name = "quantidade")
	private List<Integer> itemQuantidade;

	public List<Integer> getItemQuantidade() {
		return itemQuantidade;
	}

	public void setItemQuantidade(List<Integer> itemQuantidade) {
		this.itemQuantidade = itemQuantidade;
	}

	public List<Double> getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(List<Double> valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Pedido() {
		super();
	}

	public Pedido(Integer id, @NotNull(message = "Campo data de pedido não pode ser nulo") LocalDateTime dataPedido,
			List<Produto> produtos) {
		this.id = id;
		this.dataPedido = dataPedido;
		this.produtos = produtos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", dataPedido=" + dataPedido + ", produtos=" + produtos + "]";
	}
}