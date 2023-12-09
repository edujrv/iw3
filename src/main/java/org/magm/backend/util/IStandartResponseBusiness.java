package org.magm.backend.util;
//La interfaz IStandartResponseBusiness en el paquete org.magm.backend.util define un método build que debe ser implementado por las clases que la implementen. Aquí hay una descripción de la interfaz:
//En resumen, la interfaz IStandartResponseBusiness define un método para la construcción de respuestas estándar, permitiendo así la implementación de lógica específica en las clases que la implementen.
import org.springframework.http.HttpStatus;

public interface IStandartResponseBusiness {
	public StandartResponse build(HttpStatus httpStatus, Throwable ex, String message);

}
