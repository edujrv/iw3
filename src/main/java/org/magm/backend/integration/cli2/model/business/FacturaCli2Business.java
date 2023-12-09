package org.magm.backend.integration.cli2.model.business;

import lombok.extern.slf4j.Slf4j;
import org.magm.backend.controllers.BaseRestController;
import org.magm.backend.integration.cli2.model.FacturaCli2;
import org.magm.backend.integration.cli2.model.IFacturaCli2SlimView;
import org.magm.backend.integration.cli2.model.persistence.FacturaCli2Repository;
import org.magm.backend.model.Auditoria;
import org.magm.backend.model.DetalleFactura;
import org.magm.backend.model.business.AuditoriaBusiness;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.NotFoundException;
import org.magm.backend.model.persistence.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//La clase FacturaCli2Business es una implementación de la interfaz IFacturaCli2Business y define la lógica de negocio relacionada con las facturas en el contexto de CLI2.
//Implementa la interfaz IFacturaCli2Business.
//
//Utiliza anotaciones de Spring como @Service y @Autowired para la inyección de dependencias.
//
//Define métodos para cargar, listar, agregar, actualizar y eliminar facturas.
//
//Maneja excepciones específicas de negocio (NotFoundException, FoundException y BusinessException).
//
//Contiene métodos para realizar operaciones específicas como anular o desanular facturas, obtener una lista de facturas no anuladas, etc.
//
//Se hace uso del logger (log) para registrar mensajes y errores.
@Service
@Slf4j
public class FacturaCli2Business extends BaseRestController implements IFacturaCli2Business{

    @Autowired(required = false)
    private FacturaCli2Repository facturaDAO;

    @Autowired(required = false)
    private AuditoriaBusiness auditoriaDAO;

    @Override
    public FacturaCli2 load(long numero) throws NotFoundException, BusinessException {
        Optional<FacturaCli2> r;
//  TODO: Creo que todo se puede simplificar en esto solo
//        return  facturaDAO.findById(id).orElseThrow();

        try{
            r=facturaDAO.findFacturaCli2ByNumero(numero);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
        if(r.isEmpty()) {
            throw NotFoundException.builder().message("No se encuentra la Factura id=" + numero).build();
        }
        return r.get();
    }

    @Override
    public FacturaCli2 loadId(long id) throws NotFoundException, BusinessException {
        Optional<FacturaCli2> r;
//  TODO: Creo que todo se puede simplificar en esto solo
//        return  facturaDAO.findById(id).orElseThrow();

        try{
            r=facturaDAO.findById(id);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
        if(r.isEmpty()) {
            throw NotFoundException.builder().message("No se encuentra la Factura id=" + id).build();
        }
        return r.get();
    }

    @Override
    public List<FacturaCli2> list() throws BusinessException {
        try {
            return facturaDAO.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public FacturaCli2 add(FacturaCli2 factura) throws FoundException, BusinessException {

        double price = 0;
        //
        for (DetalleFactura item : factura.getDetallesFactura()) {
            price += item.getCantidad() * item.getPrice();
        }
        /*
        for (DetalleFactura item : factura.getDetallesFactura()) {
            price += item.getPrice();
        }
        */

        factura.setPrice(price);

        try {
            load(factura.getId());
            throw FoundException.builder().message("Se encontró la factura id=" + factura.getId()).build();
        } catch (NotFoundException e) {
        }
        try {
            load(factura.getNumero());
            throw FoundException.builder().message("Se encontró  la factura'" + factura.getNumero() +"'").build();
        } catch (NotFoundException e) {
        }

        try {
            FacturaCli2 facturaCli2 = facturaDAO.save(factura);
            //String user = getUserLogged().getAuthorities().toString();
            //auditoriaDAO.add(facturaCli2.getId(),user, factura.getFechaEmision(), "ALTA");
            return facturaCli2;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }

    }

    @Override
    public FacturaCli2 update(FacturaCli2 factura) throws NotFoundException, BusinessException {

//        load(factura.getNumero());


        FacturaCli2 facturaCli2 = load(factura.getNumero());
        delete(facturaCli2.getId());
        /*
            try {
                return facturaDAO.save(factura);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw BusinessException.builder().ex(e).build();
            }
        */

        try {
            return facturaDAO.save(factura);
            //FacturaCli2 facturaCli21Guardar = facturaDAO.save(factura);
            //String user = getUserLogged().getAuthorities().toString();
            //auditoriaDAO.add(facturaCli21Guardar.getId(),user, factura.getFechaEmision(), "MODIFICACION");
            //return facturaCli21Guardar;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public void delete(long id) throws NotFoundException, BusinessException {
        FacturaCli2 factura = loadId(id);
        /*

            loadId(id);
            try {
                facturaDAO.deleteById(id);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw BusinessException.builder().ex(e).build();
            }

        */
        try {
            facturaDAO.deleteById(id);
            //String user = getUserLogged().getAuthorities().toString();
            //auditoriaDAO.add(factura.getId(),user, factura.getFechaEmision(), "BAJA");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public void deleteByNumero(long numero) throws NotFoundException, BusinessException {
        FacturaCli2 factura = load(numero);
        /*
            FacturaCli2 f = load(numero);
            try {
                facturaDAO.deleteById(f.getId());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw BusinessException.builder().ex(e).build();
            }
        */
        try {
            facturaDAO.deleteById(factura.getId());
            //String user = getUserLogged().getAuthorities().toString();
            //auditoriaDAO.add(factura.getId(),user, factura.getFechaEmision(), "BAJA");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public List<FacturaCli2> listaNoAnulada() throws BusinessException {
//        List<FacturaCli2> facturasCLi2;

        try {
//            facturasCLi2 = facturaDAO.findFacturaCli2ByAnuladaIsFalse();
            return facturaDAO.findFacturaCli2ByAnuladaIsFalse();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }
    @Override
    public void anularFactura(long id) throws NotFoundException, BusinessException {

        try{
            facturaDAO.anularfactura(id);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }

        /*
        FacturaCli2 factura = facturaDAO.findFacturaCli2ByNumero(id).orElseThrow();
        factura.setAnulada(true);
        update(factura);
        */

    }

    @Override
    public void desAnularFactura(long id) throws NotFoundException, BusinessException {

        try{
            facturaDAO.desAnularFactura(id);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
        /*
        FacturaCli2 factura = facturaDAO.findFacturaCli2ByNumero(numero).orElseThrow();
        factura.setAnulada(false);
        update(factura);
         */
    }

    @Override
    public List<FacturaCli2> loadAll() throws NotFoundException, BusinessException {
        List<FacturaCli2> r;

        try{
            r=facturaDAO.findAll();
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
//        if(r.isEmpty()) {
//            throw NotFoundException.builder().message("No se encuentra el listado de facturas").build();
//        }
        return r;
    }

    @Override
    public List<IFacturaCli2SlimView> listV2() throws BusinessException{
        List<IFacturaCli2SlimView> facturaCli2List;

        try{
            facturaCli2List = facturaDAO.findAllV2();
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }

        return facturaCli2List;
    }

    @Override
    public List<Integer> idFacturaPorProducto (long id) throws BusinessException{
        List<Integer> ids;

        try{
            ids = facturaDAO.idDeFacturasdeUnProducto(id);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }

        return ids;
    }

    @Override
    public IFacturaCli2SlimView findOneByNumeroV2(long numero) throws BusinessException {
        try {
            return facturaDAO.findByNumero(numero);
        }catch (Exception e){
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public List<IFacturaCli2SlimView> listSlim() throws BusinessException {
        return null;
    }
}
