package br.com.api.g5.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g5.dto.ClienteDTO;
import br.com.api.g5.entities.Cliente;
import br.com.api.g5.services.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	
	@ApiOperation(value="Retorna um cliente", notes="Cliente")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna um cliente"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	@GetMapping("/buscar/{id}")
	public ClienteDTO buscarPorId(@PathVariable Integer id) {
		return clienteService.buscarPorId(id);
	}

	@GetMapping("/listar")
	public List<ClienteDTO> listarTodos() {
		return clienteService.listarTodos();
	}
	
//  @PostMapping("/salvar")
//	public Cliente salvar(@RequestBody Cliente cliente) {
//		return clienteService.salvar(cliente);
//	}
	
	@PutMapping("/atualizar/{id}")
	public Cliente atualizar(@PathVariable Integer id, @RequestBody Cliente cliente) {
		return clienteService.atualizar(id, cliente);
	}
	
	@DeleteMapping("/remover/{id}")
	public void removerLogico(@PathVariable Integer id) {
		clienteService.removerLogico(id);
	}
}
