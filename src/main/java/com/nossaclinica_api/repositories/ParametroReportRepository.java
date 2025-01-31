package com.nossaclinica_api.repositories;


import com.nossaclinica_api.models.entities.ParametroReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroReportRepository extends JpaRepository<ParametroReport, Long> {
    @Query(ParametroReportQueries.BUSCAR_PARAMETRO_PARA_TAMANHO_PAPEL)
    ParametroReport buscarParametroParaTamanhoDePapel(@Param("papel") String papel);
}
