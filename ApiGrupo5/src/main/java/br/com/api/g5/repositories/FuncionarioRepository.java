package br.com.api.g5.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.g5.entities.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{
	
	Optional<Funcionario> findByNome(String nome);
}
