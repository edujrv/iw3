package org.magm.backend.model;
//La clase Product en el paquete org.magm.backend.model representa un producto en el sistema. Aquí está una descripción de la clase:
//En resumen, la clase Product representa un producto en el sistema, con atributos como el nombre, el estado de stock, el precio y una relación con la categoría a la que pertenece. La estrategia de herencia utilizada es la de tipo "JOINED", lo que implica que la información se almacena en varias tablas relacionadas.
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="products")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable{

	private static final long serialVersionUID = 1583413618748026543L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 100, unique = true)
	private String product;
	
	@Column(columnDefinition = "tinyint default 0")
	private boolean stock = true;

	private double price;	//Precio
	
	@ManyToOne
	@JoinColumn(name="id_category", nullable = true)
	private Category category;

}
