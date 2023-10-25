package br.com.api.g5.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.api.g5.repositories.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
    UserRepository userRepo;

    @Autowired
    JWTFilter filter;

    @Autowired
    UserDetailsServiceImpl uds;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
		        .cors()
		        .and()
        		.csrf().disable()
                .httpBasic().disable()
                .authorizeHttpRequests()
                .antMatchers("/cliente/atualizar","/funcionario/buscar/{id}", "/funcionario/listar", "/funcionario/atualizar", "/funcionario/remover/{id}", "/cliente/listar", "/categoria/salvar", "/categoria/atualizar", "/categoria/remover", "/pedido/listar", "/produto/salvar", "/produto/atualizar", "/produto/remover/{id}", "/categoria/listar","/categoria/buscar/{id}", "/produto/listar", "/produto/buscar/{id}", "/user/registro", "/user/login","/cliente/buscar/{id}", "/cliente/remover/{id}", "/pedido/buscar/{id}", "/pedido/salvar", "/pedido/remover").permitAll()
                .antMatchers("/cliente/atualizar").hasRole("CLIENTE")
                .antMatchers("/funcionario/buscar/{id}", "/funcionario/listar", "/funcionario/atualizar", "/funcionario/remover/{id}", "/cliente/listar", "/categoria/salvar", "/categoria/atualizar", "/categoria/remover", "/pedido/listar", "/produto/salvar", "/produto/atualizar", "/produto/remover/{id}").hasRole("FUNCIONARIO")
                .antMatchers("/cliente/buscar/{id}", "/cliente/remover/{id}", "/pedido/buscar/{id}", "/pedido/salvar", "/pedido/remover").hasAnyRole("CLIENTE", "FUNCIONARIO")
                .and()
                .userDetailsService(uds)
                .exceptionHandling()
                    .authenticationEntryPoint(
                            (request, response, authException) ->
                                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                    )
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
	
//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//		.authorizeHttpRequests()
//		.anyRequest().permitAll()
//		.and()
//		.csrf(csrf -> csrf.disable());
//		return http.build();
//	}
}