package br.com.api.g5.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g5.entities.Funcionario;
import br.com.api.g5.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	FuncionarioRepository funcionarioRepository;

	//GET Id
	public Funcionario buscarPorId(Integer id) {
		return funcionarioRepository.findById(id).get();
	}

	//GET Listar
	public List<Funcionario> listarTodos() {
		return funcionarioRepository.findAll();
	}
	
	//POST
	public Funcionario salvar(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

	//PUT
	public Funcionario atualizar(Integer id, Funcionario funcionario) {
		Funcionario registroAntigo = buscarPorId(id);

		if (funcionario.getSenha() != null) {
			registroAntigo.setSenha(funcionario.getSenha());
		}
		if (funcionario.getNome() != null) {
			registroAntigo.setNome(funcionario.getNome());
		}
		registroAntigo.setId(id);
		return funcionarioRepository.save(registroAntigo);
	}

	//DELETE
	public void removerLogico(Integer id) {
		Funcionario funcionario = buscarPorId(id);

		if (funcionario != null) {
			funcionario.setAtivo(false);
			funcionarioRepository.save(funcionario);
		}
	}	
}