package org.magm.backend.integration.cli2.model.business;

import org.magm.backend.integration.cli2.model.FacturaCli2;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IFacturaCli2Business {
    FacturaCli2 load(long id) throws NotFoundException, BusinessException;

    List<FacturaCli2> list() throws BusinessException;

    FacturaCli2 add(FacturaCli2 factura) throws FoundException, BusinessException;

    FacturaCli2 update(FacturaCli2 factura) throws NotFoundException, BusinessException;

    void delete(long id) throws NotFoundException, BusinessException;

    List<FacturaCli2> lista_anulada() throws BusinessException;

    void anularFactura(long numero) throws NotFoundException, BusinessException;

    public void desAnularFactura(long numero) throws NotFoundException, BusinessException;

    public List<FacturaCli2> loadAll() throws NotFoundException, BusinessException;

    public FacturaCli2 loadId(long id) throws NotFoundException, BusinessException;
}

/*
agregar
quitar
anular (por numero, id)
habilitar
modificar
mostrar anuladas

*/