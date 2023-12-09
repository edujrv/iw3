package org.magm.backend.util;
//La clase StandartResponse en el paquete org.magm.backend.util es una clase de modelo que representa una respuesta estándar para la comunicación de la aplicación.
//En resumen, la clase StandartResponse encapsula la información común que puede estar presente en las respuestas de la aplicación, como mensajes, excepciones y estados HTTP. La información de desarrollo (como el stack trace) se incluye o excluye según la configuración de devInfoEnabled.
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StandartResponse {

	private String message;

	@JsonIgnore
	private Throwable ex;

	@JsonIgnore
	private HttpStatus httpStatus;

	public int getCode() {
		return httpStatus.value(); 
	}
	
	@JsonIgnore
	private boolean devInfoEnabled; 
	
	public String getDevInfo() {
		if(devInfoEnabled) {
			if(ex!=null) {
				return ExceptionUtils.getStackTrace(ex);
			} else {
				return "No stack trace";
			}
		} else {
			return null;
		}
	}
	
	public String getMessage() {
		if(message!=null)
			return message;
		if (ex!=null)
			return ex.getMessage();
		return null;
	}
}
