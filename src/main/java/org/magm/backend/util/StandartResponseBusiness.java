package org.magm.backend.util;
//La clase StandartResponseBusiness en el paquete org.magm.backend.util es una implementación de la interfaz IStandartResponseBusiness.
//En resumen, la clase StandartResponseBusiness proporciona una implementación del método build que construye objetos StandartResponse con la información proporcionada. La propiedad devInfoEnabled se utiliza para determinar si se incluyen detalles de desarrollo en la respuesta estándar.
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class StandartResponseBusiness implements IStandartResponseBusiness {

	@Value("${dev.info.enabled:false}")
	private boolean devInfoEnabled; 
	
	@Override
	public StandartResponse build(HttpStatus httpStatus, Throwable ex, String message) {
		StandartResponse sr=new StandartResponse();
		sr.setDevInfoEnabled(devInfoEnabled);
		sr.setMessage(message);
		sr.setHttpStatus(httpStatus);
		sr.setEx(ex);
		return sr;
	}

}

