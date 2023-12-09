package org.magm.backend.integration.cli2.model.business;
//La interfaz IDetalleFacturaCli2Business define las operaciones de negocio relacionadas con los detalles de factura en el contexto de CLI2.
//Define métodos para agregar y cargar detalles de factura.
//
//Utiliza excepciones específicas de negocio (NotFoundException, FoundException y BusinessException) para manejar situaciones excepcionales.
//
//Esta interfaz ofrece una abstracción de alto nivel para las operaciones relacionadas con los detalles de factura en el contexto de CLI2.
import org.magm.backend.integration.cli2.model.DetalleFacturaCli2;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.NotFoundException;

public interface IDetalleFacturaCli2Business {
    DetalleFacturaCli2 add(DetalleFacturaCli2 detalleFactura) throws FoundException, BusinessException;

    public DetalleFacturaCli2 load(long id) throws NotFoundException, BusinessException;

}
