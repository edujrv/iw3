package org.magm.backend.integration.cli2.model;
//El código proporcionado para DetalleFacturaCli2JsonSerializer parece ser un serializador Jackson personalizado para la clase DetalleFactura
//La clase extiende StdSerializer<DetalleFactura>, lo que indica que es un serializador estándar de Jackson para objetos de tipo DetalleFactura.
//
//Se proporciona una implementación del método serialize para personalizar el proceso de serialización. En este caso, se están escribiendo campos específicos en el generador JSON.
//
//Los campos como "code", "price", "cantidad" y "product" se están escribiendo en el generador JSON utilizando los métodos proporcionados por JsonGenerator.
//
//La anotación @Override indica que el método serialize está sobrescribiendo un método en la superclase.
//
//Este serializador parece estar diseñado para convertir instancias de DetalleFactura a formato JSON de una manera específica.
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.magm.backend.model.DetalleFactura;

import java.io.IOException;
//
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
