package org.magm.backend.model.business;
//La interfaz IAuditoriaBusiness en el paquete org.magm.backend.model.business define el contrato para la lógica de negocio relacionada con la entidad Auditoria.
//En resumen, la interfaz IAuditoriaBusiness establece un conjunto de métodos que deben ser implementados por las clases que proporcionan la lógica de negocio para la entidad Auditoria. Esta interfaz proporciona una abstracción clara de las operaciones relacionadas con auditorías y define las excepciones que pueden ser lanzadas durante estas operaciones.
import org.magm.backend.model.Auditoria;
import org.magm.backend.model.Product;

import java.util.Date;
import java.util.List;

public interface IAuditoriaBusiness {

    public Auditoria load(long id) throws NotFoundException, BusinessException ;

    public List<Auditoria> list(String user) throws BusinessException;

    Auditoria add(long id, String user, Date fecha, String operacion) throws FoundException, BusinessException;
}
