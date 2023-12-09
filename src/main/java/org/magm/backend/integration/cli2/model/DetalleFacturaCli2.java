package org.magm.backend.integration.cli2.model;

import java.util.Date;
import java.util.Set;
//La clase DetalleFacturaCli2 es una entidad JPA que extiende la clase DetalleFactura y parece representar detalles específicos de una factura en el contexto del módulo CLI2
//La clase DetalleFacturaCli2 tiene anotaciones JPA como @Entity y @Table, lo que indica que está mapeada a una tabla en la base de datos.
//
//La clase extiende DetalleFactura, lo que sugiere que hereda propiedades y comportamientos de la clase base DetalleFactura.
//
//Se agrega una columna específica codDetalleFacturaCli2 con anotación @Column para almacenar un código único para el detalle de la factura en el contexto de CLI2.
//
//Se proporcionan anotaciones de Lombok (@AllArgsConstructor, @NoArgsConstructor, @Getter, @Setter) para generar automáticamente constructores y métodos getter/setter.
//
//En general, esta clase parece ser una entidad de base de datos específica para detalles de factura en el contexto de CLI2 con la adición de un código único asociado a cada detalle.
import javax.persistence.*;

import org.magm.backend.model.DetalleFactura;
import org.magm.backend.model.Factura;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cli2_detalleFactura")
@PrimaryKeyJoinColumn(name = "id_detalleFactura")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetalleFacturaCli2 extends DetalleFactura {
    private static final long serialVersionUID = 2577446617276638458L;

    @Column(nullable = false, unique = true)
    private String codDetalleFacturaCli2;
}
