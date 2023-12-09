
package org.magm.backend.integration.cli2.model;

import static java.time.temporal.ChronoUnit.DAYS;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.magm.backend.util.JsonUtiles;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
public class ProductCli2SlimV1JsonSerializer extends StdSerializer<ProductCli2> {
/*
* El ProductCli2SlimV1JsonSerializer es un serializador Jackson personalizado (StdSerializer) diseñado para serializar instancias de la clase ProductCli2 en formato JSON. Este serializador está diseñado para producir una versión "delgada" o simplificada de la entidad ProductCli2, con información específica para la versión 1.

Aquí hay un resumen de lo que hace este serializador:

Constructor:

java
Copy code
public ProductCli2SlimV1JsonSerializer(Class<?> t, boolean dummy) {
    super(t, dummy);
}
Este constructor llama al constructor de la clase base (StdSerializer) y no realiza ninguna operación específica.

Método serialize:

java
Copy code
@Override
public void serialize(ProductCli2 value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    // Código de serialización aquí
}
Este método se ejecuta cuando se intenta serializar una instancia de ProductCli2. Aquí está el detalle del código de serialización:

Se inicia la escritura del objeto JSON con gen.writeStartObject().
Se escriben los campos del objeto ProductCli2 utilizando los métodos gen.writeNumberField, gen.writeStringField, gen.writeBooleanField, etc.
Para el campo "category", se verifica si la categoría es nula y se escribe el campo correspondiente con gen.writeNullField o se escribe la categoría con gen.writeObjectFieldStart.
Se escribe el campo "expirationDate" con gen.writeObjectField.
Se calcula y se escribe el campo "daysExpired", que representa la cantidad de días que han pasado desde la fecha de vencimiento hasta ahora.
Se serializan los componentes utilizando el ComponentCli2JsonSerializer y se escribe el campo "components" con gen.writeFieldName y gen.writeRawValue.
Finalmente, se cierra el objeto JSON con gen.writeEndObject().
Este serializador está configurado para generar un formato JSON específico para la versión 1 de la entidad ProductCli2. Cada campo se escribe según la lógica establecida en este serializador.
* */
	private static final long serialVersionUID = -3706327488880784297L;

	public ProductCli2SlimV1JsonSerializer(Class<?> t, boolean dummy) {
		super(t, dummy);
	}

	@Override
	public void serialize(ProductCli2 value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeStringField("product", value.getProduct());
		gen.writeStringField("version", "v1");
		gen.writeBooleanField("stock", value.isStock());
		gen.writeNumberField("price", value.getPrice());
		
		if (value.getCategory() != null) {
			gen.writeObjectFieldStart("category");
			gen.writeNumberField("id", value.getCategory().getId());
			gen.writeStringField("category", value.getCategory().getCategory());
			gen.writeEndObject();
		} else {
			gen.writeNullField("category"); 
		}

		gen.writeObjectField("expirationDate", value.getExpirationDate());

		gen.writeNumberField("daysExpired", DAYS.between(Instant.ofEpochMilli(value.getExpirationDate().getTime())
				.atZone(ZoneId.systemDefault()).toLocalDateTime(), LocalDateTime.now()));

		String componentsStr = JsonUtiles
				.getObjectMapper(ComponentCli2.class, new ComponentCli2JsonSerializer(ComponentCli2.class, false), null)
				.writeValueAsString(value.getComponents());
		gen.writeFieldName("components");
		gen.writeRawValue(componentsStr);

		gen.writeEndObject();

	}

}



