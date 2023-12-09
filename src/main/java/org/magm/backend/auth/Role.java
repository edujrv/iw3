package org.magm.backend.auth;

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
/*
*La clase Role es una entidad JPA que representa un rol en el sistema. Aquí hay una descripción de la clase:

Anotaciones de la Clase:

@Entity: Indica que la clase es una entidad JPA y está mapeada a una tabla en la base de datos.
@Table(name = "roles"): Especifica el nombre de la tabla a la que está mapeada la entidad en la base de datos.
Anotación de Campos:

@Id: Indica que el campo id es la clave primaria de la entidad.
@GeneratedValue(strategy = GenerationType.IDENTITY): Especifica que el valor del campo id se generará automáticamente y será una identidad de base de datos (autonumérico).
@Column: Especifica las propiedades de la columna en la tabla.
nullable = false: Indica que el campo no puede tener valores nulos.
length = 100: Especifica la longitud máxima de la columna.
unique = true: Indica que los valores en la columna deben ser únicos.
Campos de la Clase:

description: Representa la descripción del rol.
id: Representa el identificador único del rol.
name: Representa el nombre único del rol.
Constructores Lombok:

@AllArgsConstructor: Genera un constructor con todos los campos.
@NoArgsConstructor: Genera un constructor sin argumentos.
Getter y Setter Lombok:

@Getter: Genera métodos getters para todos los campos.
@Setter: Genera métodos setters para todos los campos.
La clase Role se utiliza para representar y persistir información sobre roles en la base de datos.
* */
@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role implements Serializable {

	private static final long serialVersionUID = -845420067971973620L;
	@Column(nullable = false, length = 100)
	private String description;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false)
	private String name;
}

