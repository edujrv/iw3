package org.magm.backend.integration.cli2.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.magm.backend.model.DetalleFactura;

import java.io.IOException;

public class DetalleFacturaCli2JsonSerializer extends StdSerializer<DetalleFactura> {


    protected DetalleFacturaCli2JsonSerializer(Class<?> t, boolean dummy) {
        super(t, dummy);
    }

    @Override
    public void serialize(DetalleFactura value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        //gen.writeNumberField("id", value.getId());
        gen.writeStringField("code", value.getCodItem());
        gen.writeNumberField("price", value.getPrice());
        gen.writeNumberField("cantidad",value.getCantidad());
        gen.writeObjectField("product",value.getProduct());
        gen.writeEndObject();
    }
}
