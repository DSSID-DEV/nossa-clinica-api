package com.nossaclinica_api.services;

import com.nossaclinica_api.models.entities.Dependente;
import com.nossaclinica_api.repositories.queries.ClienteQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryDependente extends JpaRepository<Dependente, Long> {
    @Query(value = ClienteQueries.VERIFICAR_TITULARIDADE, nativeQuery = true)
    boolean verificarTitularidade(@Param("idCliente") Long idCliente);
}
