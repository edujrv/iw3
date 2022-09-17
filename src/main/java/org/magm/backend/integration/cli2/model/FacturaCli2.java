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
