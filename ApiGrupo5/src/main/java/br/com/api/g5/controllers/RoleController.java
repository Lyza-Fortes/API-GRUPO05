package br.com.api.g5.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g5.entities.Role;
import br.com.api.g5.services.RoleService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	RoleService roleService;

	@PostMapping
	public ResponseEntity<String> save(@Valid @RequestBody Role role) {
		Role newRole = roleService.save(role);
		if (newRole != null)
			return ResponseEntity.status(HttpStatus.CREATED).body("Cargo "+ newRole.getName() + " adicionado!");
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requisição inválida.");
	}
	
}
