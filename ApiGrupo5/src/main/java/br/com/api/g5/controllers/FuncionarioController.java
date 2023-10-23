package br.com.api.g5.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g5.dto.FuncionarioAtualizarDTO;
import br.com.api.g5.dto.FuncionarioDTO;
import br.com.api.g5.services.EmailService;
import br.com.api.g5.services.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	
	private EmailService emailService;
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@GetMapping("/buscar/{id}")
	public FuncionarioDTO buscarPorId(@PathVariable Integer id) {
		return funcionarioService.buscarPorId(id);
	}

	@GetMapping("/listar")
	public List<FuncionarioDTO> listarTodos() {
		return funcionarioService.listarTodos();
	}
	
//	@PostMapping("/salvar")
//	public Funcionario salvar(@Valid @RequestBody Funcionario funcionario) {
//		return funcionarioService.salvar(funcionario);
//	}
	
	@PutMapping("/atualizar/{id}")
	public FuncionarioAtualizarDTO atualizar(@PathVariable Integer id, @Valid @RequestBody FuncionarioAtualizarDTO funcionario) {
		return funcionarioService.atualizar(id, funcionario);
	}
	
	@PutMapping("/ativar/{id}")
	public void ativarLogico(@PathVariable Integer id) {
		FuncionarioDTO funcionarioDTO = funcionarioService.buscarPorId(id);
		funcionarioService.ativarLogico(id);
		emailService.envioEmailAtivacaoContaFuncionario(funcionarioDTO);
	}
	
	@DeleteMapping("/remover/{id}")
	public void removerLogico(@PathVariable Integer id) {
		FuncionarioDTO funcionarioDTO = funcionarioService.buscarPorId(id);
		funcionarioService.removerLogico(id);
		emailService.envioEmailDesativacaoContaFuncionario(funcionarioDTO);
	}
	

}
