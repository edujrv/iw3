package org.magm.backend.integration.cli2.model.business;

import lombok.extern.slf4j.Slf4j;
import org.magm.backend.integration.cli2.model.DetalleFacturaCli2;
import org.magm.backend.integration.cli2.model.FacturaCli2;
import org.magm.backend.integration.cli2.model.ProductCli2;
import org.magm.backend.integration.cli2.model.persistence.DetalleFacturaCli2Repository;
import org.magm.backend.integration.cli2.model.persistence.ProductCli2Respository;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
//La clase DetalleFacturaCli2Business es una implementación de la interfaz IDetalleFacturaCli2Business y define la lógica de negocio relacionada con los detalles de factura en el contexto de CLI2
//Implementa la interfaz IDetalleFacturaCli2Business.
//
//Utiliza anotaciones de Spring como @Service y @Autowired para la inyección de dependencias.
//
//Define métodos para cargar y agregar detalles de factura.
//
//Maneja excepciones específicas de negocio (NotFoundException, FoundException y BusinessException).
//
//Se hace uso del logger (log) para registrar mensajes y errores.
@Service
@Slf4j
public class DetalleFacturaCli2Business implements IDetalleFacturaCli2Business{

    @Autowired(required = false)
    private DetalleFacturaCli2Repository detalleDAO;


    @Override
    public DetalleFacturaCli2 load(long id) throws NotFoundException, BusinessException {
        Optional<DetalleFacturaCli2> r;
//  TODO: Creo que todo se puede simplificar en esto solo
//        return  facturaDAO.findById(id).orElseThrow();

        try{
            r=detalleDAO.findById(id);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
        if(r.isEmpty()) {
            throw NotFoundException.builder().message("No se encuentra el detalle id=" + id).build();
        }
        return r.get();
    }

    @Override
    public DetalleFacturaCli2 add(DetalleFacturaCli2 detalleFactura) throws FoundException, BusinessException {
        /*
        double price = 0;
        price = detalleFactura.getProduct().getPrice() * detalleFactura.getCantidad();
        */
        try {
            load(detalleFactura.getId());
            throw FoundException.builder().message("Se encontró el detalle id=" + detalleFactura.getId()).build();
        } catch (NotFoundException e) {
        }
        try {
            load(detalleFactura.getId());
            throw FoundException.builder().message("Se encontró el detalle'" + detalleFactura.getId() +"'").build();
        } catch (NotFoundException e) {
        }

        try {
            return detalleDAO.save(detalleFactura);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }

    }
}
