package org.magm.backend.integration.cli2.model.persistence;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.SQLUpdate;
import org.magm.backend.integration.cli2.model.FacturaCli2;
import org.magm.backend.integration.cli2.model.IFacturaCli2SlimView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface FacturaCli2Repository  extends JpaRepository<FacturaCli2, Long>{
    public Optional<FacturaCli2> findOneByCodFacturaCli2(String codFacturaCli2);

    Optional<FacturaCli2> findFacturaCli2ByNumero(long numero);

    Boolean deleteByNumero(long numero);

    List<FacturaCli2> findFacturaCli2ByAnuladaIsTrue();

    List<FacturaCli2> findFacturaCli2ByAnuladaIsFalse();

    @Query(value = " select df.* from facturas f inner join detalle_factura df on f.id = df.id_factura", nativeQuery = true)
    public List<IFacturaCli2SlimView> findAllV2();

    @Query(value = " select * from facturas f inner join detalle_factura df on f.id = df.id_factura WHERE f.numero = ?", nativeQuery = true)
    public IFacturaCli2SlimView findOneByNumeroV2(long numero);

    IFacturaCli2SlimView findByNumero(long numero);

    @Transactional
    @Modifying
    @Query(value = "UPDATE facturas SET anulada = true WHERE id = ?", nativeQuery = true)
    public void anularfactura (long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE facturas SET anulada = false WHERE id=?", nativeQuery = true)
    public void desAnularFactura (long id);

//    @Query(value = "SELECT distinct id FROM facturas f INNER JOIN detalle_factura d on f.id=d.id_factura" +
//            "inner join products p on d.id_producto=p.id where p.id=?", nativeQuery = true)
    @Query(value = "SELECT id_factura FROM detalle_factura WHERE id_producto = ?", nativeQuery = true)
    public List<Integer> idDeFacturasdeUnProducto(long idProduct);

}
