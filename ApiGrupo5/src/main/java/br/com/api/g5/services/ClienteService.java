package br.com.api.g5.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g5.dto.ClienteDTO;
import br.com.api.g5.entities.Cliente;
import br.com.api.g5.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	//GET Id
	public ClienteDTO buscarPorId(Integer id) {
		ClienteDTO infoCliente = new ClienteDTO();
		Cliente cliente = clienteRepository.findById(id).get();
		infoCliente = converterClienteDTO(cliente);
		return infoCliente;
	}

	//GET Listar
	public List<ClienteDTO> listarTodos() {
		List<ClienteDTO> infoClientes = new ArrayList<>();
		List<Cliente> clientes = clienteRepository.findAll();
		for(Cliente cliente : clientes) {
			infoClientes.add(converterClienteDTO(cliente));
		}
		return infoClientes;
	}
	
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

	//POST
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	//PUT
	public Cliente atualizar(Integer id, Cliente cliente) {
		Cliente registroAntigo = clienteRepository.findById(id).get();

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
		if (cliente.getPassword() != null) {
			registroAntigo.setPassword(cliente.getPassword());
		}
		
		registroAntigo.setId(id);
		return clienteRepository.save(registroAntigo);
	}

	public void removerLogico(Integer id) {
		Cliente cliente = clienteRepository.findById(id).get();

		if (cliente != null) {
			cliente.setAtivo(false);
			clienteRepository.save(cliente);
		}
	}	
}
