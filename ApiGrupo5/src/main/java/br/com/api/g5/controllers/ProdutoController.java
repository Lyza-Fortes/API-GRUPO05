package br.com.api.g5.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g5.dto.ProdutoAtualizarDTO;
import br.com.api.g5.dto.ProdutoDTO;
import br.com.api.g5.services.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
	ProdutoService produtoService;
	
	@GetMapping("/buscar/{id}")
	public ProdutoDTO buscarPorId(@PathVariable Integer id) {
		return produtoService.buscarPorId(id);
	}

	@GetMapping("/listar")
	public List<ProdutoDTO> listarTodos() {
		return produtoService.listarTodos();
	}
	
	@PostMapping("/salvar")
	public ProdutoDTO salvar(@Valid @RequestBody ProdutoDTO produtoDTO) {
		return produtoService.salvar(produtoDTO);

	}
	
	@PutMapping("/atualizar/{id}")
	public ProdutoAtualizarDTO atualizar(@PathVariable Integer id, @Valid @RequestBody ProdutoAtualizarDTO produtoDTO) {
		return produtoService.atualizar(id, produtoDTO);
	}
	
	@DeleteMapping("/remover/{id}")
	public void removerLogico(@PathVariable Integer id) {
		produtoService.removerLogico(id);
	}


}
