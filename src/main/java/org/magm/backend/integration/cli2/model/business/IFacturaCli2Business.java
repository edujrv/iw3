package org.magm.backend.integration.cli2.model.business;

import org.magm.backend.integration.cli2.model.FacturaCli2;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.NotFoundException;

import java.util.List;


public interface IFacturaCli2Business {
    public FacturaCli2 load(String codCli2) throws NotFoundException, BusinessException;
    public List<FacturaCli2> list() throws BusinessException;
    public FacturaCli2 add(FacturaCli2 factura) throws FoundException,BusinessException;

}


