package org.magm.backend.auth.custom;

import java.util.Collection;

import org.magm.backend.auth.IUserBusiness;
import org.magm.backend.auth.User;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.NotFoundException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
/*
*
La clase CustomAuthenticationManager implementa la interfaz AuthenticationManager de Spring Security para realizar la autenticación personalizada. Aquí está la descripción de la clase:

Constructor:

Parámetros:
pEncoder: Un PasswordEncoder utilizado para verificar la contraseña del usuario.
userBusiness: Una instancia de IUserBusiness utilizada para realizar operaciones relacionadas con los usuarios en el negocio.
Método authenticate:

Descripción: Realiza la autenticación del usuario.
Parámetro:
authentication: La instancia de Authentication que contiene las credenciales del usuario.
Excepciones:
AuthenticationException: Excepción principal para problemas de autenticación.
BadCredentialsException: Se lanza si las credenciales son inválidas.
AccountExpiredException: Se lanza si la cuenta del usuario ha caducado.
CredentialsExpiredException: Se lanza si las credenciales del usuario han caducado.
DisabledException: Se lanza si la cuenta del usuario está deshabilitada.
LockedException: Se lanza si la cuenta del usuario está bloqueada.
Retorno: Una instancia de Authentication si la autenticación es exitosa.
Método AuthWrap:

Descripción: Envuelve las credenciales del usuario en una instancia de Authentication.
Parámetros:
name: El nombre del usuario.
pass: La contraseña del usuario.
Retorno: Una instancia de Authentication con las credenciales proporcionadas.
La clase utiliza el PasswordEncoder para verificar la contraseña del usuario y realiza varias comprobaciones de validación del usuario, lanzando excepciones correspondientes en caso de que la autenticación no sea exitosa. El método AuthWrap es utilizado para envolver las credenciales del usuario en una instancia de Authentication para su posterior procesamiento.

Este enfoque personalizado de autenticación permite adaptar la lógica de autenticación según los requisitos específicos del negocio.
* */
public class CustomAuthenticationManager implements AuthenticationManager {

	public CustomAuthenticationManager(PasswordEncoder pEncoder, IUserBusiness userBusiness) {
		this.pEncoder = pEncoder;
		this.userBusiness = userBusiness;
	}

	private IUserBusiness userBusiness;

	private PasswordEncoder pEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		User user = null;
		try {
			user = userBusiness.load(username);
		} catch (NotFoundException e) {
			throw new BadCredentialsException("No user registered with this details");
		} catch (BusinessException e) {
			e.printStackTrace();
			throw new AuthenticationServiceException(e.getMessage());
		}
		String validation = user.validate();
		if (validation.equals(User.VALIDATION_ACCOUNT_EXPIRED))
			throw new AccountExpiredException(User.VALIDATION_ACCOUNT_EXPIRED);
		if (validation.equals(User.VALIDATION_CREDENTIALS_EXPIRED))
			throw new CredentialsExpiredException(User.VALIDATION_CREDENTIALS_EXPIRED);
		if (validation.equals(User.VALIDATION_DISABLED))
			throw new DisabledException(User.VALIDATION_DISABLED);
		if (validation.equals(User.VALIDATION_LOCKED))
			throw new LockedException(User.VALIDATION_LOCKED);

		if (!pEncoder.matches(password, user.getPassword()))
			throw new BadCredentialsException("Invalid password");

		return new UsernamePasswordAuthenticationToken(user, null);

	}
	public Authentication AuthWrap(String name, String pass) {
		return new Authentication() {
			@Override
			public String getName() {
				return name;
			}
			@Override
			public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
			}
			@Override
			public boolean isAuthenticated() {
				return false;
			}
			@Override
			public Object getPrincipal() {
				return null;
			}
			@Override
			public Object getDetails() {
				return null;
			}
			@Override
			public Object getCredentials() {
				return pass;
			}
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return null;
			}
		};
	}

}

