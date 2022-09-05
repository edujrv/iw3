package org.magm.backend.model.business;

import org.magm.backend.model.DetalleFactura;

import java.util.List;

public interface IDetalleFacturaBusiness {
    public DetalleFactura load(long id) throws NotFoundException, BusinessException;

    public List<DetalleFactura> list() throws BusinessException;

    public DetalleFactura add(DetalleFactura detalleFactura) throws FoundException, BusinessException;

    public DetalleFactura update(DetalleFactura detalleFactura) throws NotFoundException, BusinessException;

    public void delete(long id) throws NotFoundException, BusinessException;
}
