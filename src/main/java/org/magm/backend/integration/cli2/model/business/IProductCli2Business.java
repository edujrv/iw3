package org.magm.backend.integration.cli2.model.business;

import java.util.Date;
import java.util.List;

import org.magm.backend.integration.cli2.model.ProductCli2;
import org.magm.backend.integration.cli2.model.ProductCli2SlimView;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.NotFoundException;
/*
* Define métodos para listar productos expirados, obtener una vista delgada de productos, cargar un producto por su código, listar todos los productos, agregar un producto y agregar un producto externo a partir de datos en formato JSON.

Utiliza excepciones específicas de negocio (NotFoundException, FoundException y BusinessException) para manejar situaciones excepcionales.

Proporciona una abstracción de alto nivel para las operaciones relacionadas con productos en el contexto de CLI2.
* */
public interface IProductCli2Business {
	public List<ProductCli2> listExpired(Date date) throws BusinessException;
	public List<ProductCli2SlimView> listSlim() throws BusinessException;

	public ProductCli2 load(String codCli2) throws NotFoundException, BusinessException;
	public List<ProductCli2> list() throws BusinessException;
	public ProductCli2 add(ProductCli2 product) throws FoundException, BusinessException;
	public ProductCli2 addExternal(String json) throws FoundException, BusinessException;
}

