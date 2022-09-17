package org.magm.backend.integration.cli2.model.persistence;

import java.util.List;
import java.util.Optional;

import org.magm.backend.integration.cli2.model.FacturaCli2;
import org.magm.backend.integration.cli2.model.IFacturaCli2SlimView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaCli2Repository  extends JpaRepository<FacturaCli2, Long>{
    public Optional<FacturaCli2> findOneByCodFacturaCli2(String codFacturaCli2);

    Optional<FacturaCli2> findFacturaCli2ByNumero(long numero);

    Boolean deleteByNumero(long numero);

    List<FacturaCli2> findFacturaCli2ByAnuladaIsTrue();

    List<FacturaCli2> findFacturaCli2ByAnuladaIsFalse();

    @Query(value = " select df.* from facturas f inner join detalle_factura df on f.id = df.id_factura", nativeQuery = true)
    public List<IFacturaCli2SlimView> findAllV2();

    @Query(value = "UPDATE factura SET anulada = true WHERE id=?", nativeQuery = true)
    public void anularfactura (long id);







}
