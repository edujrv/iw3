package org.magm.backend.integration.cli2.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.magm.backend.model.Factura;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cli2_factura")
@PrimaryKeyJoinColumn(name = "id_factura")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FacturaCli2 extends Factura {

    private static final long serialVersionUID = 2566446617276638458L;

    @Column(nullable = false, unique = true)
    private String codFacturaCli2;

}
