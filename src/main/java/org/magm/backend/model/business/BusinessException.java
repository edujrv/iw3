package org.magm.backend.model.business;

import lombok.Builder;
import lombok.NoArgsConstructor;
//La clase BusinessException en el paquete org.magm.backend.model.business es una excepción personalizada que extiende la clase Exception. Esta clase se utiliza para representar excepciones generales relacionadas con la lógica de negocio en la aplicación.
//En resumen, la clase BusinessException es una excepción personalizada que puede contener un mensaje descriptivo y/o una causa (Throwable). Se utiliza para encapsular errores generales relacionados con la lógica de negocio en la aplicación. La anotación @Builder permite la construcción fluida de instancias de esta excepción.
@NoArgsConstructor
public class BusinessException extends Exception {

	private static final long serialVersionUID = -8582277206660722157L;

	@Builder
	public BusinessException(String message, Throwable ex) {
		super(message, ex);
	}
	@Builder
	public BusinessException(String message) {
		super(message);
	}
	@Builder
	public BusinessException(Throwable ex) {
		super(ex.getMessage(), ex);
	}


}
