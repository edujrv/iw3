package org.magm.backend.integration.cli2.model;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.ICategoryBusiness;
import org.magm.backend.model.business.NotFoundException;
import org.magm.backend.util.JsonUtiles;

import java.io.IOException;
/*
* El ProductCli2JsonDeserializer es un deserializador Jackson personalizado (StdDeserializer) diseñado para convertir JSON en instancias de la clase ProductCli2. Este deserializador toma un objeto JSON y crea una instancia de ProductCli2 basándose en la información proporcionada en el JSON.

Aquí está el resumen del código:

Constructor:

java
Copy code
protected ProductCli2JsonDeserializer(Class<?> vc) {
    super(vc);
}

public ProductCli2JsonDeserializer(Class<?> vc, ICategoryBusiness categoryBusiness) {
    super(vc);
    this.categoryBusiness = categoryBusiness;
}
Estos son los constructores de la clase. El segundo constructor toma una instancia de ICategoryBusiness que se utilizará para cargar la categoría del producto durante el proceso de deserialización.

Método deserialize:

java
Copy code
@Override
public ProductCli2 deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
    // Código de deserialización aquí
}
Este método se ejecuta cuando se intenta deserializar un objeto JSON en una instancia de ProductCli2. Aquí está el detalle del código de deserialización:

Se crea una nueva instancia de ProductCli2.
Se obtiene un nodo JSON a partir del JsonParser.
Se utilizan métodos de utilidad de JsonUtiles para extraer los valores del nodo JSON y establecer los campos correspondientes en la instancia de ProductCli2.
Se intenta cargar la categoría utilizando la instancia de ICategoryBusiness proporcionada.
La instancia de ProductCli2 resultante se devuelve.
Este deserializador se encarga de leer un objeto JSON y convertirlo en una instancia de ProductCli2, estableciendo sus campos de acuerdo con el formato del JSON proporcionado. Cabe mencionar que si hay excepciones durante el proceso de deserialización, se capturan y no se propagan más allá del deserializador.
* */
public class ProductCli2JsonDeserializer extends StdDeserializer<ProductCli2> {
    private static final long serialVersionUID = -3891285352118964728L;

    protected ProductCli2JsonDeserializer(Class<?> vc) {
        super(vc);
    }

    private ICategoryBusiness categoryBusiness;

    public ProductCli2JsonDeserializer(Class<?> vc, ICategoryBusiness categoryBusiness) {
        super(vc);
        this.categoryBusiness = categoryBusiness;
    }

    @Override
    public ProductCli2 deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        ProductCli2 r = new ProductCli2();
        JsonNode node = jp.getCodec().readTree(jp);

        String code = JsonUtiles.getString(node, "product_code,code_product,code".split(","),
                System.currentTimeMillis() + "");
        String productDesc = JsonUtiles.getString(node,
                "product,description,product_description,product_name".split(","), null);
        double price = JsonUtiles.getDouble(node, "product_price,price_product,price".split(","), 0);
        boolean stock = JsonUtiles.getBoolean(node, "stock,in_stock".split(","), false);
        r.setCodCli2(code);
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
