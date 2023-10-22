package br.com.api.g5.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g5.entities.Pedido;
import br.com.api.g5.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	private EmailService emailService;
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

	//GET Id
	public Pedido buscarPorId(Integer id) {
		return pedidoRepository.findById(id).get();
	}

	//GET Listar
	public List<Pedido> listarTodos() {
		return pedidoRepository.findAll();
	}
	
	//POST
	public Pedido salvar(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	//DELETE
	public void remover(Integer id) {
		//NAO FOI TESTADO
		emailService.envioEmailCancelamentoPedido();
		pedidoRepository.deleteById(id);
	}
	
	//PUT
	public Pedido atualizar(Integer id, Pedido pedido) {
		Pedido registroAntigo = buscarPorId(id);

		if (pedido.getProdutos() != null) {
			registroAntigo.setProdutos(pedido.getProdutos());
		}
		registroAntigo.setId(id);
		return pedidoRepository.save(registroAntigo);
	}

}