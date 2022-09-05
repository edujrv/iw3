package org.magm.backend.integration.cli2.model;

import lombok.*;
import org.magm.backend.model.Factura;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "cli2_facturas")
@PrimaryKeyJoinColumn(name = "id_factura")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FacturaCli2 extends Factura {
    private static final long serialVersionUID = 2516446617276638458L;
    @Column(nullable = false,unique = true)
    private String codCli2;
}