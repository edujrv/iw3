package org.magm.backend.model.persistence;
//La interfaz AuditoriaRepository en el paquete org.magm.backend.model.persistence extiende JpaRepository y proporciona métodos para realizar operaciones de persistencia relacionadas con la entidad Auditoria
//En resumen, la interfaz AuditoriaRepository proporciona métodos estándar de CRUD (Create, Read, Update, Delete) gracias a la extensión de JpaRepository, y además incluye un método personalizado para buscar auditorías por nombre de usuario utilizando una consulta SQL nativa. Este repositorio se utiliza para interactuar con la tabla "auditoria" en la base de datos.
import org.magm.backend.model.Auditoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {

    @Query(value = "SELECT * FROM auditoria WHERE user = ?", nativeQuery = true)
    public List<Auditoria> auditoriaUsuario(String user);

}
