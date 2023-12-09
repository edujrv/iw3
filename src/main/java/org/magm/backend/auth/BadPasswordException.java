package org.magm.backend.auth;

import lombok.Builder;
import lombok.NoArgsConstructor;
//La clase BadPasswordException es una excepción específica que se utiliza para representar situaciones en las que se produce un error relacionado con la contraseña, como una contraseña incorrecta durante un intento de cambio de contraseña.
//Esta excepción puede ser lanzada en el código cuando se detecta un problema relacionado con contraseñas y proporciona información adicional a través del mensaje de la excepción y, opcionalmente, una causa subyacente. La capacidad de especificar una causa puede ser útil para rastrear la raíz del problema.
@NoArgsConstructor
public class BadPasswordException extends Exception {

	private static final long serialVersionUID = -8582277206660722157L;

	@Builder
	public BadPasswordException(String message, Throwable ex) {
		super(message, ex);
	}
	@Builder
	public BadPasswordException(String message) {
		super(message);
	}
	@Builder
	public BadPasswordException(Throwable ex) {
		super(ex.getMessage(), ex);
	}
}

