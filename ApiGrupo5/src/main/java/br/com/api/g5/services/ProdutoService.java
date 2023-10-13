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

	// GET id
	public Produto buscarPorId(Integer id) {
		return produtoRepository.findById(id).get();
	}

	// Get Listar
	public List<Produto> listarTodos() {
		return produtoRepository.findAll();
	}

	// POST
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	// PUT
	public Produto atualizar(Integer id, Produto produto) {
		Produto registroAntigo = buscarPorId(id);

		if (produto.getDescricao() != null) {
			registroAntigo.setDescricao(produto.getDescricao());
		}
		if (produto.getQtdEstoque() != null) {
			registroAntigo.setQtdEstoque(produto.getQtdEstoque());
		}
		if (produto.getValorUnit() != null) {
			registroAntigo.setValorUnit(produto.getValorUnit());
		}
		if (produto.getNome() != null) {
			registroAntigo.setNome(produto.getNome());
		}
		registroAntigo.setId(id);
		return produtoRepository.save(registroAntigo);
	}

	// DELETE LÓGICO
	public void removerLogico(Integer id) {
		Produto produto = buscarPorId(id);

		if (produto != null) {
			produto.setAtivo(false);
			produtoRepository.save(produto);
		}
	}
}
