package com.dawes.crudsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dawes.services.ContenedorService;

@Configuration
@EnableWebSecurity
public class AppSeguridad extends WebSecurityConfigurerAdapter {
	
	@Autowired
	ContenedorService cs;
	
	
	// Metodo encrptación
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	
	public String encript(String password) {
		return passwordEncoder().encode(password);
	}
	
	
	//Programar Autenticación
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("usuario").password(encript("temporal")).roles("ADMIN");
	}
	
	

	// Programo la autorización
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/usuario/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/registrador/**").hasAnyRole("USER", "ADMIN");
		http.formLogin().loginPage("/login");
		http.exceptionHandling().accessDeniedPage("/403");
		http.logout().logoutSuccessUrl("/home");
		http.csrf().disable();
	}
	
	

}
