package br.com.api.g5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.g5.entities.Email;

public interface EmailRepository extends JpaRepository<Email, Integer> {
    
}
