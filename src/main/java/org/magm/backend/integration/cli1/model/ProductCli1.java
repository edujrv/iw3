package org.magm.backend.integration.cli1.model;
//La clase ProductCli1 es una entidad JPA que representa productos específicos del sistema cli1.
//En resumen, esta clase representa productos específicos del sistema cli1 y extiende la funcionalidad básica de la clase Product. La relación de herencia está respaldada por una estrategia de clave primaria compartida.
import javax.persistence.*;

import org.magm.backend.model.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cli1_products")
@PrimaryKeyJoinColumn(name = "id_product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCli1 extends Product{

	private static final long serialVersionUID = 2516446617276638458L;

	@Column(nullable = false, unique = true)
	private String codCli1;

}

