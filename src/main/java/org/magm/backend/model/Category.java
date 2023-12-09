package org.magm.backend.model;
//La clase Category en el paquete org.magm.backend.model representa una categoría en el sistema.
//En resumen, la clase Category representa una categoría en el sistema, con atributos como el nombre de la categoría. Los objetos de esta clase se almacenan en la tabla "categories" de la base de datos.
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 100, unique = true)
	private String category;
	
}
