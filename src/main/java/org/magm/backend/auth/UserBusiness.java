package org.magm.backend.auth;



import java.util.List;
import java.util.Optional;

import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
//El servicio UserBusiness es responsable de manejar operaciones relacionadas con la entidad User.
/*
* Anotación @Service:

La clase está anotada con @Service, indicando que es un componente de servicio de Spring y será gestionada por el contenedor de Spring.
Inyección de Dependencias:

Se utiliza la anotación @Autowired para inyectar la dependencia UserRepository en el campo userDAO. Esto permite que la clase acceda a las operaciones de base de datos relacionadas con los usuarios.
Método load:

Este método carga un usuario por nombre de usuario o correo electrónico. Utiliza el método findOneByUsernameOrEmail proporcionado por UserRepository.
Si el usuario no se encuentra, se lanza una excepción NotFoundException.
Si ocurre algún error durante la operación, se lanza una excepción BusinessException.
Método changePassword:

Este método cambia la contraseña de un usuario.
Verifica la antigua contraseña antes de realizar el cambio.
Utiliza el PasswordEncoder para codificar la nueva contraseña antes de almacenarla en la base de datos.
Si la antigua contraseña no es válida, se lanza una excepción BadPasswordException.
Si el usuario no se encuentra, se lanza una excepción NotFoundException.
Si ocurre algún error durante la operación, se lanza una excepción BusinessException.
Métodos disable y enable:

Estos métodos deshabilitan o habilitan un usuario, respectivamente, cambiando el estado de la propiedad enabled.
Utilizan el método privado setDisable para realizar la actualización.
Si el usuario no se encuentra, se lanza una excepción NotFoundException.
Si ocurre algún error durante la operación, se lanza una excepción BusinessException.
Método privado setDisable:

Este método actualiza el estado de la propiedad enabled de un usuario y guarda los cambios en la base de datos.
Método list:

Este método devuelve una lista de todos los usuarios en la base de datos.
Si ocurre algún error durante la operación, se lanza una excepción BusinessException.
* */
@Service
@Slf4j
public class UserBusiness implements IUserBusiness {

	@Autowired
	private UserRepository userDAO;

	@Override
	public User load(String usernameOrEmail) throws NotFoundException, BusinessException {
		Optional<User> ou;
		try {
			ou = userDAO.findOneByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
		if (ou.isEmpty()) {
			throw NotFoundException.builder().message("No se encuentra el usuari@ email o nombre =" + usernameOrEmail)
					.build();
		}
		return ou.get();
	}

	@Override
	public void changePassword(String usernameOrEmail, String oldPassword, String newPassword, PasswordEncoder pEncoder)
			throws BadPasswordException, NotFoundException, BusinessException {
		User user = load(usernameOrEmail);
		if (!pEncoder.matches(oldPassword, user.getPassword())) {
			throw BadPasswordException.builder().build();
		}
		user.setPassword(pEncoder.encode(newPassword));
		try {
			userDAO.save(user);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
	}

	@Override
	public void disable(String usernameOrEmail) throws NotFoundException, BusinessException {
		setDisable(usernameOrEmail, false);
	}

	@Override
	public void enable(String usernameOrEmail) throws NotFoundException, BusinessException {
		setDisable(usernameOrEmail, true);
	}

	private void setDisable(String usernameOrEmail, boolean enable) throws NotFoundException, BusinessException {
		User user = load(usernameOrEmail);
		user.setEnabled(enable);
		try {
			userDAO.save(user);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
	}

	@Override
	public List<User> list() throws BusinessException {
		try {
			return userDAO.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
	}

}
