package com.nossaclinica_api.repositories;

import com.nossaclinica_api.models.entities.AnaliseColposcopio;
import com.nossaclinica_api.repositories.queries.AnaliseColposcopioQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExameColposcopioRepository extends JpaRepository<AnaliseColposcopio, Long> {
    @Query(AnaliseColposcopioQueries.LISTAR_ITENS_DE_ANALISE_COLPOSCOPIO)
    List<AnaliseColposcopio> listarItensDeAnaliseColposcopio();
}
