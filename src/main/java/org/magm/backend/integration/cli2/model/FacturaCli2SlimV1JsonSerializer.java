package org.magm.backend.integration.cli2.model;
import static java.time.temporal.ChronoUnit.DAYS;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.magm.backend.model.DetalleFactura;
import org.magm.backend.util.JsonUtiles;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class FacturaCli2SlimV1JsonSerializer extends StdSerializer<FacturaCli2>  {
    private static final long serialVersionUID = -3706328888880784297L;

    public FacturaCli2SlimV1JsonSerializer(Class<FacturaCli2> t, boolean dummy) {
        super(t, dummy);
    }

    @Override
    public void serialize(FacturaCli2 value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        gen.writeNumberField("id", value.getId());
        gen.writeStringField("version", "v1");
        gen.writeNumberField("numero", value.getNumero());
        gen.writeStringField("code", value.getCodFacturaCli2());
        gen.writeBooleanField("anulada", value.isAnulada());
        gen.writeObjectField("fechaEmision", value.getFechaEmision());
        gen.writeObjectField("fechaVencimiento ", value.getFechaVencimiento());
        gen.writeNumberField("price", value.getPrice());

        if(value.getDetallesFactura() != null){
            gen.writeNumberField("cantidadDetalles", value.getDetallesFactura().size());
        }else{
            gen.writeNullField("cantidadDetalles");
        }

//        String componentsStr = JsonUtiles
//                .getObjectMapper(ComponentCli2.class, new ComponentCli2JsonSerializer(ComponentCli2.class, false), null)
//                .writeValueAsString(value.getComponents());
//        gen.writeFieldName("components");
//        gen.writeRawValue(componentsStr);
//
        gen.writeEndObject();
    }
}
