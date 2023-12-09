package org.magm.backend.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//La clase Factura en el paquete org.magm.backend.model representa una factura en el sistema
//En resumen, la clase Factura representa una factura en el sistema, con atributos como el número de factura, su estado (anulada o no), fechas de emisión y vencimiento, el precio total y una lista de detalles de factura asociados. La estrategia de herencia utilizada es la de tipo "JOINED", lo que implica que la información se almacena en varias tablas relacionadas.
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

    @Column(nullable = false)
    private double price;   //Total

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="id_factura", nullable = true)
    private List<DetalleFactura> detallesFactura;

}
