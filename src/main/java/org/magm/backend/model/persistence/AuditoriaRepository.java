package org.magm.backend.model.persistence;

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
