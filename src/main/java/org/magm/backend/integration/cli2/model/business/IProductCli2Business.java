package org.magm.backend.integration.cli2.model.business;

import java.util.Date;
import java.util.List;

import org.magm.backend.integration.cli2.model.ProductCli2;
import org.magm.backend.integration.cli2.model.ProductCli2SlimView;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.NotFoundException;

public interface IProductCli2Business {
	public List<ProductCli2> listExpired(Date date) throws BusinessException;
	public List<ProductCli2SlimView> listSlim() throws BusinessException;

	public ProductCli2 load(String codCli2) throws NotFoundException, BusinessException;
	public List<ProductCli2> list() throws BusinessException;
	public ProductCli2 add(ProductCli2 product) throws FoundException, BusinessException;
	public ProductCli2 addExternal(String json) throws FoundException, BusinessException;
}

