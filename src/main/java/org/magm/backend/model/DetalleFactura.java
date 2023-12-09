package org.magm.backend.model;
//La clase DetalleFactura en el paquete org.magm.backend.model representa un detalle de factura en el sistema. Aquí está una descripción de la clase:
//En resumen, la clase DetalleFactura representa un detalle de factura en el sistema, con atributos como el código del ítem, el precio subtotal, la cantidad y una relación con el producto asociado. La estrategia de herencia utilizada es la de tipo "JOINED", lo que implica que la información se almacena en varias tablas relacionadas.
import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="detalleFactura")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleFactura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String codItem;

    @Column(nullable = false)
    private double price; //Subtotal

    @Column(nullable = false)
    private long cantidad;

    @ManyToOne
    @JoinColumn(name="id_producto", nullable = true)
    private Product product;
}
