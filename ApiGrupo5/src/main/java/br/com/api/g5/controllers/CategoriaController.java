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

import br.com.api.g5.dto.CategoriaDTO;
import br.com.api.g5.services.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    
	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping("/buscar/{id}")
	public CategoriaDTO buscarPorId(@PathVariable Integer id) {
		return categoriaService.buscarPorId(id);
	}

	@GetMapping("/listar")
	public List<CategoriaDTO> listarTodos() {
		return categoriaService.listarTodos();
	}
	
	@PostMapping("/salvar")
	public CategoriaDTO salvar(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		return categoriaService.salvar(categoriaDTO);

	}
	
	@PutMapping("/atualizar/{id}")
	public CategoriaDTO atualizar(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO categoriaDTO) {
		return categoriaService.atualizar(id, categoriaDTO);
	}
	
	@DeleteMapping("/remover/{id}")
	public void remover(@PathVariable Integer id) {
		categoriaService.remover(id);
	}

}
