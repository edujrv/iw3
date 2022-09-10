package org.magm.backend.integration.cli2.model.business;

import org.magm.backend.integration.cli2.model.DetalleFacturaCli2;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.NotFoundException;

public interface IDetalleFacturaCli2Business {
    DetalleFacturaCli2 add(DetalleFacturaCli2 detalleFactura) throws FoundException, BusinessException;

    public DetalleFacturaCli2 load(long id) throws NotFoundException, BusinessException;

}
