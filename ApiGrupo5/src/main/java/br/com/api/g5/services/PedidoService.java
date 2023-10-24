package br.com.api.g5.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.g5.dto.PedidoDTO;
import br.com.api.g5.dto.PedidoResponseDTO;
import br.com.api.g5.entities.Pedido;
import br.com.api.g5.entities.PedidoProduto;
import br.com.api.g5.entities.Produto;
import br.com.api.g5.entities.User;
import br.com.api.g5.mappers.Conversores;
import br.com.api.g5.repositories.PedidoRepository;
import br.com.api.g5.repositories.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ProdutoService produtoService;

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	Conversores conversores;
	
	@Autowired
	UserService userService;

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

	//GET Listar
	public List<PedidoResponseDTO> listarTodos() {
		List<PedidoResponseDTO> infoPedidos = new ArrayList<>();
		List<Pedido> pedidos = pedidoRepository.findAll();
		for(Pedido pedido : pedidos) {
			infoPedidos.add(conversores.converterPedidoResponseDTO(pedido));
		}
		return infoPedidos;
	}

	//POST
	public ResponseEntity<?> salvar(PedidoDTO pedidoDTO) {
		Pedido salvarPedido = new Pedido();
		PedidoProduto pedidoProduto = new PedidoProduto();
		//salvarPedido.setItemQuantidade(pedidoDTO.getItemQuantidade());
		salvarPedido.setId(pedidoDTO.getCliente());
		
		Double valor = 0.0;
		List<Produto> produtos = new ArrayList<>();
		for(Integer idProduto : pedidoDTO.getIdProdutos()) {
			Produto produto = produtoRepository.findById(idProduto).get();
			produtos.add(produto);
			valor += pedidoDTO.getItemQuantidade() * produto.getValorUnit();
			pedidoProduto.setItemQuantidade(pedidoDTO.getItemQuantidade());
		}
		
		salvarPedido.setProdutos(produtos);
		salvarPedido.setDataPedido(pedidoDTO.getDataPedido());
		pedidoRepository.save(salvarPedido);
		
		pedidoProduto.setValorTotal(valor);
		User user = userService.findById(pedidoDTO.getCliente());
		emailService.envioEmailConfirmacaoPedido(user, pedidoProduto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Pedido efetuado com sucesso!");
	}

	//DELETE
	public void remover(Integer id) {
		pedidoRepository.deleteById(id);
		emailService.envioEmailCancelamentoPedido();
	}
	
	//PUT
//	public PedidoDTO atualizar(Integer id, PedidoDTO pedidoDTO) {
//						
//		Pedido registroAntigo = pedidoRepository.findById(id).get();
//
//		//PedidoDTO pedidoConvertido = conversores.converterPedidoDTO(registroAntigo);
//		registroAntigo.setId(id);
//		pedidoRepository.save(registroAntigo);
//		return pedidoConvertido;
//	}
}