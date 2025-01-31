package com.nossaclinica_api.repositories.custom;

import com.nossaclinica_api.converters.ClienteConverte;
import com.nossaclinica_api.models.dtos.responses.clientes.ResponseClienteResumido;
import com.nossaclinica_api.repositories.queries.ClienteQueries;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class RepositoryClienteCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ResponseClienteResumido> listarClientesPorParametros(String dadosDaPesquisa, boolean ativo) {
        Map<String, Object> params = new HashMap<>();
        var sql = ClienteQueries.RELACIONAR_CLIENTES_ATIVOS_OU_INATIVOS;
        if(StringUtils.isNotBlank(dadosDaPesquisa)) {
            sql = sql.replace("%s", "");
            sql += ClienteQueries.CLAUSULAS_NOME_OR_CPF_OR_EMAIL;
            params.put("dadosDaPesquisa", "%" + dadosDaPesquisa + "%");
        } else {
            sql = sql.formatted("and dependente.id_dependente is null");
        }
        params.put("ativo", ativo);
        log.info(sql);
        Query query = entityManager.createNativeQuery(sql);
//        TypedQuery<Cliente> query = entityManager.createQuery(sql, Cliente.class);
        for(var entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return (List<ResponseClienteResumido>) query.getResultList().stream()
                .map(ClienteConverte::paraResponseClienteResumido)
                .collect(Collectors.toList());
    }


}
