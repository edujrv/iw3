package org.magm.backend.integration.cli1.controllers;

import org.magm.backend.controllers.BaseRestController;
import org.magm.backend.controllers.Constants;
import org.magm.backend.integration.cli1.model.ProductCli1;
import org.magm.backend.integration.cli1.model.business.IProductCli1Business;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.NotFoundException;
import org.magm.backend.util.IStandartResponseBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
* La clase ProductCli1RestController es un controlador de Spring MVC que maneja las operaciones relacionadas con los productos de la integración cli1. Aquí hay una descripción de la clase:

Anotaciones:

@Profile({"cli1","mysqldev"}): Esta anotación de perfil indica que este controlador está activo solo cuando se activa el perfil "cli1" o "mysqldev". Esto permite configurar diferentes conjuntos de controladores para diferentes perfiles de la aplicación.
Extendido de BaseRestController:

El controlador extiende la clase BaseRestController, que proporciona funcionalidades comunes relacionadas con la autenticación.
Inyección de Dependencias:

@Autowired IProductCli1Business productBusiness: Se inyecta una instancia de IProductCli1Business para manejar las operaciones comerciales relacionadas con los productos de cli1.
@Autowired IStandartResponseBusiness response: Se inyecta una instancia de IStandartResponseBusiness para construir respuestas estandarizadas en caso de excepciones comerciales.
Mapeo de Endpoints:

@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE): Mapea la URL relativa /integration/cli1/products para manejar solicitudes GET y devuelve la lista de productos.
@GetMapping(value = "/{codCli1}", produces = MediaType.APPLICATION_JSON_VALUE): Mapea la URL relativa /integration/cli1/products/{codCli1} para manejar solicitudes GET y devuelve un producto específico según el código de cli1.
@PostMapping(value = ""): Mapea la URL relativa /integration/cli1/products para manejar solicitudes POST y agrega un nuevo producto.
@PostMapping(value = "/b2b"): Mapea la URL relativa /integration/cli1/products/b2b para manejar solicitudes POST y agrega un nuevo producto externo.
Método list:

Retorna la lista de productos cli1 en formato JSON.
Método loadByCode:

Obtiene y retorna un producto cli1 específico según el código proporcionado en la URL.
Método add:

Agrega un nuevo producto cli1 y retorna la ubicación del recurso creado en los encabezados de respuesta.
Método addExternal:

Agrega un nuevo producto externo y retorna la ubicación del recurso creado en los encabezados de respuesta.
En general, este controlador maneja operaciones CRUD (Crear, Leer y Actualizar) relacionadas con los productos de la integración cli1.
* */
@Profile({"cli1","mysqldev"})
@RestController
@RequestMapping(Constants.URL_INTEGRATION_CLI1 + "/products")
public class ProductCli1RestController extends BaseRestController {

	@Autowired
	private IProductCli1Business productBusiness;

	@Autowired
	private IStandartResponseBusiness response;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> list() {
		try {
			return new ResponseEntity<>(productBusiness.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{codCli1}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loadByCode(@PathVariable("codCli1") String codCli1) {
		try {
			return new ResponseEntity<>(productBusiness.load(codCli1), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<?> add(@RequestBody ProductCli1 product) {
		try {
			ProductCli1 response = productBusiness.add(product);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constants.URL_INTEGRATION_CLI1 + "/products/" + response.getCodCli1());
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
			ProductCli1 response = productBusiness.addExternal(httpEntity.getBody());
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constants.URL_INTEGRATION_CLI1 + "/products/" + response.getCodCli1());
			return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (FoundException e) {
			return new ResponseEntity<>(response.build(HttpStatus.FOUND, e, e.getMessage()), HttpStatus.FOUND);
		}
	}


}
