package org.magm.backend.model.business;

import org.magm.backend.model.Factura;
import org.magm.backend.model.persistence.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FacturaBusiness implements IFacturaBusiness{

    @Autowired
    private FacturaRepository facturaDAO;

    @Override
    public Factura load(long id) throws NotFoundException, BusinessException {
        Optional<Factura> r;
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
    public List<Factura> list() throws BusinessException {
        try {
            return facturaDAO.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public Factura add(Factura factura) throws FoundException, BusinessException {
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
    public Factura update(Factura factura) throws NotFoundException, BusinessException {
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
    public List<Factura> lista_anulada() throws BusinessException{
        List<Factura> r;
        List<Factura> rf=null;
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
    public void anularFactura(long id) throws BusinessException, NotFoundException {
        Factura factura = load(id);
        factura.setAnulada(true);
        update(factura);
    }
}
