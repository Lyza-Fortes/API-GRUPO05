package br.com.api.g5.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g5.entities.Cliente;
import br.com.api.g5.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	//GET Id
	public Cliente buscarPorId(Integer id) {
		return clienteRepository.findById(id).get();
	}

	//GET Listar
	public List<Cliente> listarTodos() {
		return clienteRepository.findAll();
	}

	//POST
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	//PUT
	public Cliente atualizar(Integer id, Cliente cliente) {
		Cliente registroAntigo = buscarPorId(id);

		if (cliente.getNomeUsuario() != null) {
			registroAntigo.setNomeUsuario(cliente.getNomeUsuario());
		}
		if (cliente.getEmail() != null) {
			registroAntigo.setEmail(cliente.getEmail());
		}
		if (cliente.getNome() != null) {
			registroAntigo.setNome(cliente.getNome());
		}
		if (cliente.getTelefoneFixo() != null) {
			registroAntigo.setTelefoneFixo(cliente.getTelefoneFixo());
		}
		if (cliente.getCelular() != null) {
			registroAntigo.setCelular(cliente.getCelular());
		}
		if (cliente.getSenha() != null) {
			registroAntigo.setSenha(cliente.getSenha());
		}
		registroAntigo.setId(id);
		return clienteRepository.save(registroAntigo);
	}

	public void removerLogico(Integer id) {
		Cliente cliente = buscarPorId(id);

		if (cliente != null) {
			cliente.setAtivo(false);
			clienteRepository.save(cliente);
		}
	}	
}
