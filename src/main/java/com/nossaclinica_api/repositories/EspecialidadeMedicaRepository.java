package com.nossaclinica_api.repositories;

import com.nossaclinica_api.models.entities.EspecialidadeMedica;
import com.nossaclinica_api.repositories.queries.EspecialidadeMedicaQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspecialidadeMedicaRepository extends JpaRepository<EspecialidadeMedica, Long> {

    @Query(value = EspecialidadeMedicaQueries.LISTAR_ESPECIALIDADES_DO_ESPECIALISTA, nativeQuery = false)
    List<EspecialidadeMedica> listarEspecialidadesDoMedico(@Param("idEspecialista") Long idEspecialista);

    @Query(value = EspecialidadeMedicaQueries.BUSCAR_ESPECIALIDADE_MEDICA_EXISTENTE, nativeQuery = false)
    EspecialidadeMedica buscarEspecialidadeMedicaExistente(@Param("idEspecialista") Long idEspecialista,
                                                           @Param("idEspecialidade") Long idEspecialidade,
                                                           @Param("rqe") String rqe,
                                                           @Param("areaDeAtuacao") String areaDeAtuacao);

    @Query(value = EspecialidadeMedicaQueries.VERIFICAR_SE_HA_ESSA_ESPECIALIDADE_PARA_ESSE_ESPECIALISTA, nativeQuery = false)
    boolean existeEsteServicoParaEsseMedico(@Param("idEspecialista") Long idEspecialista,
                                            @Param("idEspecialidade") Long idEspecialidade,
                                            @Param("rqe") String rqe,
                                            @Param("areaDeAtuacao") String areaDeAtuacao);

}
