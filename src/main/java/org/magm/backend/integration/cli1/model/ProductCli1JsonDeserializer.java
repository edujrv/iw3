package org.magm.backend.integration.cli1.model;

import java.io.IOException;

import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.ICategoryBusiness;
import org.magm.backend.model.business.NotFoundException;
import org.magm.backend.util.JsonUtiles;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
/*
*La clase ProductCli1JsonDeserializer es un deserializador personalizado de Jackson que se utiliza para convertir el formato JSON de un producto cli1 en un objeto ProductCli1. Aquí hay una descripción de la clase:

Extendido de StdDeserializer<ProductCli1>:

Esta clase extiende StdDeserializer<ProductCli1>, que es parte del framework Jackson y proporciona la funcionalidad básica de deserialización.
Inyección de Dependencias:

private ICategoryBusiness categoryBusiness: Se inyecta una instancia de ICategoryBusiness para manejar las operaciones comerciales relacionadas con las categorías de productos.
Constructor:

ProductCli1JsonDeserializer(Class<?> vc, ICategoryBusiness categoryBusiness): Constructor que recibe la clase y una instancia de ICategoryBusiness. Utiliza estos valores para llamar al constructor de la superclase.
Método deserialize:

Este método se llama cuando Jackson encuentra un objeto JSON que debe ser convertido a un objeto ProductCli1.
Lee el nodo JSON usando un JsonParser.
Utiliza la clase de utilidad JsonUtiles para extraer valores específicos del nodo JSON, como el código del producto, la descripción, el precio, el estado del stock y la categoría.
Crea un nuevo objeto ProductCli1 y configura sus propiedades con los valores extraídos del JSON.
Si se proporciona el nombre de la categoría en el JSON, intenta cargar la categoría correspondiente utilizando la instancia de ICategoryBusiness y la establece en el objeto ProductCli1.
En resumen, este deserializador convierte un objeto JSON que representa un producto cli1 en una instancia de la clase ProductCli1, teniendo en cuenta la posibilidad de incluir información sobre la categoría del producto.
* */
public class ProductCli1JsonDeserializer extends StdDeserializer<ProductCli1> {

	private static final long serialVersionUID = -3881285352118964728L;

	protected ProductCli1JsonDeserializer(Class<?> vc) {
		super(vc);
	}

	private ICategoryBusiness categoryBusiness;

	public ProductCli1JsonDeserializer(Class<?> vc, ICategoryBusiness categoryBusiness) {
		super(vc);
		this.categoryBusiness = categoryBusiness;
	}

	@Override
	public ProductCli1 deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
		ProductCli1 r = new ProductCli1();
		JsonNode node = jp.getCodec().readTree(jp);

		String code = JsonUtiles.getString(node, "product_code,code_product,code".split(","),
				System.currentTimeMillis() + "");
		String productDesc = JsonUtiles.getString(node,
				"product,description,product_description,product_name".split(","), null);
		double price = JsonUtiles.getDouble(node, "product_price,price_product,price".split(","), 0);
		boolean stock = JsonUtiles.getBoolean(node, "stock,in_stock".split(","), false);
		r.setCodCli1(code);
		r.setProduct(productDesc);
		r.setPrice(price);
		r.setStock(stock);
		String categoryName = JsonUtiles.getString(node, "category,product_category,category_product".split(","), null);
		if (categoryName != null) {
			try {
				r.setCategory(categoryBusiness.load(categoryName));
			} catch (NotFoundException | BusinessException e) {
			}
		}
		return r;
	}

}
