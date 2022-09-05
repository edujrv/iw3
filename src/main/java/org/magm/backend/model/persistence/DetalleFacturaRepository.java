package org.magm.backend.model.persistence;

import java.util.Optional;

import org.magm.backend.model.DetalleFactura;
import org.magm.backend.model.Factura;
import org.magm.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long>{
    Optional<DetalleFactura> findByProduct(long id);
}
