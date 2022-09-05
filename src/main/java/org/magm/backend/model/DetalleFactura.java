package org.magm.backend.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="detalleFactura")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleFactura implements Serializable {

    @Id
    private long idDetalle;

    @Column
    private double cantidad;

    @Column
    private double precio;

    @Column
    @ManyToOne // TODO: TRABAJO PARA ALFREDO :D
    @JoinColumn(name = "idProducto", nullable = false)
    private Product producto;

}
/*
*- id long (id relacional)
- cantidad double
- producto referencia al modelo ProductCli2
- precio double
* */