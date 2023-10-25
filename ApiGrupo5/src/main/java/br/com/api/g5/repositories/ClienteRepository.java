package br.com.api.g5.repositories;

import java.util.Optional; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.g5.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	Optional<Cliente> findByEmail(String email);
	
}
