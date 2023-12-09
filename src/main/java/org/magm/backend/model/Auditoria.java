package org.magm.backend.model;
//La clase Auditoria en el paquete org.magm.backend.model representa una entidad de auditoría en el sistema. Aquí está una descripción de la clase:
//En resumen, la clase Auditoria representa una entidad de auditoría en el sistema, con atributos como el ID de la factura, el nombre del usuario, la operación realizada y la fecha de la auditoría. Los objetos de esta clase se almacenan en la tabla "auditoria" de la base de datos.
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "auditoria")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Auditoria {

    private static final long serialVersionUID = 1583413718748026543L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,unique = false)
    private long id_factura;

    @Column(nullable = false)
    private String user;

    @Column
    private String operacion;

    @Column
    private Date fecha;

}

