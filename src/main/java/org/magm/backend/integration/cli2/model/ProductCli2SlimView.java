package org.magm.backend.integration.cli2.model;
/*
*
El modelo ProductCli2SlimView es una interfaz de proyección que define una vista simplificada de un producto en el contexto de la integración cli2. Esta interfaz se utiliza para definir la estructura de los datos que se recuperarán al realizar ciertas consultas de productos, proporcionando solo la información necesaria.

La interfaz define los siguientes atributos:

getId(): Método para recuperar el ID del producto.

getProduct(): Método para recuperar el nombre del producto.

getPrice(): Método para recuperar el precio del producto.

getCategory(): Método para recuperar la categoría del producto. Esta categoría es otra interfaz anidada en ProductCli2SlimView que define un método getCategory() para recuperar el nombre de la categoría.

Esta interfaz se utiliza como una proyección en consultas específicas, permitiendo obtener solo los campos necesarios de los productos, lo que puede ser útil para optimizar el rendimiento y reducir la cantidad de datos transferidos.
* */
//https://docs.spring.io/spring-data/commons/docs/current/reference/html/#projections
public interface ProductCli2SlimView {
	Long getId();

	String getProduct();

	Double getPrice();

	Category getCategory();

	interface Category {
		String getCategory();
	}
}
