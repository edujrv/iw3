package org.magm.backend.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
* La interfaz RoleRepository es una interfaz de repositorio de Spring Data JPA que extiende JpaRepository y está diseñada para trabajar con la entidad Role. Aquí hay una descripción de la interfaz:

Anotación de la Interfaz:

@Repository: Indica que esta interfaz es un componente de repositorio de Spring.
Extensión de JpaRepository:

La interfaz extiende JpaRepository<Role, Integer>, donde Role es el tipo de entidad con la que trabaja la interfaz y Integer es el tipo de datos del identificador primario de la entidad.
Funcionalidades Básicas:

Al extender JpaRepository, la interfaz hereda automáticamente métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la entidad Role. Estos métodos incluyen save, findById, findAll, delete, entre otros.
En resumen, RoleRepository proporciona una interfaz para interactuar con la base de datos para las operaciones relacionadas con la entidad Role utilizando Spring Data JPA.
* */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{ 
}

