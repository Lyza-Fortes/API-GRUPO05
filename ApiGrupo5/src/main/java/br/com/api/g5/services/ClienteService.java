package br.com.api.g5.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g5.config.PasswordEncoder;
import br.com.api.g5.dto.ClienteAtualizarDTO;
import br.com.api.g5.dto.ClienteDTO;
import br.com.api.g5.entities.Cliente;
import br.com.api.g5.entities.Endereco;
import br.com.api.g5.entities.User;
import br.com.api.g5.mappers.Conversores;
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
	
	@Autowired
	Conversores conversores;
	
	private EmailService emailService;
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

	// GET Id
	public ClienteDTO buscarPorId(Integer id) {
		ClienteDTO infoCliente = new ClienteDTO();
		Cliente cliente = clienteRepository.findById(id).get();
		infoCliente = conversores.converterClienteDTO(cliente);
		return infoCliente;
	}

	// GET Listar
	public List<ClienteDTO> listarTodos() {
		List<ClienteDTO> infoClientes = new ArrayList<>();
		List<Cliente> clientes = clienteRepository.findAll();
		for (Cliente cliente : clientes) {
			infoClientes.add(conversores.converterClienteDTO(cliente));
		}
		return infoClientes;
	}

	// POST
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	// PUT
	public ClienteAtualizarDTO atualizar(Integer id, ClienteAtualizarDTO clienteDTO) {

		Cliente registroAntigo = clienteRepository.findById(id).get();

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
		if (clienteDTO.getPassword() != null) {
            User user = userService.findByEmail(registroAntigo.getEmail());
            String senhaCriptografada = PasswordEncoder.encodePassword(clienteDTO.getPassword());
            registroAntigo.setPassword(senhaCriptografada);
            user.setPassword(senhaCriptografada);
            userService.save(user);
            emailService.envioEmailTrocaSenha(user);
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
		ClienteAtualizarDTO clienteConvertido = conversores.converterClienteAtualizarDTO(registroAntigo);
		registroAntigo.setId(id);
		clienteRepository.save(registroAntigo);
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
