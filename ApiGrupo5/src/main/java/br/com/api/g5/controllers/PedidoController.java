package br.com.api.g5.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g5.dto.PedidoDTO;
import br.com.api.g5.services.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;
	
//	@GetMapping("/buscar/{id}")
//	public PedidoDTO buscarPorId(@PathVariable Integer id) {
//		return pedidoService.buscarPorId(id);
//	}

//	@GetMapping("/listar")
//	public List<PedidoResponseDTO> listarTodos() {
//		return pedidoService.listarTodos();
//	}
	
	@PostMapping("/salvar/{idCliente}")
	public ResponseEntity<?> salvar(@PathVariable Integer idCliente, @Valid @RequestBody PedidoDTO pedidoDTO) {
		return pedidoService.salvar(idCliente, pedidoDTO);
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
