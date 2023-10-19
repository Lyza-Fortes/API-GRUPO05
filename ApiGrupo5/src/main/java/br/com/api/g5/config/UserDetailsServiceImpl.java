package br.com.api.g5.config;

import java.util.Optional;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.api.g5.entities.User;
import br.com.api.g5.repositories.RoleRepository;
import br.com.api.g5.repositories.UserRepository;
import br.com.api.g5.services.UserService;


/*Essa classe é responsável por carregar os detalhes do usuário a partir de um repositório (como um banco de dados) e fornecer esses detalhes ao Spring Security para permitir o login e a autorização do usuário. */ 

@Component /* Significa que essa classe é um bean gerenciado pelo Spring e pode ser injetado em outras partes da aplicação. */
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepo; /* Injeta o repositório de usuário */

	@Autowired
	UserService userService; /* Injeta o serviço de usuário */

	@Autowired
	RoleRepository roleRepo; /* Injeta o repositório de roles de usuário */
	
	
	@Override
	/* Este método é usado para carregar os detalhes do usuário com base no nome de usuário (neste caso, o email). */
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		/* Procura um usuário no repositório com base no email */
		Optional<User> userRes = userRepo.findByEmail(email);
		/* O uso do Optional indica que pode ou não haver um usuário correspondente ao email fornecido. Se não houver um usuário com esse email, o Optional estará vazio, e um UsernameNotFoundException será lançado para indicar que o usuário não foi encontrado. Se um usuário for encontrado, o Optional conterá esse usuário.*/
		if (userRes.isEmpty()) {
			throw new UsernameNotFoundException("Não foi possível encontrar usuário com o email = " + email);
		}

		/* Cria um objeto UserDetails usando informações do usuário encontrado. */
		return new org.springframework.security.core.userdetails.User(
				email, /* nome de usuário (email)*/
				userRes.get().getPassword(), /* Senha do usuário. */
				roleRepo.roles(email) /* Chamando um método roles no repositório RoleRepository para recuperar as autorizacoes (roles) associadas a um usuário com base no email. */
				.stream()/* Converte a coleção de autorizacoes obtida a partir do repositório em um fluxo */.map(role -> new SimpleGrantedAuthority(role.getName().name())) /* Mapeando cada autorizacao para um objeto SimpleGrantedAuthority, isso é necessário porque o Spring Security espera que as autorizacoes do usuário sejam objetos GrantedAuthority.*/
				.collect(Collectors.toList()) /* Coleta as autorizações mapeadas anteriormente para uma lista, está lista é o terceiro argumento passado no construtor User */
				); // essa linha de codigo (47 - 53) está criando uma instância de User para representar um usuário autenticado com seu email, senha e as suas autorizações.
	}
}
