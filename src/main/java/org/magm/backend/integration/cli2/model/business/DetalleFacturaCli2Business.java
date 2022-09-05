package org.magm.backend.integration.cli2.model.business;

import org.magm.backend.integration.cli2.model.DetalleFacturaCli2;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.NotFoundException;

import java.util.List;

public class DetalleFacturaCli2Business implements IDetalleFacturaCli2Business{
    @Override
    public DetalleFacturaCli2 load(String codCli2) throws NotFoundException, BusinessException {
        return null;
    }

    @Override
    public List<DetalleFacturaCli2> list() throws BusinessException {
        return null;
    }

    @Override
    public DetalleFacturaCli2 add(DetalleFacturaCli2 detalleFactura) throws FoundException, BusinessException {
        return null;
    }
}
