package org.magm.backend.model.business;

import lombok.extern.slf4j.Slf4j;
import org.magm.backend.model.Auditoria;
import org.magm.backend.model.persistence.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AuditoriaBusiness implements IAuditoriaBusiness{
    @Autowired(required = false)
    private AuditoriaRepository auditoriaDAO;

    @Override
    public Auditoria load(long id) throws NotFoundException, BusinessException {
        Optional<Auditoria> r;
        try {
            r=auditoriaDAO.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
        if(r.isEmpty()) {
            throw NotFoundException.builder().message("No se encuentra el auditaje=" + id).build();
        }

        return r.get();

    }

    @Override
    public List<Auditoria> list() throws BusinessException{
        try {
            return auditoriaDAO.auditoriaUsuario("UsuarioXdefecto1");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public Auditoria add(long numero, String user, Date fecha, String operacion) throws FoundException, BusinessException {


        Auditoria auditoria = Auditoria.builder()
                                        .id_factura(numero)
                                        .user(user)
                                        .fecha(fecha)
                                        .operacion(operacion)
                                        .build();

        return auditoriaDAO.save(auditoria);
    }
}