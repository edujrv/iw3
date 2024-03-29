package org.magm.backend.model.business;

import java.util.List;
import java.util.Optional;

import org.magm.backend.model.Product;
import org.magm.backend.model.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
//La clase ProductBusiness en el paquete org.magm.backend.model.business es un componente de servicio (@Service) que implementa la interfaz IProductBusiness
//n resumen, la clase ProductBusiness implementa la lógica de negocio relacionada con la entidad Product. Proporciona métodos para realizar operaciones como cargar, listar, agregar, actualizar y eliminar productos, manejando diferentes excepciones para casos específicos. Este componente de servicio se utiliza para encapsular la lógica de acceso a datos y proporciona una capa de abstracción entre la capa de controladores y la capa de persistencia.


@Service
@Slf4j
public class ProductBusiness implements IProductBusiness {

	@Autowired
	private ProductRepository productDAO;
	
	@Override
	public Product load(long id) throws NotFoundException, BusinessException {
		Optional<Product> r;
		try {
			r=productDAO.findById(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
		if(r.isEmpty()) {
			throw NotFoundException.builder().message("No se encuentra el Producto id=" + id).build();
		}
			
		return r.get();
	}

	@Override
	public Product load(String product) throws NotFoundException, BusinessException {
		Optional<Product> r;
		try {
			r = productDAO.findByProduct(product);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
		if (r.isEmpty()) {
			throw NotFoundException.builder().message("No se encuentra el Producto '"+product+"'").build();
		}
		return r.get();
	}

	@Override
	public List<Product> list() throws BusinessException {
		try {
			return productDAO.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}

	}

	@Override
	public Product add(Product product) throws FoundException, BusinessException {
		try {
			load(product.getId());
			throw FoundException.builder().message("Se encuentró el Producto id=" + product.getId()).build();
		} catch (NotFoundException e) {
		}
		try {
			load(product.getProduct());
			throw FoundException.builder().message("Se encuentró el Producto '" + product.getProduct() +"'").build();
		} catch (NotFoundException e) {
		}

		try {
			return productDAO.save(product);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}

	}

	@Override
	public Product update(Product product) throws NotFoundException, BusinessException {
		load(product.getId());
		try {
			return productDAO.save(product);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}

	}

	@Override
	public void delete(long id) throws NotFoundException, BusinessException {
		load(id);
		try {
			 productDAO.deleteById(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}


	}

}
