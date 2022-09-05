package org.magm.backend.model.persistence;

import java.util.Optional;

import org.magm.backend.model.Factura;
import org.magm.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    Optional<Factura> findByProduct(long numero);

    Void anularFactura(long id);
}
