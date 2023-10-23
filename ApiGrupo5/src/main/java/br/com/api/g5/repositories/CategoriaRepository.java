package br.com.api.g5.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.g5.entities.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	Optional<Categoria> findByNome(String nome);
	
}


	
