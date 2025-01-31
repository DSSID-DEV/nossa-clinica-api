package com.nossaclinica_api.repositories;

import com.nossaclinica_api.models.entities.Especialidade;
import com.nossaclinica_api.repositories.queries.EspecialidadeQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {

    @Query(value = EspecialidadeQueries.BUSCAR_POR_NOME, nativeQuery = false)
    boolean existsEspecialidade(String descricao);
}
