package br.com.api.g5.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g5.dto.PedidoDTO;
import br.com.api.g5.dto.PedidoResponseDTO;
import br.com.api.g5.entities.Pedido;
import br.com.api.g5.services.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping("/buscar/{id}")
	public PedidoDTO buscarPorId(@PathVariable Integer id) {
		return pedidoService.buscarPorId(id);
	}

	@GetMapping("/listar")
	public List<PedidoResponseDTO> listarTodos() {
		return pedidoService.listarTodos();
	}
	
	@PostMapping("/salvar")
	public PedidoDTO salvar(@Valid @RequestBody PedidoDTO pedidoDTO) {
		return pedidoService.salvar(pedidoDTO);
	}
	
//	@PutMapping("/atualizar/{id}")
//	public Pedido atualizar(@PathVariable Integer id, @Valid @RequestBody Pedido pedido) {
//		return pedidoService.atualizar(id, pedido);
//	}
//	
	@DeleteMapping("/remover/{id}")
	public void remover(@PathVariable Integer id) {
		pedidoService.remover(id);
	}
}
