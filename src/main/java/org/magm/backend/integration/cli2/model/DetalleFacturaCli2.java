package org.magm.backend.integration.cli2.model;

import java.util.Date;
import java.util.Set;

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
