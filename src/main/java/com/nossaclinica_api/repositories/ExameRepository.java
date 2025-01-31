package com.nossaclinica_api.repositories;


import com.nossaclinica_api.models.entities.Exame;
import com.nossaclinica_api.repositories.queries.ExameQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Long> {

    @Query(value = ExameQueries.EXISTS_EXAME, nativeQuery = false)
    boolean existsExame(String material, String descricao, String sigla, String meioDeColeta);
}
