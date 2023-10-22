package br.com.api.g5.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g5.dto.FuncionarioAtualizarDTO;
import br.com.api.g5.dto.FuncionarioDTO;
import br.com.api.g5.entities.Endereco;
import br.com.api.g5.entities.Funcionario;
import br.com.api.g5.entities.User;
import br.com.api.g5.repositories.EnderecoRepository;
import br.com.api.g5.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	FuncionarioRepository funcionarioRepository;

	@Autowired
	EnderecoService enderecoService;

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	UserService userService;

	// GET Id
	public FuncionarioDTO buscarPorId(Integer id) {
		FuncionarioDTO infoFuncionario = new FuncionarioDTO();
		Funcionario funcionario = funcionarioRepository.findById(id).get();
		infoFuncionario = converterFuncionarioDTO(funcionario);
		return infoFuncionario;
	}

	// GET Listar
	public List<FuncionarioDTO> listarTodos() {
		List<FuncionarioDTO> infoFuncionarios = new ArrayList<>();
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		for (Funcionario funcionario : funcionarios) {
			infoFuncionarios.add(converterFuncionarioDTO(funcionario));
		}
		return infoFuncionarios;
	}

	// Conversão DTO
	public FuncionarioDTO converterFuncionarioDTO(Funcionario funcionario) {
		FuncionarioDTO funcionarioConvertido = new FuncionarioDTO();
		funcionarioConvertido.setNome(funcionario.getNome());
		funcionarioConvertido.setTelefoneFixo(funcionario.getTelefoneFixo());
		funcionarioConvertido.setCelular(funcionario.getCelular());
		funcionarioConvertido.setNomeUsuario(funcionario.getNomeUsuario());
		funcionarioConvertido.setCpf(funcionario.getCpf());
		funcionarioConvertido.setEmail(funcionario.getEmail());
		funcionarioConvertido.setDataNascimento(funcionario.getDataNascimento());
		funcionarioConvertido.setPassword(funcionario.getPassword());
		funcionarioConvertido.setBairro(funcionario.getEndereco().getBairro());
		funcionarioConvertido.setCep(funcionario.getEndereco().getCep());
		funcionarioConvertido.setComplemento(funcionario.getEndereco().getComplemento());
		funcionarioConvertido.setLocalidade(funcionario.getEndereco().getLocalidade());
		funcionarioConvertido.setLogradouro(funcionario.getEndereco().getLogradouro());
		funcionarioConvertido.setNumero(funcionario.getEndereco().getNumero());
		funcionarioConvertido.setUf(funcionario.getEndereco().getUf());
		return funcionarioConvertido;
	}

	// POST
	public Funcionario salvar(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

	// PUT
	public FuncionarioAtualizarDTO atualizar(Integer id, FuncionarioAtualizarDTO funcionarioDTO) {

		Funcionario registroAntigo = funcionarioRepository.findById(id).get();

		if (funcionarioDTO.getPassword() != null) {
			registroAntigo.setPassword(funcionarioDTO.getPassword());
		}
		if (funcionarioDTO.getNome() != null) {
			registroAntigo.setNome(funcionarioDTO.getNome());
		}
		if (funcionarioDTO.getTelefoneFixo() != null) {
			registroAntigo.setTelefoneFixo(funcionarioDTO.getTelefoneFixo());
		}
		if (funcionarioDTO.getCelular() != null) {
			registroAntigo.setCelular(funcionarioDTO.getCelular());
		}
		if (funcionarioDTO.getEmail() != null) {
			User user = userService.findByEmail(registroAntigo.getEmail());
			user.setEmail(funcionarioDTO.getEmail());
			registroAntigo.setEmail(funcionarioDTO.getEmail());
			userService.save(user);
		}
		if (funcionarioDTO.getCep() != null) {
			Endereco viaCep = enderecoService.pesquisarEndereco(funcionarioDTO.getCep());
			Endereco enderecoNovo = new Endereco();
			enderecoNovo.setBairro(viaCep.getBairro());
			enderecoNovo.setCep(funcionarioDTO.getCep());
			enderecoNovo.setComplemento(funcionarioDTO.getComplemento());
			enderecoNovo.setLocalidade(viaCep.getLocalidade());
			enderecoNovo.setLogradouro(viaCep.getLogradouro());
			enderecoNovo.setNumero(funcionarioDTO.getNumero());
			enderecoNovo.setUf(viaCep.getUf());
			enderecoRepository.save(enderecoNovo);
			registroAntigo.setEndereco(enderecoNovo);
		}
		FuncionarioAtualizarDTO funcionarioConvertido = converterFuncionarioAtualizarDTO(registroAntigo);
		registroAntigo.setId(id);
		funcionarioRepository.save(registroAntigo);
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

	// DELETE
	public void removerLogico(Integer id) {
		Funcionario funcionario = funcionarioRepository.findById(id).get();

		if (funcionario != null) {
			funcionario.setAtivo(false);
			funcionarioRepository.save(funcionario);
		}
	}

	// Ativar Lógico
	public void ativarLogico(Integer id) {
		Funcionario funcionario = funcionarioRepository.findById(id).get();

		if (funcionario != null) {
			funcionario.setAtivo(true);
			funcionarioRepository.save(funcionario);
		}
	}
}