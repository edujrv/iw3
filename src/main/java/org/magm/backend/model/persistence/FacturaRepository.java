package org.magm.backend.model.persistence;
//La interfaz FacturaRepository en el paquete org.magm.backend.model.persistence extiende JpaRepository y proporciona métodos para realizar operaciones de persistencia relacionadas con la entidad Factura. Aquí está una descripción de la interfaz:
//En resumen, la interfaz FacturaRepository proporciona métodos estándar de CRUD (Create, Read, Update, Delete) gracias a la extensión de JpaRepository, y además incluye métodos personalizados para realizar consultas nativas relacionadas con la entidad Factura. Este repositorio se utiliza para interactuar con la tabla "facturas" en la base de datos.
import org.magm.backend.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    @Query(value = "SELECT distinct id FROM factura f INNER JOIN detallefactura d on f.(clave)=d.(clave)" +
            "inner join producto p on d.(clave)=p.(clave) where p.id=?", nativeQuery = true)
    public List<Integer> idDeFacturasdeUnProducto(long idProduct);

    @Query(value = "UPDATE factura SET anulada = true WHERE id=?", nativeQuery = true)
    public void anularfactura (long id);
}
