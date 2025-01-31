package com.nossaclinica_api.repositories.custom;

import com.nossaclinica_api.converters.ConverteAnaliseColposcopio;
import com.nossaclinica_api.models.dtos.DadosColposcopio;
import com.nossaclinica_api.repositories.queries.AnaliseColposcopioQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RepositoryExameColposcopioCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<DadosColposcopio> listarAchados(Long idAgendamento, List<Long> listAchados) {
        var sql = AnaliseColposcopioQueries.LISTAR_ACHADOS;
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("idAgendamento", idAgendamento);
        query.setParameter("achados", listAchados);
        return (List<DadosColposcopio>) query.getResultList().stream()
                .map(ConverteAnaliseColposcopio::paraDadosColpospio)
                .collect(Collectors.toList());
    }
}
