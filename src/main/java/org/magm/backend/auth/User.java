package org.magm.backend.auth;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
* La clase User es una entidad JPA que representa a los usuarios en el sistema. Aquí tienes una descripción de la clase:

Anotaciones de la Clase:

@Entity: Indica que la clase es una entidad JPA y está mapeada a una tabla en la base de datos.
@Table(name = "users"): Especifica el nombre de la tabla en la base de datos.
Atributos:

accountNonExpired, accountNonLocked, credentialsNonExpired, enabled: Representan el estado de la cuenta del usuario, como si la cuenta ha expirado, está bloqueada, si las credenciales han expirado y si la cuenta está habilitada.
VALIDATION_* constantes: Definen códigos de validación para diferentes estados de la cuenta.
email: Representa la dirección de correo electrónico del usuario.
idUser: Identificador único del usuario, generado automáticamente.
username: Nombre de usuario único.
password: Contraseña del usuario.
roles: Relación muchos a muchos con la entidad Role, representa los roles asignados al usuario.
Métodos:

validate(): Realiza la validación del estado de la cuenta y devuelve un código de validación.
isInRole(Role role) y isInRole(String role): Verifican si el usuario tiene un rol específico.
getAuthorities(): Implementación de UserDetails, devuelve los roles del usuario como objetos GrantedAuthority.
getAuthoritiesStr(): Devuelve los nombres de los roles del usuario como una lista de cadenas.
Implementación de UserDetails:

La clase implementa la interfaz UserDetails, que es parte de Spring Security y proporciona información necesaria sobre el usuario durante la autenticación.
Relación con la Tabla de Roles:

La relación con la tabla de roles se establece a través de la tabla de unión userroles.
Anotación @Getter y @Setter:

Estas anotaciones de Lombok generan automáticamente los métodos getter y setter para los atributos de la clase.
En resumen, la clase User encapsula la información del usuario y su relación con los roles. Además, implementa la interfaz UserDetails para integrarse con Spring Security.
* */
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

	private static final long serialVersionUID = 1181473083412294679L;

	@Column(columnDefinition = "tinyint default 0")
	private boolean accountNonExpired = true;

	@Column(columnDefinition = "tinyint default 0")
	private boolean accountNonLocked = true;

	@Column(columnDefinition = "tinyint default 0")
	private boolean credentialsNonExpired = true;

	@Column(columnDefinition = "tinyint default 0")
	private boolean enabled;

	public static final String VALIDATION_OK = "OK";
	public static final String VALIDATION_ACCOUNT_EXPIRED = "ACCOUNT_EXPIRED";
	public static final String VALIDATION_CREDENTIALS_EXPIRED = "CREDENTIALS_EXPIRED";
	public static final String VALIDATION_LOCKED = "LOCKED";
	public static final String VALIDATION_DISABLED = "DISABLED";

	public String validate() {
		if (!accountNonExpired)
			return VALIDATION_ACCOUNT_EXPIRED;
		if (!credentialsNonExpired)
			return VALIDATION_CREDENTIALS_EXPIRED;
		if (!accountNonLocked)
			return VALIDATION_LOCKED;
		if (!enabled)
			return VALIDATION_DISABLED;
		return VALIDATION_OK;
	}

	@Column(length = 255, nullable = false, unique = true)
	private String email;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	@Column(length = 100, unique = true)
	private String username;
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "userroles", joinColumns = {
			@JoinColumn(name = "idUser", referencedColumnName = "idUser") }, inverseJoinColumns = {
					@JoinColumn(name = "idRole", referencedColumnName = "id") })
	private Set<Role> roles;

	@Transient
	public boolean isInRole(Role role) {
		return isInRole(role.getName());
	}

	@Transient
	public boolean isInRole(String role) {
		for (Role r : getRoles())
			if (r.getName().equals(role))
				return true;
		return false;
	}

	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
		return authorities;
	}

	@Transient
	public List<String> getAuthoritiesStr() {
		List<String> authorities = getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());
		return authorities;
	}

}

