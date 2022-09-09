package org.magm.backend.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="facturas")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Factura implements Serializable {

    //TODO: VER QUE ONDA CON ESTE SERIALIZABLE
    private static final long serialVersionUID = 1583413718748026543L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,unique = true)
    private long numero;

    @Column(columnDefinition = "tinyint default 0", nullable = false)
    private boolean anulada;

    @Column(nullable = false)
    private Date fechaEmision;

    @Column(nullable = false)
    private Date fechaVencimiento;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="id_detalleFactura", nullable = true)
    private List<DetalleFactura> detallesFactura;

}
