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

import org.magm.backend.model.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//La clase ProductCli2 es una entidad JPA (Java Persistence API) que representa la estructura de la tabla cli2_products en la base de datos.
/*
* Aquí están los puntos clave:

Anotaciones JPA:

@Entity: Indica que la clase es una entidad JPA.
@Table(name = "cli2_products"): Especifica el nombre de la tabla en la base de datos.
@PrimaryKeyJoinColumn(name = "id_product"): Especifica la columna que se utilizará como clave primaria.
Atributos:

codCli2: Representa el código del producto en el sistema cli2. Es una columna no nula y única en la base de datos.
expirationDate: Representa la fecha de vencimiento del producto.
components: Representa la relación muchos a muchos con ComponentCli2, utilizando una tabla de unión llamada cli2_product_component.
Relaciones JPA:

@ManyToMany: Define una relación muchos a muchos con ComponentCli2.
@JoinTable: Especifica los detalles de la tabla de unión, como los nombres de las columnas y las claves foráneas.
Hereda de Product:

Extiende la clase Product, lo que indica que ProductCli2 es una especialización de la clase base Product.
Lombok Annotations:

@AllArgsConstructor, @NoArgsConstructor: Generan constructores con y sin argumentos.
@Getter, @Setter: Generan métodos getter y setter para los campos de la clase.
Esta clase representa la entidad ProductCli2 en la base de datos y está diseñada para funcionar con JPA para realizar operaciones de persistencia.
* */
@Entity
@Table(name = "cli2_products")
@PrimaryKeyJoinColumn(name = "id_product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCli2 extends Product{

	private static final long serialVersionUID = 2516446617276638458L;

	@Column(nullable = false, unique = true)
	private String codCli2;

	@Column(nullable = true)
	private Date expirationDate;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "cli2_product_component",
		joinColumns = { @JoinColumn(name = "id_product", referencedColumnName = "id_product") },
		inverseJoinColumns = {	@JoinColumn(name = "id_component", referencedColumnName = "id") })
	private Set<ComponentCli2> components;

}

