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

import br.com.api.g5.entities.Funcionario;
import br.com.api.g5.services.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	@Autowired
	FuncionarioService funcionarioService;
	
	@GetMapping("/buscar/{id}")
	public Funcionario buscarPorId(@PathVariable Integer id) {
		return funcionarioService.buscarPorId(id);
	}

	@GetMapping("/listar")
	public List<Funcionario> listarTodos() {
		return funcionarioService.listarTodos();
	}
	
	@PostMapping("/salvar")
	public Funcionario salvar(@RequestBody Funcionario funcionario) {
		return funcionarioService.salvar(funcionario);

	}
	
	@PutMapping("/atualizar/{id}")
	public Funcionario atualizar(@PathVariable Integer id, @RequestBody Funcionario funcionario) {
		return funcionarioService.atualizar(id, funcionario);
	}
	
	@DeleteMapping("/remover/{id}")
	public void removerLogico(@PathVariable Integer id) {
		funcionarioService.removerLogico(id);
	}

}
