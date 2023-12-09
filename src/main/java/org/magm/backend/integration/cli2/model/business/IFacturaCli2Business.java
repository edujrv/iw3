package org.magm.backend.integration.cli2.model.business;

import org.magm.backend.integration.cli2.model.FacturaCli2;
import org.magm.backend.integration.cli2.model.IFacturaCli2SlimView;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.NotFoundException;

import java.util.List;
/*
*Define métodos para cargar, listar, agregar, actualizar y eliminar facturas, así como para anular y desanular facturas por su identificador.

Proporciona operaciones para listar facturas no anuladas, cargar todas las facturas, cargar una factura por su identificador, eliminar facturas por su número, listar vistas slim de facturas, listar vistas V2 de facturas y obtener una vista V2 de factura por su número.

Utiliza excepciones específicas de negocio (NotFoundException, FoundException y BusinessException) para manejar situaciones excepcionales.

Esta interfaz ofrece una abstracción de alto nivel para las operaciones relacionadas con las facturas en el contexto de CLI2
* */

public interface IFacturaCli2Business {
    FacturaCli2 load(long id) throws NotFoundException, BusinessException;

    List<FacturaCli2> list() throws BusinessException;

    FacturaCli2 add(FacturaCli2 factura) throws FoundException, BusinessException;

    FacturaCli2 update(FacturaCli2 factura) throws NotFoundException, BusinessException;

    void delete(long id) throws NotFoundException, BusinessException;

    List<FacturaCli2> listaNoAnulada() throws BusinessException;

    void anularFactura(long id) throws NotFoundException, BusinessException;

    public void desAnularFactura(long id) throws NotFoundException, BusinessException;

    public List<FacturaCli2> loadAll() throws NotFoundException, BusinessException;

    public FacturaCli2 loadId(long id) throws NotFoundException, BusinessException;

    public void deleteByNumero(long numero) throws NotFoundException, BusinessException;

    List<IFacturaCli2SlimView> listV2() throws BusinessException;

    public List<IFacturaCli2SlimView> listSlim() throws BusinessException;
    public List<Integer> idFacturaPorProducto (long id) throws BusinessException;

    public IFacturaCli2SlimView findOneByNumeroV2(long numero) throws BusinessException;


}

/*
agregar
quitar
anular (por numero, id)
habilitar
modificar
mostrar anuladas

*/