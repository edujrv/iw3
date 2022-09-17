package org.magm.backend.model.persistence;

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
