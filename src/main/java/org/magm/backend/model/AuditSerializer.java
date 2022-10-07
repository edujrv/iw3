package org.magm.backend.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class AuditSerializer extends StdSerializer<Auditoria> {
    private static final long serialVersionUID = -3706328888880784297L;

    public AuditSerializer(Class<?> t, boolean dummy) {
        super(t, dummy);
    }

    @Override
    public void serialize(Auditoria value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("fecha", value.getFecha());
        gen.writeStringField("operacion", value.getOperacion());
        gen.writeNumberField("factura", value.getId_factura());
        gen.writeStringField("usuario", value.getUser());
        gen.writeEndObject();
    }
}
