package org.magm.backend.model.business;

import org.magm.backend.model.Auditoria;
import org.magm.backend.model.Product;

import java.util.Date;
import java.util.List;

public interface IAuditoriaBusiness {

    public Auditoria load(long id) throws NotFoundException, BusinessException ;

    public List<Auditoria> list() throws BusinessException;

    Auditoria add(long id, String user, Date fecha, String operacion) throws FoundException, BusinessException;
}
