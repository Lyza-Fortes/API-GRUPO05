package br.com.api.g5.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.api.g5.dto.CategoriaDTO;
import br.com.api.g5.dto.ClienteAtualizarDTO;
import br.com.api.g5.dto.ClienteDTO;
import br.com.api.g5.dto.FuncionarioAtualizarDTO;
import br.com.api.g5.dto.FuncionarioResponseDTO;
import br.com.api.g5.dto.ProdutoDTO;
import br.com.api.g5.entities.Categoria;
import br.com.api.g5.entities.Cliente;
import br.com.api.g5.entities.Funcionario;
import br.com.api.g5.entities.Produto;
import br.com.api.g5.repositories.ProdutoRepository;
import br.com.api.g5.services.CategoriaService;
import br.com.api.g5.services.FuncionarioService;
import br.com.api.g5.services.ProdutoService;


@Component
public class Conversores {

	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	ProdutoService produtoService;
	//Conversão DTO
	public CategoriaDTO converterCategoriaDTO(Categoria categoria) {
		CategoriaDTO categoriaConvertida = new CategoriaDTO();
		categoriaConvertida.setNome(categoria.getNome());
		categoriaConvertida.setDescricao(categoria.getDescricao());
		return categoriaConvertida;
	}

	// Conversão DTO
		public ClienteDTO converterClienteDTO(Cliente cliente) {
			ClienteDTO clienteConvertido = new ClienteDTO();
			clienteConvertido.setNome(cliente.getNome());
			clienteConvertido.setTelefoneFixo(cliente.getTelefoneFixo());
			clienteConvertido.setCelular(cliente.getCelular());
			clienteConvertido.setNomeUsuario(cliente.getNomeUsuario());
			clienteConvertido.setCpf(cliente.getCpf());
			clienteConvertido.setEmail(cliente.getEmail());
			clienteConvertido.setDataNascimento(cliente.getDataNascimento());
			clienteConvertido.setPassword(cliente.getPassword());
			clienteConvertido.setBairro(cliente.getEndereco().getBairro());
			clienteConvertido.setCep(cliente.getEndereco().getCep());
			clienteConvertido.setComplemento(cliente.getEndereco().getComplemento());
			clienteConvertido.setLocalidade(cliente.getEndereco().getLocalidade());
			clienteConvertido.setLogradouro(cliente.getEndereco().getLogradouro());
			clienteConvertido.setNumero(cliente.getEndereco().getNumero());
			clienteConvertido.setUf(cliente.getEndereco().getUf());
			return clienteConvertido;
		}
		
		// Conversão DTO
		public ClienteAtualizarDTO converterClienteAtualizarDTO(Cliente cliente) {
			ClienteAtualizarDTO clienteConvertido = new ClienteAtualizarDTO();
			clienteConvertido.setNome(cliente.getNome());
			clienteConvertido.setTelefoneFixo(cliente.getTelefoneFixo());
			clienteConvertido.setCelular(cliente.getCelular());
			clienteConvertido.setEmail(cliente.getEmail());
			clienteConvertido.setPassword(cliente.getPassword());
			clienteConvertido.setCep(cliente.getEndereco().getCep());
			clienteConvertido.setComplemento(cliente.getEndereco().getComplemento());
			clienteConvertido.setNumero(cliente.getEndereco().getNumero());
			return clienteConvertido;
		}
		
		// Conversão DTO
		public FuncionarioResponseDTO converterFuncionarioResponseDTO(Funcionario funcionario) {
			FuncionarioResponseDTO funcionarioConvertido = new FuncionarioResponseDTO();
			funcionarioConvertido.setNome(funcionario.getNome());
			funcionarioConvertido.setNomeUsuario(funcionario.getNomeUsuario());
			funcionarioConvertido.setEmail(funcionario.getEmail());
			return funcionarioConvertido;
		}
		
		// Conversão DTO
		public FuncionarioAtualizarDTO converterFuncionarioAtualizarDTO(Funcionario funcionario) {
			FuncionarioAtualizarDTO funcionarioConvertido = new FuncionarioAtualizarDTO();
			funcionarioConvertido.setNome(funcionario.getNome());
			funcionarioConvertido.setTelefoneFixo(funcionario.getTelefoneFixo());
			funcionarioConvertido.setCelular(funcionario.getCelular());
			funcionarioConvertido.setEmail(funcionario.getEmail());
			funcionarioConvertido.setPassword(funcionario.getPassword());
			funcionarioConvertido.setCep(funcionario.getEndereco().getCep());
			funcionarioConvertido.setComplemento(funcionario.getEndereco().getComplemento());
			funcionarioConvertido.setNumero(funcionario.getEndereco().getNumero());
			return funcionarioConvertido;
		}
		
		public ProdutoDTO converterProdutoDTO(Produto produto) {
			ProdutoDTO produtoConvertido = new ProdutoDTO();
			produtoConvertido.setNome(produto.getNome());
			produtoConvertido.setDescricao(produto.getDescricao());
			produtoConvertido.setDataFab(produto.getDataFab());
			produtoConvertido.setQtdEstoque(produto.getQtdEstoque());
			produtoConvertido.setValorUnit(produto.getValorUnit());
			produtoConvertido.setCategoriaDTO(categoriaService.buscarPorId(produto.getCategoria().getId()));
			produtoConvertido
					.setFuncionarioResponseDTO(funcionarioService.buscarFuncPorId(produto.getFuncionario().getId()));
			return produtoConvertido;
		}
		
		//Conversão DTO
//		public PedidoDTO converterPedidoDTO(Pedido pedido) {
//		    PedidoDTO pedidoDTOConvertido = new PedidoDTO();
//		    PedidoProduto pedidoProduto = new PedidoProduto();
//		    pedidoDTOConvertido.setDataPedido(pedido.getDataPedido());
//		    
//		    List<Produto> produtos = new ArrayList<>();
//		    
//		    return pedidoDTOConvertido;
//		}
//
//		//Conversão Response DTO
//		public PedidoResponseDTO converterPedidoResponseDTO(Pedido pedido) {
//			PedidoResponseDTO pedidoResponseConvertido = new PedidoResponseDTO();
//			return pedidoResponseConvertido;
//		}

}


