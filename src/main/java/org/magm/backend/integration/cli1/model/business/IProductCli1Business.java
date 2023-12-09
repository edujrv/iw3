package org.magm.backend.integration.cli1.model.business;
//La interfaz IProductCli1Business define las operaciones de negocio relacionadas con la entidad ProductCli1
//Esta interfaz proporciona una abstracción para la lógica de negocio relacionada con la gestión de productos en el contexto de la integración cli1. Implementar esta interfaz permite definir las operaciones específicas para interactuar con la entidad ProductCli1 y manejar excepciones personalizadas relacionadas con la lógica de negocio.
import java.util.List;

import org.magm.backend.integration.cli1.model.ProductCli1;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.NotFoundException;

public interface IProductCli1Business {

	public ProductCli1 load(String codCli1) throws NotFoundException, BusinessException;
	public List<ProductCli1> list() throws BusinessException;
	public ProductCli1 add(ProductCli1 product) throws FoundException, BusinessException;
	public ProductCli1 addExternal(String json) throws FoundException, BusinessException;
	
}

