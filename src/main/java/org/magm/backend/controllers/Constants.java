package org.magm.backend.controllers;

/*
* La clase Constants define constantes que representan las rutas URL utilizadas en la aplicación. Aquí hay una descripción de las constantes definidas en esta clase:

Rutas principales:

URL_API: Representa la parte de la URL que indica la API (/api).
URL_API_VERSION: Representa la parte de la URL que indica la versión de la API (/v1).
URL_BASE: Es la combinación de URL_API y URL_API_VERSION, formando la ruta base de la API (/api/v1).
Rutas específicas:

URL_PRODUCTS: Representa la ruta para acceder a los productos (/api/v1/products).
URL_INTEGRATION: Representa la ruta de integración (/api/v1/integration).
URL_INTEGRATION_CLI1: Representa la ruta de integración para CLI1 (/api/v1/integration/cli1).
URL_INTEGRATION_CLI2: Representa la ruta de integración para CLI2 (/api/v1/integration/cli2).
URL_FACTURAS: Representa la ruta para acceder a las facturas en CLI2 (/api/v1/integration/cli2/facturas).
URL_DETALLES: Representa la ruta para acceder a los detalles en CLI2 (/api/v1/integration/cli2/detalles).
Rutas de autenticación:

URL_LOGIN: Representa la ruta para la autenticación (/api/v1/login).
URL_AUTHORIZATION: Representa la ruta para la autorización (/api/v1/authorization).
Estas constantes proporcionan una forma centralizada y mantenible de gestionar las rutas URL utilizadas en la aplicación, lo que facilita su mantenimiento y evita errores de escritura manual.
* */
public final class Constants {
	public static final String URL_API = "/api";
	public static final String URL_API_VERSION = "/v1";
	public static final String URL_BASE = URL_API + URL_API_VERSION;
	public static final String URL_PRODUCTS = URL_BASE + "/products";

	public static final String URL_INTEGRATION = URL_BASE + "/integration";
	public static final String URL_INTEGRATION_CLI1 = URL_INTEGRATION + "/cli1";
	public static final String URL_INTEGRATION_CLI2 = URL_INTEGRATION + "/cli2";

	public static final String URL_FACTURAS = URL_INTEGRATION_CLI2 + "/facturas";
	public static final String URL_DETALLES = URL_INTEGRATION_CLI2 + "/detalles";

	public static final String URL_LOGIN = URL_BASE + "/login";
	public static final String URL_AUTHORIZATION = URL_BASE + "/authorization";

}
