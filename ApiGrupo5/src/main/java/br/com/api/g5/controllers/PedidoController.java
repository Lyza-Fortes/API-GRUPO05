package br.com.api.g5.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g5.entities.Pedido;
import br.com.api.g5.services.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping("/buscar/{id}")
	public Pedido buscarPorId(@PathVariable Integer id) {
		return pedidoService.buscarPorId(id);
	}

	@GetMapping("/listar")
	public List<Pedido> listarTodos() {
		return pedidoService.listarTodos();
	}
	
	@PostMapping("/salvar")
	public Pedido salvar(@RequestBody Pedido pedido) {
		return pedidoService.salvar(pedido);

	}
	
//	@PutMapping("/atualizar/{id}")
//	public Pedido atualizar(@PathVariable Integer id, @RequestBody Pedido pedido) {
//		return pedidoService.atualizar(id, pedido);
//	}
//	
	@DeleteMapping("/remover/{id}")
	public void remover(@PathVariable Integer id) {
		pedidoService.remover(id);
	}
}
