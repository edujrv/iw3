package org.magm.backend.controllers;

import org.magm.backend.model.Category;
import org.magm.backend.model.Product;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.ICategoryBusiness;
import org.magm.backend.model.business.IProductBusiness;
import org.magm.backend.model.business.NotFoundException;
import org.magm.backend.util.IStandartResponseBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
*La clase ProductRestController es un controlador de Spring que gestiona las operaciones relacionadas con los productos y categorías. Aquí hay una descripción de los métodos definidos en esta clase:

Operaciones sobre Productos:

list: Recupera la lista de todos los productos.
load: Recupera un producto por su ID o nombre.
add: Agrega un nuevo producto.
update: Actualiza la información de un producto existente.
delete: Elimina un producto por su ID.
Operaciones sobre Categorías:

listCategories: Recupera la lista de todas las categorías.
loadCategory: Recupera una categoría por su ID.
addCategory: Agrega una nueva categoría.
updateCategory: Actualiza la información de una categoría existente.
deleteCategory: Elimina una categoría por su ID.
Para cada operación, se manejan las excepciones de negocio (BusinessException, NotFoundException, FoundException) y se devuelven las respuestas adecuadas con el uso de ResponseEntity. Además, se utilizan las constantes definidas en la clase Constants para construir las rutas URL.

Este controlador sigue el enfoque RESTful al utilizar los métodos HTTP adecuados (GET, POST, PUT, DELETE) y manejar los recursos de manera jerárquica (productos y categorías). Además, utiliza anotaciones de Spring Security, como @PreAuthorize, para aplicar autorización basada en roles a los diferentes puntos finales.
* */
@RestController
@RequestMapping(Constants.URL_PRODUCTS)
public class ProductRestController extends BaseRestController {
	
	@Autowired
	private IStandartResponseBusiness response;
	
	@Autowired
	private IProductBusiness productBusiness;

	@GetMapping(value="", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> list() {
		try {
			return new ResponseEntity<>(productBusiness.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> load(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<>(productBusiness.load(id), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	

	@GetMapping(value="/by-name/{product}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> load(@PathVariable("product") String product) {
		try {
			return new ResponseEntity<>(productBusiness.load(product), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="")
	public ResponseEntity<?> add(@RequestBody Product product) {
		try {
			Product response=productBusiness.add(product);
			HttpHeaders responseHeaders=new HttpHeaders();
			responseHeaders.set("location",Constants.URL_PRODUCTS+"/"+response.getId());
			return new ResponseEntity<>( responseHeaders,HttpStatus.CREATED);
		} catch (FoundException e) {
			return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
		} catch (BusinessException e) {
			return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@PutMapping(value = "")
	public ResponseEntity<?> update(@RequestBody Product product) {
		try {
			productBusiness.update(product);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		try {
			productBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}


	
	// Categorías

		@Autowired
		private ICategoryBusiness categoryBusiness;

		@GetMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> listCategories() {
			try {
				return new ResponseEntity<>(categoryBusiness.list(), HttpStatus.OK);
			} catch (BusinessException e) {
				return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		@GetMapping(value = "/categories/{id}")
		public ResponseEntity<?> loadCategory(@PathVariable("id") long id) {
			try {
				return new ResponseEntity<>(categoryBusiness.load(id), HttpStatus.OK);
			} catch (BusinessException e) {
				return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (NotFoundException e) {
				return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
			}
		}

		@PostMapping(value = "/categories")
		public ResponseEntity<?> addCategory(@RequestBody Category category) {
			try {
				Category response = categoryBusiness.add(category);
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set("location", Constants.URL_PRODUCTS + "/categories/" + response.getId());
				return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
			} catch (BusinessException e) {
				return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (FoundException e) {
				return new ResponseEntity<>(response.build(HttpStatus.FOUND, e, e.getMessage()), HttpStatus.FOUND);
			}
		}

		@PutMapping(value = "/categories")
		public ResponseEntity<?> updateCategory(@RequestBody Category category) {
			try {
				categoryBusiness.update(category);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (BusinessException e) {
				return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (NotFoundException e) {
				return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
			}
		}

		@DeleteMapping(value = "/categories/{id}")
		public ResponseEntity<?> deleteCategory(@PathVariable("id") long id) {
			try {
				categoryBusiness.delete(id);
				return new ResponseEntity<String>(HttpStatus.OK);
			} catch (BusinessException e) {
				return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (NotFoundException e) {
				return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
			}
		}
	}

