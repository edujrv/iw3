package org.magm.backend.integration.cli2.model;
//La clase ComponentCli2JsonSerializer es un serializador personalizado para la clase ComponentCli2. Este serializador se utiliza para convertir instancias de ComponentCli2 en su representación JSON correspondiente
//La clase extiende StdSerializer<ComponentCli2>, indicando que es un serializador para objetos de tipo ComponentCli2.
//
//En el método serialize, se utiliza JsonGenerator para escribir los campos del objeto JSON. En este caso, se agrega un campo "id" con el valor del ID del componente y un campo "component" con el valor del nombre del componente.
//
//Este serializador es parte de la lógica de serialización personalizada utilizada al convertir objetos ComponentCli2 a formato JSON.
//
//En resumen, esta clase se encarga de cómo se debe serializar un objeto ComponentCli2 específico a su representación JSON
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ComponentCli2JsonSerializer extends StdSerializer<ComponentCli2> {

	private static final long serialVersionUID = -3706327488880784297L;

	public ComponentCli2JsonSerializer(Class<?> t, boolean dummy) {
		super(t, dummy);
	}

	@Override
	public void serialize(ComponentCli2 value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();                                  // {
		gen.writeNumberField("id", value.getId());               //    "id" : 123,
		gen.writeStringField("component", value.getComponent()); //    "component" : "componente 1"
		gen.writeEndObject();                                    // }
	}

}
