package org.magm.backend.model.business;

import lombok.Builder;
import lombok.NoArgsConstructor;
//La clase NotFoundException en el paquete org.magm.backend.model.business es una excepción personalizada que extiende de la clase base Exception
//En resumen, la clase NotFoundException proporciona constructores convenientes para crear instancias de la excepción con mensajes y causas específicas. Puede utilizarse para señalar que no se ha encontrado un recurso, como un producto en el caso de la lógica de negocio en el paquete org.magm.backend.model.business.
@NoArgsConstructor
public class NotFoundException extends Exception {

	private static final long serialVersionUID = -8582277206660722157L;

	@Builder
	public NotFoundException(String message, Throwable ex) {
		super(message, ex);
	}

	@Builder
	public NotFoundException(String message) {
		super(message);
	}

	@Builder
	public NotFoundException(Throwable ex) {
		super(ex.getMessage(), ex);
	}
}
