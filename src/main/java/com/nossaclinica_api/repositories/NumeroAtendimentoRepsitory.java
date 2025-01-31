package com.nossaclinica_api.repositories;

import com.nossaclinica_api.models.entities.NumeroAtendimento;
import com.nossaclinica_api.repositories.queries.NumeroAtendimentoQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface NumeroAtendimentoRepsitory extends JpaRepository<NumeroAtendimento, Long> {

    @Query(value = NumeroAtendimentoQueries.DO_ATENDIMENTO_DO_PARAMENTRO, nativeQuery = true)
    NumeroAtendimento buscarDoCliente(@Param("idCliente") Long idCliente,
                                      @Param("idEspecialista") Long idEspecialista,
                                      @Param("idEspecialidade") Long idEspecialidade,
                                      @Param("tipoAtendimento") String tipoAtendimento,
                                      @Param("dataAtendimento") LocalDate dataAtendimento);
}
