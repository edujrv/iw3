package org.magm.backend.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.magm.backend.auth.IUserBusiness;
import org.magm.backend.auth.User;
import org.magm.backend.integration.cli2.model.FacturaCli2;
import org.magm.backend.integration.cli2.model.FacturaCli2SlimV1JsonSerializer;
import org.magm.backend.model.AuditSerializer;
import org.magm.backend.model.Auditoria;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.IAuditoriaBusiness;
import org.magm.backend.util.IStandartResponseBusiness;
import org.magm.backend.util.JsonUtiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//La clase AuthorizationRestController es un controlador Spring MVC que maneja las solicitudes relacionadas con la autorización y la seguridad en la aplicación
/*
* Anotaciones del Controlador:

@RestController: Indica que la clase es un controlador de Spring y que cada método en la clase devuelve directamente un objeto serializado en lugar de una vista.
@RequestMapping(Constants.URL_AUTHORIZATION): Define la URL base para todas las solicitudes manejadas por este controlador.
Método onlyAdmin:

@PreAuthorize("hasRole('ROLE_ADMIN')"): Especifica que solo los usuarios con el rol "ROLE_ADMIN" pueden acceder a este método.
@GetMapping("/admin"): Maneja las solicitudes GET a la URL "/admin" y devuelve un mensaje con el código de estado OK.
Método auditoria:

@PreAuthorize("hasRole('ROLE_VIEW')"): Permite el acceso solo a los usuarios con el rol "ROLE_VIEW".
@GetMapping("/auditoria"): Maneja las solicitudes GET a la URL "/auditoria" y devuelve una lista de auditorías en formato JSON. Usa un objeto AuditSerializer para personalizar la serialización de las auditorías.
Método onlyUser:

@PreAuthorize("hasRole('ROLE_USER')"): Permite el acceso solo a los usuarios con el rol "ROLE_USER".
@GetMapping("/user"): Maneja las solicitudes GET a la URL "/user" y devuelve un mensaje con el código de estado OK.
Método rolUserOArdmin:

@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')"): Permite el acceso a usuarios con el rol "ROLE_USER" o "ROLE_ADMIN".
@GetMapping("/user-or-admin"): Maneja las solicitudes GET a la URL "/user-or-admin" y devuelve un mensaje con el código de estado OK.
Método myRols:

@PreAuthorize("#username == authentication.principal.username"): Permite el acceso solo si el parámetro "username" coincide con el nombre de usuario autenticado.
@GetMapping("/my-rols"): Maneja las solicitudes GET a la URL "/my-rols" y devuelve los roles del usuario autenticado.
Método variable:

@GetMapping("/variable"): Maneja las solicitudes GET a la URL "/variable" y devuelve un mensaje basado en si el usuario autenticado tiene el rol "ROLE_ADMIN".
Método fullData:

@PostAuthorize("returnObject.username == #username"): Permite el acceso solo si el nombre de usuario devuelto por el método coincide con el parámetro "username".
@GetMapping("/full-data"): Maneja las solicitudes GET a la URL "/full-data" y devuelve los datos completos del usuario autenticado.
Método selfFilter:

@PostFilter("filterObject != authentication.principal.username"): Filtra la lista de usuarios para excluir al usuario autenticado.
@GetMapping("/self-filter"): Maneja las solicitudes GET a la URL "/self-filter" y devuelve una lista filtrada de nombres de usuario.
* */
//Este controlador utiliza anotaciones de seguridad de Spring, como @PreAuthorize y @PostAuthorize, para controlar el acceso a diferentes recursos y personalizar la lógica de autorización. También interactúa con servicios de negocio y utiliza anotaciones como @Autowired para inyectar dependencias
@RestController
@RequestMapping(Constants.URL_AUTHORIZATION)
public class AuthorizationRestController extends BaseRestController {

	@Autowired
	private IAuditoriaBusiness auditoriaBusiness;

	@Autowired
	private IStandartResponseBusiness response;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin")
	public ResponseEntity<String> onlyAdmin() {
		return new ResponseEntity<String>("Servicio admin", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_VIEW')")
	@GetMapping("/auditoria")
	public ResponseEntity<?> auditoria(){
		try {
			StdSerializer<Auditoria> ser = null;

			ser = new AuditSerializer(Auditoria.class, false);

			String result = JsonUtiles.getObjectMapper(Auditoria.class, ser, null)
					.writeValueAsString(auditoriaBusiness.list(getUserLogged().getUsername()));

			return new ResponseEntity<>(result, HttpStatus.OK);
//			return new ResponseEntity<>(auditoriaBusiness.list(getUserLogged().getUsername()), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/user")
	public ResponseEntity<String> onlyUser() {
		return new ResponseEntity<String>("Servicio user", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/user-or-admin")
	public ResponseEntity<String> rolUserOArdmin() {
		return new ResponseEntity<String>("Servicio user or admin", HttpStatus.OK);
	}

	////Se compara con los datos de entrada
	@PreAuthorize("#username == authentication.principal.username")
	@GetMapping("/my-rols")
	public ResponseEntity<String> myRols(@RequestParam("username") String username) {
		return new ResponseEntity<String>(getUserLogged().getAuthorities().toString(), HttpStatus.OK);
	}

	@GetMapping("/variable")
	public ResponseEntity<String> variable(HttpServletRequest request) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			return new ResponseEntity<String>("Tenés rol admin", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No tenés rol admin", HttpStatus.OK);
		}
	}

	//Se compara con los datos de respuesta
	@PostAuthorize("returnObject.username == #username")
	@GetMapping("/full-data")
	public User fullData(@RequestParam("username") String username) {
		return getUserLogged();
	}

	@Autowired
	private IUserBusiness userBusiness;

	//El user actual no figura en la lista
	@PostFilter("filterObject != authentication.principal.username")
	@GetMapping("/self-filter")
	public List<String> selfFilter() {
		List<String> r = null;
		try {
			r = userBusiness.list().stream().map(u -> u.getUsername()).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return r;
	}

}

