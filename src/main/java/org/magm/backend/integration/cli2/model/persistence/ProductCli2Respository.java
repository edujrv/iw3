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

@Repository
public interface ProductCli2Respository extends JpaRepository<ProductCli2, Long> {
	public List<ProductCli2> findByExpirationDateBeforeOrderByExpirationDateDesc(Date expirationDate);

	public List<ProductCli2SlimView> findByOrderByPriceDesc();

	public Optional<ProductCli2> findOneByCodCli2(String codCli2);
}
