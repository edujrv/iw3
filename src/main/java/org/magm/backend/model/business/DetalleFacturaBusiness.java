package org.magm.backend.model.business;

import org.magm.backend.model.DetalleFactura;

import java.util.List;

public class DetalleFacturaBusiness implements IDetalleFacturaBusiness{

    @Override
    public DetalleFactura load(long id) throws NotFoundException, BusinessException {
        return null;
    }

    @Override
    public List<DetalleFactura> list() throws BusinessException {
        return null;
    }

    @Override
    public DetalleFactura add(DetalleFactura detalleFactura) throws FoundException, BusinessException {
        return null;
    }

    @Override
    public DetalleFactura update(DetalleFactura detalleFactura) throws NotFoundException, BusinessException {
        return null;
    }

    @Override
    public void delete(long id) throws NotFoundException, BusinessException {

    }
}
