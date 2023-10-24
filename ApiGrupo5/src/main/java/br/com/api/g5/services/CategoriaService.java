package br.com.api.g5.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g5.dto.CategoriaDTO;
import br.com.api.g5.entities.Categoria;
import br.com.api.g5.mappers.Conversores;
import br.com.api.g5.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	Conversores conversores;
	
	//GET Id
	public CategoriaDTO buscarPorId(Integer id) {
		CategoriaDTO infoCategoria = new CategoriaDTO();
		Categoria categoria = categoriaRepository.findById(id).get();
		infoCategoria = conversores.converterCategoriaDTO(categoria);
		return infoCategoria;
	}
	
	public Categoria buscarPorNome(CategoriaDTO nome) {
		return categoriaRepository.findByNome(nome.getNome()).get();
	}

	//GET Listar
	public List<CategoriaDTO> listarTodos() {
		List<CategoriaDTO> infoCategorias = new ArrayList<>();
		List<Categoria> categorias = categoriaRepository.findAll();
		for(Categoria categoria : categorias) {
			infoCategorias.add(conversores.converterCategoriaDTO(categoria));
		}
		return infoCategorias;
	}

	//POST
	public CategoriaDTO salvar(CategoriaDTO categoriaDTO) {
		Categoria salvarCategoria = new Categoria();
		salvarCategoria.setNome(categoriaDTO.getNome());
		salvarCategoria.setDescricao(categoriaDTO.getDescricao());
		categoriaRepository.save(salvarCategoria);
		CategoriaDTO categoriaConvertida = conversores.converterCategoriaDTO(salvarCategoria);
		
		return categoriaConvertida;
	}

	//DELETE
	public void remover(Integer id) {
		categoriaRepository.deleteById(id);
	}
	
	//PUT
	public CategoriaDTO atualizar(Integer id, CategoriaDTO categoriaDTO) {
						
		Categoria registroAntigo = categoriaRepository.findById(id).get();

		if (categoriaDTO.getDescricao() != null) {
			registroAntigo.setDescricao(categoriaDTO.getDescricao());
		}
		if (categoriaDTO.getNome() != null) {
			registroAntigo.setNome(categoriaDTO.getNome());
		}
		CategoriaDTO categoriaConvertida = conversores.converterCategoriaDTO(registroAntigo);
		registroAntigo.setId(id);
		categoriaRepository.save(registroAntigo);
		return categoriaConvertida;
	}
}


