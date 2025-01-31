package com.nossaclinica_api.repositories.custom;

import com.nossaclinica_api.converters.ConverteExame;
import com.nossaclinica_api.converters.ExameConverte;
import com.nossaclinica_api.models.dtos.responses.exames.ResponseExame;
import com.nossaclinica_api.repositories.queries.ExameQueries;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RepositoryExameCustom {

    @PersistenceContext
    private EntityManager entityManager;
    public List<ResponseExame> lstarExames(String tipoExame, String params) {
        var sql = montarQuery(tipoExame, params);
        var query = entityManager.createNativeQuery(sql);
        setarParametros(query, tipoExame, params);
        return (List<ResponseExame>) query.getResultList().stream().map(ConverteExame::paraResponseExame)
                .collect(Collectors.toList());
    }

    private Query setarParametros(Query query, String tipoExame, String params) {
        if(StringUtils.isNotBlank(tipoExame)) {
            query.setParameter("tipoExame", tipoExame);
        }
        if(StringUtils.isNotBlank(params)) {
            query.setParameter("params", "%"+params+"%");
        }
        return query;
    }

    private String montarQuery(String tipoExame, String params) {
        var sql = ExameQueries.BUSCAR_EXAMES;
        if(StringUtils.isNotBlank(tipoExame)) {
            sql += "and exame.tipo_exame = :tipoExame ";
        }
        if(StringUtils.isNotBlank(params)) {
            sql += "and descricao like :params  or sigla like ? or interpretacao like :params "
                + "or doencas_relacionadas like :params ";
        }
        return sql += "order by descricao";
    }
}
