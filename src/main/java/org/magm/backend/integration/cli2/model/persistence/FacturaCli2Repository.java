package org.magm.backend.integration.cli2.model.persistence;

import java.util.Optional;

import org.magm.backend.integration.cli2.model.FacturaCli2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaCli2Repository extends JpaRepository<FacturaCli2, Long>{
    public Optional<FacturaCli2> findOneByCodCli2(String codCli2);
}
