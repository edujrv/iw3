package org.magm.backend.integration.cli2.model.persistence;

import java.util.Optional;

import org.magm.backend.integration.cli2.model.DetalleFacturaCli2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleFacturaCli2Repository extends JpaRepository<DetalleFacturaCli2, Long>{
    public Optional<DetalleFacturaCli2> findOneByCodDetalleFacturaCli2(String codDetalleFacturaCli2);
}
