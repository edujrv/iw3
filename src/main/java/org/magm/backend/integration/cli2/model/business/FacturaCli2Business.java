package org.magm.backend.integration.cli2.model.business;

import org.magm.backend.integration.cli2.model.FacturaCli2;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.NotFoundException;

import java.util.List;

public class FacturaCli2Business implements IFacturaCli2Business {

    @Override
    public FacturaCli2 load(String codCli2) throws NotFoundException, BusinessException {
        return null;
    }

    @Override
    public List<FacturaCli2> list() throws BusinessException {
        return null;
    }

    @Override
    public FacturaCli2 add(FacturaCli2 factura) throws FoundException, BusinessException {
        return null;
    }
}
