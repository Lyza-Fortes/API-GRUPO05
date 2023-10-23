package br.com.api.g5.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g5.dto.PedidoDTO;
import br.com.api.g5.dto.PedidoResponseDTO;
import br.com.api.g5.dto.ProdutoDTO;
import br.com.api.g5.entities.Pedido;
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

	private EmailService emailService;
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

	//GET Id
	public PedidoDTO buscarPorId(Integer id) {
		PedidoDTO infoPedido = new PedidoDTO();
		Pedido pedido = pedidoRepository.findById(id).get();
		infoPedido = conversores.converterPedidoDTO(pedido);
		return infoPedido;
	}

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
	public PedidoDTO salvar(PedidoDTO pedidoDTO) {
		Pedido salvarPedido = new Pedido();
		salvarPedido.setItemQuantidade(pedidoDTO.getItemQuantidade());
		salvarPedido.setProdutos(produtoService.buscarPorIdLista(pedidoDTO.getIdProdutos()));
		salvarPedido.setDataPedido(pedidoDTO.getDataPedido());
		ProdutoDTO produtoDTO = produtoService.buscarPorId(pedidoDTO.getIdProdutos());
		salvarPedido.setValorTotal(produtoService.buscarValorPorId(produtoDTO, pedidoDTO));
		
		PedidoDTO pedidoConvertido = conversores.converterPedidoDTO(salvarPedido);
		pedidoRepository.save(salvarPedido);

		return pedidoConvertido;
	}

	//DELETE
	public void remover(Integer id) {
		pedidoRepository.deleteById(id);
	}
	
	//PUT
	public PedidoDTO atualizar(Integer id, PedidoDTO pedidoDTO) {
						
		Pedido registroAntigo = pedidoRepository.findById(id).get();

		PedidoDTO pedidoConvertido = conversores.converterPedidoDTO(registroAntigo);
		registroAntigo.setId(id);
		pedidoRepository.save(registroAntigo);
		return pedidoConvertido;
	}
}