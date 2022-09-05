package org.magm.backend.integration.cli2.model.business;

import org.magm.backend.integration.cli2.model.ProductCli2;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.NotFoundException;

import java.util.List;

public interface IProductCLi2Business {
    public ProductCli2 load(String codCli2) throws NotFoundException, BusinessException;
    public List<ProductCli2> list() throws BusinessException;
    public ProductCli2 add(ProductCli2 product) throws FoundException,BusinessException;

}
