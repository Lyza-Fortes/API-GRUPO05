package br.com.api.g5.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g5.entities.Produto;
import br.com.api.g5.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	//GET id
	public Produto buscarPorId(Integer id) {
		return produtoRepository.findById(id).get();
	}
	
	//Get Listar
	public List<Produto> listarTodos(){
		return produtoRepository.findAll();
	}
	
	//POST
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	//DELETE LÓGICO
	public void removerLogico(Integer id) {
		Produto produto = buscarPorId(id);
		
		if (produto != null) {
			produto.setAtivo(false);
			produtoRepository.save(produto);
		}
	}
}

