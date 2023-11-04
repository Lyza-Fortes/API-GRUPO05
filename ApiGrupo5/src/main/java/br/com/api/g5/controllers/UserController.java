package br.com.api.g5.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g5.config.JWTUtil;
import br.com.api.g5.dto.LoginDTO;
import br.com.api.g5.dto.UserDTO;
import br.com.api.g5.entities.Cliente;
import br.com.api.g5.entities.Endereco;
import br.com.api.g5.entities.Funcionario;
import br.com.api.g5.entities.Role;
import br.com.api.g5.entities.User;
import br.com.api.g5.enums.TipoRoleEnum;
import br.com.api.g5.repositories.EnderecoRepository;
import br.com.api.g5.repositories.RoleRepository;
import br.com.api.g5.services.ClienteService;
import br.com.api.g5.services.EmailService;
import br.com.api.g5.services.EnderecoService;
import br.com.api.g5.services.FuncionarioService;
import br.com.api.g5.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	private EmailService emailService;
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

	@Autowired
	UserService userService;

	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@Autowired
	EnderecoService enderecoService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/registro")
	public ResponseEntity<String> cadastro(@RequestParam String email, @Valid @RequestBody UserDTO user) {

		Set<String> strRoles = user.getRoles();
		Set<Role> roles = new HashSet<>();

		Endereco viaCep = enderecoService.pesquisarEndereco(user.getCep());
		Endereco enderecoNovo = new Endereco();
		enderecoNovo.setBairro(viaCep.getBairro());
		enderecoNovo.setCep(user.getCep());
		enderecoNovo.setComplemento(user.getComplementoAdicional());
		enderecoNovo.setLocalidade(viaCep.getLocalidade());
		enderecoNovo.setLogradouro(viaCep.getLogradouro());
		enderecoNovo.setNumero(user.getNumero());
		enderecoNovo.setUf(viaCep.getUf());
		enderecoRepository.save(enderecoNovo);
		List<Endereco> enderecos = new ArrayList<>();
		enderecos.add(enderecoNovo);
<<<<<<< HEAD

		User usuarioResumido = new User();
		usuarioResumido.setNomeUsuario(user.getNomeUsuario());
		usuarioResumido.setEmail(user.getEmail());
		usuarioResumido.setRoles(roles);
		String encodedPass = passwordEncoder.encode(user.getPassword());
		usuarioResumido.setPassword(encodedPass);
		userService.save(usuarioResumido);
=======
>>>>>>> 20a4a3bae75d584705639469b1e8ba119a5ec7de
		
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(TipoRoleEnum.ROLE_CLIENTE)
					.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "FUNCIONARIO":
					Role adminRole = roleRepository.findByName(TipoRoleEnum.ROLE_FUNCIONARIO)
							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
					roles.add(adminRole);
					Funcionario funcionario = new Funcionario();
					User usuarioResumidoFun = new User();
					String encodedPassFun = passwordEncoder.encode(user.getPassword());
					usuarioResumidoFun.setNomeUsuario(user.getNomeUsuario());
					usuarioResumidoFun.setEmail(user.getEmail());
					usuarioResumidoFun.setRoles(roles);
					usuarioResumidoFun.setPassword(encodedPassFun);
					userService.save(usuarioResumidoFun);
					funcionario.setNome(user.getNome());
					funcionario.setCelular(user.getCelular());
					funcionario.setTelefoneFixo(user.getTelefoneFixo());
					funcionario.setNomeUsuario(user.getNomeUsuario()); 
					funcionario.setPassword(encodedPassFun);
					funcionario.setCpf(user.getCpf());
					funcionario.setDataNascimento(user.getDataNascimento());
					funcionario.setEmail(user.getEmail());
					funcionario.setEndereco(enderecoNovo);
					funcionario.setUser(usuarioResumidoFun);
					funcionarioService.salvar(funcionario);
					break;
				case "CLIENTE":
					Role userRole = roleRepository.findByName(TipoRoleEnum.ROLE_CLIENTE)
							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
					roles.add(userRole);
					Cliente cliente = new Cliente();
					User usuarioResumidoCli = new User();
					String encodedPassCli = passwordEncoder.encode(user.getPassword());
					usuarioResumidoCli.setNomeUsuario(user.getNomeUsuario());
					usuarioResumidoCli.setEmail(user.getEmail());
					usuarioResumidoCli.setRoles(roles);
					usuarioResumidoCli.setPassword(encodedPassCli);
					userService.save(usuarioResumidoCli);
					cliente.setNome(user.getNome());
					cliente.setTelefoneFixo(user.getTelefoneFixo());
					cliente.setCelular(user.getCelular());
					cliente.setNomeUsuario(user.getNomeUsuario());
					cliente.setPassword(encodedPassCli);
					cliente.setCpf(user.getCpf());
					cliente.setDataNascimento(user.getDataNascimento());
					cliente.setEmail(user.getEmail());
					cliente.setEndereco(enderecoNovo);
					cliente.setUser(usuarioResumidoCli);
					clienteService.salvar(cliente);
				}
			});
		}

		emailService.envioEmailCadastro(user);
		
		
		
		//return ResponseEntity.ok(new MessageResponseDTO("Cadastro efetuado com sucesso!"));
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro efetuado com sucesso!");
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginDTO body) {
		try {
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
					body.getEmail(), body.getPassword());

			authManager.authenticate(authInputToken);

			User user = userService.findByEmail(body.getEmail());
			User usuarioResumido = new User();
			usuarioResumido.setNomeUsuario(user.getNomeUsuario());
			usuarioResumido.setEmail(user.getEmail());
			usuarioResumido.setIdUser(user.getIdUser());
			usuarioResumido.setRoles(user.getRoles());
			String token = jwtUtil.generateTokenWithUserData(usuarioResumido);

			//MessageResponseDTO retornoLogin = new MessageResponseDTO("Login efetuado com sucesso! Por favor copie o seu Token: Bearer "
				//	+ token);
			//return ResponseEntity.ok().body(retornoLogin);
			
			return ResponseEntity.status(HttpStatus.OK).body("Login efetuado com sucesso!\n\nToken:"+token);
		} catch (AuthenticationException authExc) {
			throw new RuntimeException("Credenciais Invalidas");
		}
	}

//	@DeleteMapping("/remover/{id}")
//	public void remover(@PathVariable Integer id) {
//		userService.remover(id);
//	}

}
