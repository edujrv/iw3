package org.magm.backend.integration.cli2.model.persistence;

import java.util.List;
import java.util.Optional;

import org.magm.backend.integration.cli2.model.DetalleFacturaCli2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
//La interfaz DetalleFacturaCli2Repository extiende JpaRepository y proporciona métodos para realizar operaciones de acceso a datos relacionadas con la entidad DetalleFacturaCli2.
//La interfaz extiende JpaRepository<DetalleFacturaCli2, Long>, lo que significa que hereda métodos de acceso a datos comunes de Spring Data JPA para la entidad DetalleFacturaCli2.
//
//Se definen métodos para buscar detalles de factura por su código y para obtener los identificadores de facturas asociadas a un producto específico.
//
//La anotación @Repository indica que esta interfaz es un componente de repositorio de Spring y permite la detección automática por parte del escáner de componentes de Spring.
@Repository
public interface DetalleFacturaCli2Repository extends JpaRepository<DetalleFacturaCli2, Long>{
    public Optional<DetalleFacturaCli2> findOneByCodDetalleFacturaCli2(String codDetalleFacturaCli2);
    @Query(value = "SELECT distinct id_factura from detallefactura where id_producto=?", nativeQuery = true)
    public List<Integer> idDeFacturasdeUnProducto(long idProduct);
}
