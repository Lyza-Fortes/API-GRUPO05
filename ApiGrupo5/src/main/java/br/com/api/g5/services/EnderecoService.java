package br.com.api.g5.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g5.entities.Endereco;
import br.com.api.g5.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;

	//GET Id
	public Endereco buscarPorId(Integer id) {
		return enderecoRepository.findById(id).get();
	}

	//GET Listar
	public List<Endereco> listarTodos() {
		return enderecoRepository.findAll();
	}

	//POST
	public Endereco salvar(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	//PUT
	public Endereco atualizar(Integer id, Endereco endereco) {
		Endereco registroAntigo = buscarPorId(id);

		if (endereco.getCep() != null) {
			registroAntigo.setCep(endereco.getCep());
		}
		registroAntigo.setId(id);
		return enderecoRepository.save(registroAntigo);
	}

	//DELETE
	public void remover(Integer id) {
		enderecoRepository.deleteById(id);
	}
}