package com.nossaclinica_api.repositories;

import com.nossaclinica_api.models.entities.ServicosMedico;
import com.nossaclinica_api.repositories.queries.ServicoMedicoQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ServicoMedicoRepository extends JpaRepository<ServicosMedico, Long> {

    @Query(value = ServicoMedicoQueries.RELACIONAR_SERVICOS_DO_MEDICO)
    List<ServicosMedico> listarServicosDoMedico(@Param("idEspecialista") Long idEspecialista);


    @Query(value = ServicoMedicoQueries.EXIST_SERVICO_MEDICO, nativeQuery = false)
    boolean existsServicoMedico(@Param("idEspecialista") Long idEspecialista, @Param("idServico") Long idServico, @Param("descricao") String descricao);
}
