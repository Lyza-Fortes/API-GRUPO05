package br.com.api.g5.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private Integer id;

	@NotNull
	@Column(name = "data_pedido")
	private LocalDateTime dataPedido;
	
	@ManyToMany
	@JoinTable(
			name="pedido_produto",
			joinColumns=@JoinColumn(name="pedido_id"),
			inverseJoinColumns=@JoinColumn(name="produto_id")
			)
	private List<Produto> produtos;

	public Pedido() {
		super();
	}

	public Pedido(Integer id, @NotNull LocalDateTime dataPedido) {
		super();
		this.id = id;
		this.dataPedido = dataPedido;
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
