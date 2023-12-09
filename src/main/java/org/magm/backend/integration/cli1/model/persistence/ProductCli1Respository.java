package org.magm.backend.integration.cli1.model.persistence;
//El ProductCli1Repository es una interfaz que extiende JpaRepository y proporciona métodos para realizar operaciones de persistencia relacionadas con la entidad ProductCli1. A continuación, se describen las características principales de esta interfaz:
//Los repositorios de Spring Data JPA proporcionan una forma conveniente de interactuar con la capa de persistencia sin necesidad de escribir consultas SQL manualmente. En este caso, ProductCli1Repository permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la entidad ProductCli1.
import java.util.Optional;

import org.magm.backend.integration.cli1.model.ProductCli1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCli1Respository extends JpaRepository<ProductCli1, Long>{ 
	public Optional<ProductCli1> findOneByCodCli1(String codCli1);
}

