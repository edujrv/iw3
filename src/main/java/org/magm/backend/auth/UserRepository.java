package org.magm.backend.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//La interfaz UserRepository extiende JpaRepository y se utiliza para realizar operaciones de acceso a datos relacionadas con la entidad User
//En resumen, esta interfaz proporciona métodos básicos de CRUD (Crear, Leer, Actualizar, Eliminar) para la entidad User. Además, incluye un método personalizado para buscar un usuario por nombre de usuario o correo electrónico. Cuando necesites realizar operaciones de base de datos relacionadas con la entidad User, puedes utilizar esta interfaz, y Spring Data JPA se encargará de implementar automáticamente las operaciones de base de datos correspondientes.
@Repository
public interface UserRepository extends JpaRepository<User, Long>{ 
	public Optional<User> findOneByUsernameOrEmail(String username, String email);
}

