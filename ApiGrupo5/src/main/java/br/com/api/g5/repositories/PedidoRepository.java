package br.com.api.g5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.g5.entities.Pedido;
import br.com.api.g5.entities.PedidoProduto;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
	
	@Query(value = "select*from pedido_produto where produto_id = :idProduto and pedido_id = :idPedido", nativeQuery = true)
	PedidoProduto findProdPed(Integer idProduto, Integer idPedido);

}
