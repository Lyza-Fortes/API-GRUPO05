package br.com.api.g5.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g5.entities.Categoria;
import br.com.api.g5.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	//GET Id
	public Categoria buscarPorId(Integer id) {
		return categoriaRepository.findById(id).get();
	}

	//GET Listar
	public List<Categoria> listarTodos() {
		return categoriaRepository.findAll();
	}
	
	//POST
	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	//DELETE
	public void remover(Integer id) {
		categoriaRepository.deleteById(id);
	}
	
	//PUT
	public Categoria atualizar(Integer id, Categoria categoria) {
		Categoria registroAntigo = buscarPorId(id);

		if (categoria.getDescricao() != null) {
			registroAntigo.setDescricao(categoria.getDescricao());
		}

		registroAntigo.setId(id);
		return categoriaRepository.save(registroAntigo);
	}
}


