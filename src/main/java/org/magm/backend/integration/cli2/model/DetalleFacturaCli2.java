package org.magm.backend.integration.cli2.model;

import lombok.*;
import org.magm.backend.model.DetalleFactura;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "cli2_detalleFactura")
@PrimaryKeyJoinColumn(name = "id_detalleFactura")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetalleFacturaCli2 extends DetalleFactura {
    private static final long serialVersionUID = 2516446617276638458L;
    @Column(nullable = false,unique = true)
    private String codCli2;
}
