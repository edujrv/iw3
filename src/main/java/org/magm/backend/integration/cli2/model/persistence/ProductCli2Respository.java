package org.magm.backend.integration.cli2.model.persistence;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.magm.backend.integration.cli1.model.ProductCli1;
import org.magm.backend.integration.cli2.model.ProductCli2;
import org.magm.backend.integration.cli2.model.ProductCli2SlimView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
//La interfaz ProductCli2Respository extiende JpaRepository y proporciona métodos para realizar operaciones de acceso a datos relacionadas con la entidad ProductCli2
/*
* La interfaz extiende JpaRepository<ProductCli2, Long>, lo que significa que hereda métodos de acceso a datos comunes de Spring Data JPA para la entidad ProductCli2. Esto incluye operaciones CRUD estándar y métodos para realizar consultas personalizadas.

Se define un método findByExpirationDateBeforeOrderByExpirationDateDesc, que busca productos cuya fecha de vencimiento sea anterior a la fecha proporcionada, ordenados por fecha de vencimiento de forma descendente.

Se define un método findByOrderByPriceDesc, que busca productos ordenados por precio de forma descendente. Nota: Este método devuelve una lista de ProductCli2SlimView, no de ProductCli2.

Se define un método findOneByCodCli2 para buscar un producto por su código (codCli2).

La anotación @Repository indica que esta interfaz es un componente de repositorio de Spring y permite la detección automática por parte del escáner de componentes de Spring.

En resumen, esta interfaz proporciona métodos para acceder a datos relacionados con los productos (ProductCli2) en la base de datos
* */
@Repository
public interface ProductCli2Respository extends JpaRepository<ProductCli2, Long> {
	public List<ProductCli2> findByExpirationDateBeforeOrderByExpirationDateDesc(Date expirationDate);

	public List<ProductCli2SlimView> findByOrderByPriceDesc();

	public Optional<ProductCli2> findOneByCodCli2(String codCli2);
}
