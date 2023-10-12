package br.com.api.g5.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g5.entities.Pedido;
import br.com.api.g5.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	//GET Id
	public Pedido buscarPorId(Integer id) {
		return pedidoRepository.findById(id).get();
	}

	//GET Listar
	public List<Pedido> listarTodos() {
		return pedidoRepository.findAll();
	}
	
	//POST
	public Pedido salvar(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	//DELETE
	public void remover(Integer id) {
		pedidoRepository.deleteById(id);
	}
	
/*
	public void removerLogico(Integer id) {
		Categoria categoria = buscarPorId(id);

		if (categoria != null) {
			categoria.setAtivo(false);
			pedidoRepository.save(categoria);
		}
	}
*/
}