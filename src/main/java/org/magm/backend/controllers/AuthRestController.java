package org.magm.backend.controllers;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.magm.backend.auth.User;
import org.magm.backend.auth.UserSerializer;
import org.magm.backend.auth.custom.CustomAuthenticationManager;
import org.magm.backend.auth.filters.AuthConstants;
import org.magm.backend.integration.cli2.model.FacturaCli2;
import org.magm.backend.integration.cli2.model.FacturaCli2SlimV1JsonSerializer;
import org.magm.backend.util.JsonUtiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
/*
* La clase AuthRestController es un controlador Spring MVC que maneja las solicitudes relacionadas con la autenticación y generación de tokens JWT. A continuación, se describe la funcionalidad principal de este controlador:

Anotaciones del Controlador:

@RestController: Indica que la clase es un controlador de Spring y que cada método en la clase devuelve directamente un objeto serializado en lugar de una vista.
Método loginExternalOnlyToken:

@PostMapping(value = Constants.URL_LOGIN, produces = MediaType.TEXT_PLAIN_VALUE): Maneja las solicitudes POST a la URL definida en la constante Constants.URL_LOGIN y produce una respuesta en formato de texto plano.
@RequestParam: Anotación para mapear parámetros de solicitud a los parámetros del método.
value = "username": Nombre del parámetro de solicitud correspondiente al nombre de usuario.
value = "password": Nombre del parámetro de solicitud correspondiente a la contraseña.
value = "json": Nombre del parámetro de solicitud para indicar si se desea una respuesta en formato JSON.
El método realiza la autenticación del usuario utilizando un AuthenticationManager personalizado (CustomAuthenticationManager).
Si la autenticación es exitosa, se genera un token JWT.
La respuesta puede ser un token JWT o los detalles del usuario en formato JSON, según el valor del parámetro "json".
En caso de errores, se manejan excepciones y se devuelve la respuesta adecuada.
Generación del Token JWT:

Se utiliza la biblioteca Auth0 JWT para generar el token.
El token incluye información como el nombre de usuario, el ID interno del usuario, los roles, el correo electrónico y la versión.
El token tiene un tiempo de expiración definido por AuthConstants.EXPIRATION_TIME.
Se firma el token con el algoritmo HMAC512 y la clave secreta definida en AuthConstants.SECRET.
En resumen, este controlador maneja las solicitudes de inicio de sesión, autentica al usuario y genera un token JWT como respuesta. También ofrece la opción de devolver detalles del usuario en formato JSON si se especifica en los parámetros de solicitud.
* */
@RestController
public class AuthRestController extends BaseRestController {

	@Autowired
	private AuthenticationManager authManager;

	@PostMapping(value = Constants.URL_LOGIN, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> loginExternalOnlyToken(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "json", required = false, defaultValue = "false") String json) throws JsonProcessingException {
		Authentication auth = null;
		try {
			auth = authManager.authenticate(((CustomAuthenticationManager) authManager).AuthWrap(username, password));
		} catch (AuthenticationServiceException e0) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (AuthenticationException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		User user = (User) auth.getPrincipal();

		if(json.equals("true")){
			StdSerializer<User> ser = new UserSerializer(User.class,false);

			String result = JsonUtiles.getObjectMapper(User.class, ser, null)
					.writeValueAsString(user);

			return new ResponseEntity<String>(result, HttpStatus.OK);

		}else{
			String token = JWT.create().withSubject(user.getUsername())
					.withClaim("internalId", user.getIdUser())
					.withClaim("roles", new ArrayList<String>(user.getAuthoritiesStr()))
					.withClaim("email", user.getEmail())
					.withClaim("version", "1.0.0")
					.withExpiresAt(new Date(System.currentTimeMillis() + AuthConstants.EXPIRATION_TIME))
					.sign(Algorithm.HMAC512(AuthConstants.SECRET.getBytes()));
			return new ResponseEntity<String>(token, HttpStatus.OK);
		}

	}
}

