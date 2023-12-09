package org.magm.backend.model.business;
//La clase FoundException en el paquete org.magm.backend.model.business es una excepción que puede ser lanzada en la capa de lógica de negocio cuando se encuentra un recurso (por ejemplo, al intentar agregar un nuevo elemento que ya existe)
//En resumen, la clase FoundException proporciona una forma de representar una excepción específica para casos donde se encuentra un recurso cuando no se esperaba. Esta excepción puede ser utilizada para manejar situaciones en las que se intenta agregar un elemento que ya existe en el sistema.
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FoundException extends Exception {

	private static final long serialVersionUID = -8582277206660722157L;

	@Builder
	public FoundException(String message, Throwable ex) {
		super(message, ex);
	}
	@Builder
	public FoundException(String message) {
		super(message);
	}
	@Builder
	public FoundException(Throwable ex) {
		super(ex.getMessage(), ex);
	}
}
