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

@Component /* Significa que essa classe é um bean gerenciado pelo Spring e pode ser injetado em outras partes da aplicação. */
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepo; /* Injeta o repositório de usuário */

	@Autowired
	UserService userService; /* Injeta o serviço de usuário */

	@Autowired
	RoleRepository roleRepo; /* Injeta o repositório de roles de usuário */
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		/* Procura um usuário no repositório com base no email */
		Optional<User> userRes = userRepo.findByEmail(email);
		/* O uso do Optional indica que pode ou não haver um usuário correspondente ao email fornecido. Se não houver um usuário com esse email, o Optional estará vazio, e um UsernameNotFoundException será lançado para indicar que o usuário não foi encontrado. Se um usuário for encontrado, o Optional conterá esse usuário.*/
		if (userRes.isEmpty()) {
			throw new UsernameNotFoundException("Não foi possível encontrar usuário com o email = " + email);
		}

		/* Cria um objeto UserDetails usando informações do usuário encontrado. */
		return new org.springframework.security.core.userdetails.User(
			email, userRes.get().getPassword(), /* Senha do usuário. */
			roleRepo.roles(email) /* Recupera os roles (autorizações) do usuário a partir do repositório de roles. */
				.stream().map(role -> new SimpleGrantedAuthority(role.getName().name())) /* Mapeia os roles para objetos SimpleGrantedAuthority que podem ser utilizados para construir o perfil de autorização de um usuário. */
				.collect(Collectors.toList()) /* Coleta as autorizações mapeadas. */
		); // Define de forma estática, o perfil do usuário encontrado.
	}
}
