package org.magm.backend.integration.cli2.model.persistence;

import java.util.Optional;

import org.magm.backend.integration.cli2.model.ProductCli2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCli2Repository extends JpaRepository<ProductCli2, Long>{
    public Optional<ProductCli2> findOneByCodCli2(String codCli2);
}

