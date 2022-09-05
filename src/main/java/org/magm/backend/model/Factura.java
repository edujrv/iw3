package org.magm.backend.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="facturas")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(unique = true)
    long numero;

    @Column
    private Timestamp fechaEmision;

    @Column
    private Timestamp fechaVencimiento;

    @Column
    private boolean anulada;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idDetalle", nullable = false)
    private List<DetalleFactura> detalles;

}

/*
*Factura
- id long (id relacional)
- numero long (valor único)
- fechaEmision
- fechaVencimiento
- anulada boolean
Cada factura tiene un detalle compuesto por Items, cada Item contiene:
- id long (id relacional)
- cantidad double
- producto referencia al modelo ProductCli2
- precio double
* */
