package br.com.api.g5.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g5.dto.PedidoDTO;
import br.com.api.g5.dto.ProdutoAtualizarDTO;
import br.com.api.g5.dto.ProdutoDTO;
import br.com.api.g5.entities.Pedido;
import br.com.api.g5.entities.Produto;
import br.com.api.g5.mappers.Conversores;
import br.com.api.g5.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	CategoriaService categoriaService;

	@Autowired
	FuncionarioService funcionarioService;

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	Conversores conversores;

	// GET id
	public ProdutoDTO buscarPorId(Integer id) {
		ProdutoDTO infoProduto = new ProdutoDTO();
		Produto produto = produtoRepository.findById(id).get();
		infoProduto = conversores.converterProdutoDTO(produto);
		return infoProduto;
	}

	public List<Produto> buscarPorIdLista(Integer id) {
		List<Produto>listaProdutos = new ArrayList<>();
		for (Produto p : listaProdutos) {
			listaProdutos.add(produtoRepository.findById(id).get());
		}
		return listaProdutos;
	}

	public List<Double> buscarValorPorId(ProdutoDTO produtoDTO, PedidoDTO pedidoDTO) {
		Double n = produtoDTO.getValorUnit() * pedidoDTO.getItemQuantidade().get(0);
		List<Double> i = new ArrayList();
		i.add(n);
		return i;
	}

	// GET Listar
	public List<ProdutoDTO> listarTodos() {
		List<ProdutoDTO> infoProdutos = new ArrayList<>();
		List<Produto> produtos = produtoRepository.findAll();
		for (Produto produto : produtos) {
			infoProdutos.add(conversores.converterProdutoDTO(produto));
		}
		return infoProdutos;
	}

	//método passa o pedido como parâmetro para depois retornar o id de produto
	public Integer buscarIdPorObjeto(Pedido pedido) {
		Produto p = produtoRepository.findById(pedido.getId()).get();

		return p.getId();
	}

	// POST
	public ProdutoDTO salvar(ProdutoDTO produtoDTO) {
		Produto salvarProduto = new Produto();
		salvarProduto.setNome(produtoDTO.getNome());
		salvarProduto.setDescricao(produtoDTO.getDescricao());
		salvarProduto.setDataFab(produtoDTO.getDataFab());
		salvarProduto.setQtdEstoque(produtoDTO.getQtdEstoque());
		salvarProduto.setValorUnit(produtoDTO.getValorUnit());
		salvarProduto.setCategoria(categoriaService.buscarPorNome(produtoDTO.getCategoriaDTO()));
		salvarProduto.setFuncionario(funcionarioService.buscarPorNome(produtoDTO.getFuncionarioResponseDTO()));

		ProdutoDTO categoriaConvertida = conversores.converterProdutoDTO(salvarProduto);
		produtoRepository.save(salvarProduto);

		return categoriaConvertida;
	}

	// PUT
	public ProdutoAtualizarDTO atualizar(Integer id, ProdutoAtualizarDTO produtoAtualizarDTO) {
		Produto registroAntigo = produtoRepository.findById(id).get();

		if (produtoAtualizarDTO.getDescricao() != null) {
			registroAntigo.setDescricao(produtoAtualizarDTO.getDescricao());
		}
		if (produtoAtualizarDTO.getQtdEstoque() != null) {
			registroAntigo.setQtdEstoque(produtoAtualizarDTO.getQtdEstoque());
		}
		if (produtoAtualizarDTO.getValorUnit() != null) {
			registroAntigo.setValorUnit(produtoAtualizarDTO.getValorUnit());
		}
		if (produtoAtualizarDTO.getNome() != null) {
			registroAntigo.setNome(produtoAtualizarDTO.getNome());
		}
//		if (produtoAtualizarDTO.getFuncionarioDTO() != null) {
//			registroAntigo.setFuncionario(produtoAtualizarDTO.getFuncionarioDTO());
//		}
//		if (produtoAtualizarDTO.getCategoriaDTO() != null) {
//			registroAntigo.setCategoria(produtoAtualizarDTO.getCategoriaDTO());
//		}

		ProdutoAtualizarDTO produtoConvertido = converterProdutoAtualizarDTO(registroAntigo);
		registroAntigo.setId(id);
		produtoRepository.save(registroAntigo);
		return produtoConvertido;
	}

	public ProdutoAtualizarDTO converterProdutoAtualizarDTO(Produto produto) {
		ProdutoAtualizarDTO produtoConvertido = new ProdutoAtualizarDTO();
		produtoConvertido.setNome(produto.getNome());
		produtoConvertido.setDescricao(produto.getDescricao());
		produtoConvertido.setQtdEstoque(produto.getQtdEstoque());
		produtoConvertido.setValorUnit(produto.getValorUnit());
		produtoConvertido.setFuncionarioDTO(funcionarioService.buscarPorId(produto.getFuncionario().getId()));
		produtoConvertido.setCategoriaDTO(categoriaService.buscarPorId(produto.getCategoria().getId()));
		return produtoConvertido;
	}

	// DELETE LÓGICO
	public void removerLogico(Integer id) {
		Produto produto = produtoRepository.findById(id).get();

		if (produto != null) {
			produto.setAtivo(false);
			produtoRepository.save(produto);
		}
	}

	//tipo inteiro para pegarmos já o número
	// public Integer buscarPorProduto(Produto produto) {
	// 	ProdutoDTO infoProduto = new ProdutoDTO();
	// 	Produto produto = produtoRepository.findAll(produto);
	// 	infoProduto = converterProdutoDTO(produto);
	// 	return infoProduto;
	// }
}
