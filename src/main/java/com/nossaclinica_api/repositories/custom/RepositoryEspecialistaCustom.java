package com.nossaclinica_api.repositories.custom;

import com.nossaclinica_api.models.entities.Especialista;
import com.nossaclinica_api.repositories.queries.EspecialistaQueries;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositoryEspecialistaCustom {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Especialista> findAllAtivos(String dadosDaPesquisa, boolean ativo) {
        Map<String, Object> parametros = new HashMap<>();
        var sql = EspecialistaQueries.BUSCAR_MEDICOS_ATIVOS_OU_INATIVOS_POR_PARAMENTROS;
        parametros.put("ativo", ativo);
        if(StringUtils.isNotBlank(dadosDaPesquisa)) {
            sql += EspecialistaQueries.CLAUSULAS_NOME_OR_CRM_OR_ESPECIALIDADE_OR_RQE;
            parametros.put("dadosDaPesquisa", dadosDaPesquisa);
        }
        TypedQuery<Especialista> query = entityManager.createQuery(sql, Especialista.class);
        for(var entry : parametros.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

}
