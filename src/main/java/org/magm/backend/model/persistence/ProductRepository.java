package org.magm.backend.model.persistence;
//La interfaz ProductRepository en el paquete org.magm.backend.model.persistence extiende JpaRepository y proporciona métodos para realizar operaciones de persistencia relacionadas con la entidad Product
//En resumen, la interfaz ProductRepository proporciona métodos estándar de CRUD (Create, Read, Update, Delete) gracias a la extensión de JpaRepository, y además incluye métodos personalizados para realizar consultas nativas y actualizaciones relacionadas con la entidad Product. Este repositorio se utiliza para interactuar con la tabla "products" en la base de datos.
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.magm.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findByProduct(String product);

	@Query(value = "SELECT count(*) FROM products where id_category=?", nativeQuery = true)
	public Integer countProductsByCategory(long idCategory);

	@Transactional
	@Modifying
	@Query(value = "UPDATE products SET stock=? WHERE id=?", nativeQuery = true)
	public int setStock(boolean stock, long idProduct);

}
