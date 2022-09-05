package org.magm.backend.integration.cli2.model.business;

import org.magm.backend.integration.cli2.model.ProductCli2;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.NotFoundException;

import java.util.List;

public class ProductCli2Business implements IProductCLi2Business{
    @Override
    public ProductCli2 load(String codCli2) throws NotFoundException, BusinessException {
        return null;
    }

    @Override
    public List<ProductCli2> list() throws BusinessException {
        return null;
    }

    @Override
    public ProductCli2 add(ProductCli2 product) throws FoundException, BusinessException {
        return null;
    }
}
