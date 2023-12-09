package org.magm.backend.integration.cli2.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.magm.backend.model.DetalleFactura;
import org.magm.backend.model.Factura;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
* La clase FacturaCli2 extiende Factura, lo que indica que FacturaCli2 es una subclase de Factura. Esta relación de herencia sugiere que FacturaCli2 hereda atributos y comportamientos de la clase Factura.

La anotación @Entity indica que la clase es una entidad JPA y se puede persistir en una base de datos.

La anotación @Table(name = "cli2_factura") especifica el nombre de la tabla en la base de datos a la que está mapeada la entidad.

La anotación @PrimaryKeyJoinColumn(name = "id_factura") especifica que la clave principal de la tabla cli2_factura está mapeada a la columna id_factura.

La anotación @Column(nullable = false, unique = true) en codFacturaCli2 especifica que el campo codFacturaCli2 no puede ser nulo y debe ser único.

La relación @ManyToMany con ComponentCli2 indica una relación muchos a muchos entre FacturaCli2 y ComponentCli2, y la tabla de unión se llama cli2_factura_component.
* */
@Entity
@Table(name = "cli2_factura")
@PrimaryKeyJoinColumn(name = "id_factura")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FacturaCli2 extends Factura {

    private static final long serialVersionUID = 2566446617276638458L;

    @Column(nullable = false, unique = true)
    private String codFacturaCli2;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cli2_factura_component",
            joinColumns = { @JoinColumn(name = "id_factura", referencedColumnName = "id_factura") },
            inverseJoinColumns = {	@JoinColumn(name = "id_component", referencedColumnName = "id") })
    private Set<ComponentCli2> components;
}
