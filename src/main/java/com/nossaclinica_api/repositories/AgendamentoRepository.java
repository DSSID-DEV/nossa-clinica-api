package com.nossaclinica_api.repositories;

import com.nossaclinica_api.models.entities.Agendamento;
import com.nossaclinica_api.repositories.queries.AgendamentoQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query(value = AgendamentoQueries.EXISTS_AGENDAMENTO, nativeQuery = true)
    boolean existsAgendamento(@Param("idCliente") Long idCliente,
                              @Param("idEspecialista") Long idEspecialista,
                              @Param("idEspecialidade") Long idEspecialidade,
                              @Param("idServico") Long idServico,
                              @Param("agendadoPara")LocalDate agendadoPara);

    @Query(value = AgendamentoQueries.BUSCAR_AGENDAMENTO_PARA_EDICAO, nativeQuery = true)
    Optional<?> buscarAgendamentoParaEdicao(Long idAgendamento);
}
