package org.magm.backend.model.business;

import java.util.List;

import org.magm.backend.model.Product;
//La interfaz IProductBusiness en el paquete org.magm.backend.model.business define el contrato para la lógica de negocio relacionada con la entidad Product
//En resumen, la interfaz IProductBusiness establece un conjunto de métodos que deben ser implementados por las clases que proporcionan la lógica de negocio para la entidad Product. Esta interfaz proporciona una abstracción clara de las operaciones relacionadas con productos y define las excepciones que pueden ser lanzadas durante estas operaciones.
public interface IProductBusiness {	
	public Product load(long id) throws NotFoundException, BusinessException;
	
	public Product load(String product) throws NotFoundException, BusinessException;
	
	public List<Product> list() throws BusinessException;

	public Product add(Product product) throws FoundException, BusinessException;

	public Product update(Product product) throws NotFoundException, BusinessException;

	public void delete(long id) throws NotFoundException, BusinessException;

}	
