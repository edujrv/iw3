package org.magm.backend.integration.cli2.model;
//La clase está anotada con @Entity, indicando que es una entidad JPA que se puede almacenar en la base de datos.
//
//Se utiliza la anotación @Table para especificar el nombre de la tabla de base de datos asociada a esta entidad.
//
//El campo id se anota con @Id y @GeneratedValue, lo que significa que es la clave primaria y se generará automáticamente.
//
//El campo component se mapea a una columna en la base de datos con la anotación @Column, y se especifica que debe ser único y tener una longitud máxima de 100 caracteres.
//
//Se utilizan las anotaciones de Lombok (@AllArgsConstructor, @NoArgsConstructor, @Getter, @Setter) para generar automáticamente constructores con y sin argumentos, así como métodos getter y setter.
//
//En resumen, esta clase define la estructura de un componente en el contexto de la aplicación y cómo se mapea a la base de datos.
import java.io.Serializable;

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
@Table(name = "cli2_components")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComponentCli2 implements Serializable{

	private static final long serialVersionUID = 5695618110757822325L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 100, unique = true)
	private String component;
}

