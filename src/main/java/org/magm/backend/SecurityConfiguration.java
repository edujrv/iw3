package org.magm.backend;

import org.magm.backend.auth.IUserBusiness;
import org.magm.backend.auth.custom.CustomAuthenticationManager;
import org.magm.backend.auth.filters.JWTAuthorizationFilter;
import org.magm.backend.controllers.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//Este archivo SecurityConfiguration es una clase de configuración de Spring Security que define cómo se asegura la aplicación.
//En resumen, esta clase configura la seguridad de la aplicación, permitiendo el acceso a ciertas rutas sin autenticación, asegurando todas las demás rutas y utilizando un filtro personalizado para la autorización basada en JWT. También configura CORS para permitir solicitudes desde cualquier origen.
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private IUserBusiness userBusiness;


	@Bean
	public AuthenticationManager authenticationManager() {
		return new CustomAuthenticationManager(bCryptPasswordEncoder(), userBusiness);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*").allowedHeaders("*").allowedOrigins("*");
			}
		};
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.authorizeRequests().antMatchers(HttpMethod.POST, Constants.URL_LOGIN).permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilter(new JWTAuthorizationFilter(authenticationManager()))
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();

	}
}