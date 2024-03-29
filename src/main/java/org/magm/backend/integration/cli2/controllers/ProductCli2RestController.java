package org.magm.backend.integration.cli2.controllers;

import java.util.Calendar;
import java.util.Date;

import org.magm.backend.controllers.BaseRestController;
import org.magm.backend.controllers.Constants;
import org.magm.backend.integration.cli2.model.ProductCli2;
import org.magm.backend.integration.cli2.model.ProductCli2SlimV1JsonSerializer;
import org.magm.backend.integration.cli2.model.business.IProductCli2Business;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.NotFoundException;
import org.magm.backend.util.IStandartResponseBusiness;
import org.magm.backend.util.JsonUtiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
/*
* El controlador ProductCli2RestController maneja las operaciones relacionadas con los productos en el contexto de la integración cli2. Aquí hay un resumen de los métodos definidos en este controlador:

Listar Productos Expirados (/list-expired):

Ruta: GET /integration/cli2/products/list-expired
Descripción: Recupera la lista de productos expirados desde una fecha dada.
Parámetros de entrada:
since (opcional): Fecha a partir de la cual se deben buscar productos expirados.
slim (opcional): Versión delgada del producto (v1 por defecto).
Respuestas:
200 OK: Lista de productos expirados recuperada exitosamente.
500 INTERNAL SERVER ERROR: Si hay un error general en la operación.
Listar Todos los Productos (/):

Ruta: GET /integration/cli2/products
Descripción: Recupera la lista de todos los productos.
Respuestas:
200 OK: Lista de productos recuperada exitosamente.
500 INTERNAL SERVER ERROR: Si hay un error general en la operación.
Cargar Producto por Código (/{codCli2}):

Ruta: GET /integration/cli2/products/{codCli2}
Descripción: Recupera un producto por su código.
Parámetros de entrada: Código del producto (codCli2).
Respuestas:
200 OK: Producto recuperado exitosamente.
404 NOT FOUND: Si el producto no se encuentra.
500 INTERNAL SERVER ERROR: Si hay un error general en la operación.
Agregar Producto (/):

Ruta: POST /integration/cli2/products
Descripción: Agrega un nuevo producto.
Parámetros de entrada: Producto (ProductCli2).
Respuestas:
201 CREATED: Producto creado exitosamente.
404 NOT FOUND: Si se encuentra un problema (por ejemplo, ya existe el producto).
500 INTERNAL SERVER ERROR: Si hay un error general en la operación.
Agregar Producto Externo (/b2b):

Ruta: POST /integration/cli2/products/b2b
Descripción: Agrega un nuevo producto utilizando datos externos.
Parámetros de entrada: Datos externos del producto en el cuerpo de la solicitud.
Respuestas:
201 CREATED: Producto creado exitosamente.
404 NOT FOUND: Si se encuentra un problema (por ejemplo, ya existe el producto).
500 INTERNAL SERVER ERROR: Si hay un error general en la operación.
Estos son los métodos principales proporcionados por el controlador ProductCli2RestController en el contexto de la integración cli2.
* */
@Profile({"cli2","mysqldev"})
@RestController
@RequestMapping(Constants.URL_INTEGRATION_CLI2 + "/products")
public class ProductCli2RestController extends BaseRestController {

	@Autowired
	private IProductCli2Business productBusiness;

	@Autowired
	private IStandartResponseBusiness response;


	@GetMapping(value = "/list-expired", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listExpired(
			@RequestParam(name = "since", required = false, defaultValue = "1970-01-01 00:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date since,
			@RequestParam(name = "slim", required = false, defaultValue = "v0") String slimVersion) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(since);
			if (c.get(Calendar.YEAR) == 1970) {
				since = new Date();
			}
			StdSerializer<ProductCli2> ser = null;
			if (slimVersion.equalsIgnoreCase("v1")) {
				ser = new ProductCli2SlimV1JsonSerializer(ProductCli2.class, false);
			} else {
				return new ResponseEntity<>(productBusiness.listExpired(since), HttpStatus.OK);
			}
			String result = JsonUtiles.getObjectMapper(ProductCli2.class, ser, null)
					.writeValueAsString(productBusiness.listExpired(since));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (BusinessException | JsonProcessingException e) {
			return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> list() {
		try {
			return new ResponseEntity<>(productBusiness.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{codCli2}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loadByCode(@PathVariable("codCli2") String codCli2) {
		try {
			return new ResponseEntity<>(productBusiness.load(codCli2), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<?> add(@RequestBody ProductCli2 product) {
		try {
			ProductCli2 response = productBusiness.add(product);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constants.URL_INTEGRATION_CLI2 + "/products/" + response.getCodCli2());
			return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (FoundException e) {
			return new ResponseEntity<>(response.build(HttpStatus.FOUND, e, e.getMessage()), HttpStatus.FOUND);
		}
	}

	@PostMapping(value = "/b2b")
	public ResponseEntity<?> addExternal(HttpEntity<String> httpEntity) {
		try {
			ProductCli2 response = productBusiness.addExternal(httpEntity.getBody());
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constants.URL_INTEGRATION_CLI2 + "/products/" + response.getCodCli2());
			return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (FoundException e) {
			return new ResponseEntity<>(response.build(HttpStatus.FOUND, e, e.getMessage()), HttpStatus.FOUND);
		}
	}




}
