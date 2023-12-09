package org.magm.backend.integration.cli2.model;

import static java.time.temporal.ChronoUnit.DAYS;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.magm.backend.integration.cli2.model.business.IFacturaCli2Business;
import org.magm.backend.model.DetalleFactura;
import org.magm.backend.model.Factura;
import org.magm.backend.model.Product;
import org.magm.backend.util.JsonUtiles;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
//El serializador FacturaCli2SlimV2JsonSerializer está diseñado para serializar objetos FacturaCli2 en formato JSON según la versión 2 de la vista "delgada" (v2)
//Se implementa el método serialize para convertir el objeto FacturaCli2 en formato JSON.
//Se escribe el campo "id" y "version" directamente en el objeto JSON.
//Se utiliza DetalleFacturaCli2JsonSerializer para serializar la lista de detalles de factura.
//Este serializador proporciona una representación JSON de la entidad FacturaCli2 según la versión 2 de la vista "delgada". Si tienes más preguntas o necesitas más clarificaciones, no dudes en preguntar.
//CtrlZ
public class FacturaCli2SlimV2JsonSerializer extends StdSerializer<FacturaCli2> {
    private static final long serialVersionUID = -3706328888880784297L;


    public FacturaCli2SlimV2JsonSerializer(Class<FacturaCli2> t, boolean dummy) {
        super(t, dummy);
    }

    @Override
    public void serialize(FacturaCli2 value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        gen.writeNumberField("id", value.getId());
        gen.writeStringField("version", "v2");

        String componentsStr = JsonUtiles
                .getObjectMapper(DetalleFactura.class, new DetalleFacturaCli2JsonSerializer(DetalleFactura.class, false), null)
                .writeValueAsString(value.getDetallesFactura());
        gen.writeFieldName("details");
        gen.writeRawValue(componentsStr);

        //        gen.writeNumberField("daysExpired", DAYS.between(Instant.ofEpochMilli(value.getExpirationDate().getTime())
//                .atZone(ZoneId.systemDefault()).toLocalDateTime(), LocalDateTime.now()));


        gen.writeEndObject();
    }
}
//
//    @Override
//    public void serialize(IFacturaCli2SlimView value, JsonGenerator gen, SerializerProvider provider) throws IOException {
//        gen.writeStartObject();
//
//        gen.writeNumberField("id", value.getId());
///*
//        gen.writeObjectFieldStart("detallesFactura");
//        for (DetalleFactura item : value.getDetallesFactura()) {
//            gen.writeNumberField("cantidad",item.getPrice();
//        }
//        gen.writeEndObject();
//*/
//
//        gen.writeStringField("version", "v2");
//        List<IFacturaCli2SlimView.DetallesFactura> detalles = value.getDetallesFactura();
//        String componentsStr = JsonUtiles
//                .getObjectMapper(DetalleFactura.class, new DetalleFacturaCli2JsonSerializer(DetalleFactura.class, false), null)
//                .writeValueAsString(detalles);
//        gen.writeFieldName("details");
//        gen.writeRawValue(componentsStr);
//
//        //        gen.writeNumberField("daysExpired", DAYS.between(Instant.ofEpochMilli(value.getExpirationDate().getTime())
////                .atZone(ZoneId.systemDefault()).toLocalDateTime(), LocalDateTime.now()));
//
//
//        gen.writeEndObject();
//    }

