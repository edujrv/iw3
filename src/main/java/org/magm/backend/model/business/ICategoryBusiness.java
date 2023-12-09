package org.magm.backend.model.business;
//La interfaz ICategoryBusiness en el paquete org.magm.backend.model.business define el contrato para la lógica de negocio relacionada con la entidad Category
//En resumen, la interfaz ICategoryBusiness establece un conjunto de métodos que deben ser implementados por las clases que proporcionan la lógica de negocio para la entidad Category. Esta interfaz proporciona una abstracción clara de las operaciones relacionadas con categorías y define las excepciones que pueden ser lanzadas durante estas operaciones.
import java.util.List;

import org.magm.backend.model.Category;

public interface ICategoryBusiness {

	public Category load(long id) throws NotFoundException, BusinessException;

	public Category load(String category) throws NotFoundException, BusinessException;

	public List<Category> list() throws BusinessException;

	public Category add(Category category) throws FoundException, BusinessException;

	public Category update(Category category) throws NotFoundException, BusinessException;

	public void delete(long id) throws NotFoundException, BusinessException;

}
