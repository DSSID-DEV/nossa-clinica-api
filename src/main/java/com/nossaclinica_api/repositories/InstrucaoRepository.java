package com.nossaclinica_api.repositories;

import com.nossaclinica_api.models.entities.Instrucao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrucaoRepository extends JpaRepository<Instrucao, Long> {

    @Query(value = InstrucoesQueries.INSTRUCOES_DO_EXAME, nativeQuery = false)
    List<Instrucao> instrucoesDoExame(@Param("idExame") Long idExame);


}
