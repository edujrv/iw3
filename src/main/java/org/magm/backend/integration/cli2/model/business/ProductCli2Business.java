package org.magm.backend.integration.cli2.model.business;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.magm.backend.integration.cli2.model.ProductCli2;
import org.magm.backend.integration.cli2.model.ProductCli2JsonDeserializer;
import org.magm.backend.integration.cli2.model.ProductCli2SlimView;
import org.magm.backend.integration.cli2.model.persistence.ProductCli2Respository;
import org.magm.backend.model.business.*;
import org.magm.backend.util.JsonUtiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductCli2Business implements IProductCli2Business {

	@Autowired(required = false)
	private ProductCli2Respository productDAO;


	@Override
	public ProductCli2 load(String codCli2) throws NotFoundException, BusinessException {
		Optional<ProductCli2> r;
		try {
			r = productDAO.findOneByCodCli2(codCli2);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
		if (r.isEmpty()) {
			throw NotFoundException.builder().message("No se encuentra el Producto codCli2=" + codCli2).build();
		}
		return r.get();
	}

	@Override
	public List<ProductCli2> list() throws BusinessException {
		try {
			return productDAO.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
	}

	@Autowired(required = false)
	private IProductBusiness productBaseBusiness;

	@Override
	public ProductCli2 add(ProductCli2 product) throws FoundException, BusinessException {

		try {
			productBaseBusiness.load(product.getId());
			throw FoundException.builder().message("Se encontró el Producto id=" + product.getId()).build();
		} catch (NotFoundException e) {
		}

		if (productDAO.findOneByCodCli2(product.getCodCli2()).isPresent()) {
			throw FoundException.builder().message("Se encontró el Producto código=" + product.getCodCli2()).build();
		}


		try {
			return productDAO.save(product);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
	}

	@Autowired
	private ICategoryBusiness categoryBusiness;

	@Override
	public ProductCli2 addExternal(String json) throws FoundException, BusinessException {
		ObjectMapper mapper = JsonUtiles.getObjectMapper(ProductCli2.class,
				new ProductCli2JsonDeserializer(ProductCli2.class, categoryBusiness));
		ProductCli2 product = null;
		try {
			product = mapper.readValue(json, ProductCli2.class);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}

		return add(product);

	}

	@Override
	public List<ProductCli2> listExpired(Date date) throws BusinessException {
		try {
			return productDAO.findByExpirationDateBeforeOrderByExpirationDateDesc(date);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
	}

	@Override
	public List<ProductCli2SlimView> listSlim() throws BusinessException {
		try {
			return productDAO.findByOrderByPriceDesc();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}

	}

}

