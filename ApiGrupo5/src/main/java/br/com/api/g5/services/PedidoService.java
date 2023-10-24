package br.com.api.g5.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.g5.dto.PedidoDTO;
import br.com.api.g5.entities.Cliente;
import br.com.api.g5.entities.Pedido;
import br.com.api.g5.entities.PedidoProduto;
import br.com.api.g5.entities.Produto;
import br.com.api.g5.mappers.Conversores;
import br.com.api.g5.repositories.ClienteRepository;
import br.com.api.g5.repositories.PedidoRepository;
import br.com.api.g5.repositories.ProdutoRepository;
import br.com.api.g5.repositories.RoleRepository;
import br.com.api.g5.repositories.UserRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ProdutoService produtoService;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	Conversores conversores;

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ClienteRepository clienteRepository;

	private EmailService emailService;

	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

//	//GET Id
//	public PedidoDTO buscarPorId(Integer id) {
//		PedidoDTO infoPedido = new PedidoDTO();
//		Pedido pedido = pedidoRepository.findById(id).get();
//		infoPedido = conversores.converterPedidoDTO(pedido);
//		return infoPedido;
//	}

//	// GET Listar
//	public List<PedidoResponseDTO> listarTodos() {
//		List<PedidoResponseDTO> infoPedidos = new ArrayList<>();
//		List<Pedido> pedidos = pedidoRepository.findAll();
//		for (Pedido pedido : pedidos) {
//			infoPedidos.add(conversores.converterPedidoResponseDTO(pedido));
//		}
//		return infoPedidos;
//	}

	// POST
	public ResponseEntity<?> salvar(Integer idCliente, PedidoDTO pedidoDTO) throws NullPointerException {
		Pedido salvarPedido = new Pedido();
		PedidoProduto pedidoProduto = new PedidoProduto();
		Cliente cliente = clienteRepository.findById(idCliente).get();

		Double valor = 0.0;
		List<Produto> produtos = new ArrayList<>();
		for (Integer idProduto : pedidoDTO.getIdProdutos()) {
			Produto produto = produtoRepository.findById(idProduto).get();
			valor += pedidoDTO.getItemQuantidade() * produto.getValorUnit();
			pedidoProduto.setValorTotal(valor);
			pedidoProduto.setItemQuantidade(pedidoDTO.getItemQuantidade());
			produto.getItemQuantidade().put(salvarPedido, pedidoProduto);
			produto.setQtdEstoque(produto.getQtdEstoque()-pedidoDTO.getItemQuantidade());
			produtos.add(produto);
		}
		
		salvarPedido.setDataPedido(pedidoDTO.getDataPedido());
		pedidoRepository.save(salvarPedido);
		
		List<Pedido> pedidos = new ArrayList<>();
		pedidos.add(salvarPedido);
		cliente.setPedido(pedidos);
		clienteRepository.save(cliente);
		
		emailService.envioEmailConfirmacaoPedido(cliente, pedidoProduto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Pedido efetuado com sucesso!");
	}

	// DELETE
	public void remover(Integer id) {
		pedidoRepository.deleteById(id);
		emailService.envioEmailCancelamentoPedido();
	}

//	// PUT
//	public PedidoDTO atualizar(Integer id, PedidoDTO pedidoDTO) {
//						
//		Pedido registroAntigo = pedidoRepository.findById(id).get();
//
//		PedidoDTO pedidoConvertido = conversores.converterPedidoDTO(registroAntigo);
//		registroAntigo.setId(id);
//		pedidoRepository.save(registroAntigo);
//		return pedidoConvertido;
//	}
	
}