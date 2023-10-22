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

import br.com.api.g5.entities.Categoria;
import br.com.api.g5.services.CategoriaService;
import br.com.api.g5.services.EmailService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    
	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping("/buscar/{id}")
	public Categoria buscarPorId(@PathVariable Integer id) {
		return categoriaService.buscarPorId(id);
	}

	@GetMapping("/listar")
	public List<Categoria> listarTodos() {
		return categoriaService.listarTodos();
	}
	
	@PostMapping("/salvar")
	public Categoria salvar(@RequestBody Categoria categoria) {
		return categoriaService.salvar(categoria);

	}
	
	@PutMapping("/atualizar/{id}")
	public Categoria atualizar(@PathVariable Integer id, @RequestBody Categoria categoria) {
		return categoriaService.atualizar(id, categoria);
	}
	
	@DeleteMapping("/remover/{id}")
	public void remover(@PathVariable Integer id) {
		categoriaService.remover(id);
	}

}
