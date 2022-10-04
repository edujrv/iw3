package org.magm.backend.model;

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

    @Column(nullable = false,unique = true)
    private long id_factura;

    @Column(nullable = false)
    private String user;

    @Column
    private String operacion;

    @Column
    private Date fecha;

}

