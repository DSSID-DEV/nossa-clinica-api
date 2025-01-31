package com.nossaclinica_api.repositories.custom;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class EndpointRepositoryCustom {
    private static final String SELECT_URI = """
            select uri from uris_geradores_pdf 
            where tipo_documento = :tipo and id_servico = :idServico
            """;
    @PersistenceContext
    private EntityManager entityManager;

    public String para(Long idServico, Integer tipo) {
        var sql = SELECT_URI;
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("idServico", idServico);
        query.setParameter("tipo", tipo);
        return query.getSingleResult().toString();
    }
}
