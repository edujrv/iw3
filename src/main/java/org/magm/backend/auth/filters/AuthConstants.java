package org.magm.backend.auth.filters;
//La clase AuthConstants define algunas constantes relacionadas con la autenticación y los tokens JWT.
//Estas constantes son utilizadas en otros componentes del sistema, como los filtros de autenticación y autorización, para garantizar consistencia y facilitar la configuración. Por ejemplo, EXPIRATION_TIME determina cuánto tiempo será válido un token antes de expirar, y SECRET es la clave utilizada para firmar y verificar la autenticidad del token. El prefijo TOKEN_PREFIX se utiliza para indicar que el valor de Authorization es un token JWT.
public final class AuthConstants {
	public static final long EXPIRATION_TIME = (60 * 60 * 1000);
	public static final String SECRET = "HyLLzejPj@ynEf6LxUwM8T!z&vBHqZmf2T*N%y56ZKv3*Y8qt4";

	public static final String AUTH_HEADER_NAME = "Authorization";
	public static final String AUTH_PARAM_NAME = "authtoken";
	public static final String TOKEN_PREFIX = "Bearer ";
}
