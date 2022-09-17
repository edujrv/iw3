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
