package org.magm.backend.model.persistence;
//La interfaz CategoryRespository en el paquete org.magm.backend.model.persistence extiende JpaRepository y proporciona métodos para realizar operaciones de persistencia relacionadas con la entidad Category
//En resumen, la interfaz CategoryRespository proporciona métodos estándar de CRUD (Create, Read, Update, Delete) gracias a la extensión de JpaRepository, y además incluye un método personalizado para buscar una categoría por su nombre utilizando convenciones de Spring Data JPA. Este repositorio se utiliza para interactuar con la tabla "categories" en la base de datos.
import java.util.Optional;

import org.magm.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRespository extends JpaRepository<Category, Long>{ 
	//https://docs.spring.io/spring-data/jpa/docs/2.7.0/reference/html/#repositories.query-methods.details
	//https://www.baeldung.com/spring-data-derived-queries

	public Optional<Category> findOneByCategory(String category);
}

