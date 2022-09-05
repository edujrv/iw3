package org.magm.backend.model.business;

import org.magm.backend.model.Factura;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IFacturaBusiness {
    public Factura load(long id) throws NotFoundException, BusinessException;

    public List<Factura> list() throws BusinessException;

    public Factura add(Factura factura) throws FoundException, BusinessException;

    public Factura update(Factura factura) throws NotFoundException, BusinessException;

    public void delete(long id) throws NotFoundException, BusinessException;

    public List<Factura> lista_anulada() throws BusinessException;

    public void anularFactura(long id) throws NotFoundException, BusinessException;
}
