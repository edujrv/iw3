package org.magm.backend.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.magm.backend.auth.filters.AuthConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
/*
* La clase RoleSerializer es un serializador Jackson personalizado para la clase Role. Aquí está una descripción de la clase:

Anotación de la Clase:

@JsonComponent: Esta anotación indica que la clase es un componente de Jackson y debe ser escaneada automáticamente.
Herencia y Constructor:

La clase extiende StdSerializer<Role>, que es una clase base para serializadores personalizados de Jackson.
Tiene un constructor que toma la clase a serializar (Role) y un booleano (dummy).
Método serialize:

Sobrescribe el método serialize, que es responsable de convertir un objeto Role en formato JSON.
En este caso, genera un objeto JSON simple con un campo "role" que contiene el nombre del rol.
En resumen, esta clase proporciona la lógica de serialización específica para la clase Role cuando se utiliza Jackson para convertir objetos Java a formato JSON.
* */
public class RoleSerializer extends StdSerializer<Role>  {

    public RoleSerializer(Class<Role> t, boolean dummy) {
        super(t, dummy);
    }

    @Override
    public void serialize(Role value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("role", value.getName());
        gen.writeEndObject();
    }
}
