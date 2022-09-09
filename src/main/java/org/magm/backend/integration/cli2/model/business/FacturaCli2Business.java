package org.magm.backend.integration.cli2.model.business;

import lombok.extern.slf4j.Slf4j;
import org.magm.backend.integration.cli2.model.FacturaCli2;
import org.magm.backend.integration.cli2.model.persistence.FacturaCli2Repository;
import org.magm.backend.integration.cli2.model.persistence.ProductCli2Respository;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FacturaCli2Business implements IFacturaCli2Business{

    @Autowired(required = false)
    private FacturaCli2Repository facturaDAO;

    @Override
    public FacturaCli2 load(long id) throws NotFoundException, BusinessException {
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
            return facturaDAO.save(factura);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public FacturaCli2 update(FacturaCli2 factura) throws NotFoundException, BusinessException {
        load(factura.getId());
        try {
            return facturaDAO.save(factura);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public void delete(long id) throws NotFoundException, BusinessException {
        load(id);
        try {
            facturaDAO.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public List<FacturaCli2> lista_anulada() throws BusinessException {
        List<FacturaCli2> r;
        List<FacturaCli2> rf=null;
        try {
            r=facturaDAO.findAll();

            for (int i=0; i<r.size();i++){
                if(r.get(i).isAnulada()){
                    rf.add(r.get(i));
                }
            }
            return rf;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }
    @Override
    public void anularFactura(long id) throws NotFoundException, BusinessException {
        FacturaCli2 factura = load(id);
        factura.setAnulada(true);
        update(factura);
    }
}
