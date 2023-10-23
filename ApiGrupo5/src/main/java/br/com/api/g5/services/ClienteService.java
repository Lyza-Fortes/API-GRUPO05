package br.com.api.g5.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g5.dto.ClienteAtualizarDTO;
import br.com.api.g5.dto.ClienteDTO;
import br.com.api.g5.entities.Cliente;
import br.com.api.g5.entities.Endereco;
import br.com.api.g5.entities.User;
import br.com.api.g5.repositories.ClienteRepository;
import br.com.api.g5.repositories.EnderecoRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoService enderecoService;

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	UserService userService;

	// GET Id
	public ClienteDTO buscarPorId(Integer id) {
		ClienteDTO infoCliente = new ClienteDTO();
		Cliente cliente = clienteRepository.findById(id).get();
		infoCliente = converterClienteDTO(cliente);
		return infoCliente;
	}

	// GET Listar
	public List<ClienteDTO> listarTodos() {
		List<ClienteDTO> infoClientes = new ArrayList<>();
		List<Cliente> clientes = clienteRepository.findAll();
		for (Cliente cliente : clientes) {
			infoClientes.add(converterClienteDTO(cliente));
		}
		return infoClientes;
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

	// POST
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	// PUT
	public ClienteAtualizarDTO atualizar(Integer id, ClienteAtualizarDTO clienteDTO) {

		Cliente registroAntigo = clienteRepository.findById(id).get();

		if (clienteDTO.getPassword() != null) {
			registroAntigo.setPassword(clienteDTO.getPassword());
		}
		if (clienteDTO.getNome() != null) {
			registroAntigo.setNome(clienteDTO.getNome());
		}
		if (clienteDTO.getTelefoneFixo() != null) {
			registroAntigo.setTelefoneFixo(clienteDTO.getTelefoneFixo());
		}
		if (clienteDTO.getCelular() != null) {
			registroAntigo.setCelular(clienteDTO.getCelular());
		}
		if (clienteDTO.getEmail() != null) {
			User user = userService.findByEmail(registroAntigo.getEmail());
			user.setEmail(clienteDTO.getEmail());
			registroAntigo.setEmail(clienteDTO.getEmail());
			userService.save(user);
		}
		if (clienteDTO.getCep() != null) {
			Endereco viaCep = enderecoService.pesquisarEndereco(clienteDTO.getCep());
			Endereco enderecoNovo = new Endereco();
			enderecoNovo.setBairro(viaCep.getBairro());
			enderecoNovo.setCep(clienteDTO.getCep());
			enderecoNovo.setComplemento(clienteDTO.getComplemento());
			enderecoNovo.setLocalidade(viaCep.getLocalidade());
			enderecoNovo.setLogradouro(viaCep.getLogradouro());
			enderecoNovo.setNumero(clienteDTO.getNumero());
			enderecoNovo.setUf(viaCep.getUf());
			enderecoRepository.save(enderecoNovo);
			registroAntigo.setEndereco(enderecoNovo);
		}
		ClienteAtualizarDTO clienteConvertido = converterClienteAtualizarDTO(registroAntigo);
		registroAntigo.setId(id);
		clienteRepository.save(registroAntigo);
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

	// Deletar Lógico
	public void removerLogico(Integer id) {
		Cliente cliente = clienteRepository.findById(id).get();

		if (cliente != null) {
			cliente.setAtivo(false);
			clienteRepository.save(cliente);
		}
	}

	// Ativar Lógico
	public void ativarLogico(Integer id) {
		Cliente cliente = clienteRepository.findById(id).get();

		if (cliente != null) {
			cliente.setAtivo(true);
			clienteRepository.save(cliente);
		}
	}
}
