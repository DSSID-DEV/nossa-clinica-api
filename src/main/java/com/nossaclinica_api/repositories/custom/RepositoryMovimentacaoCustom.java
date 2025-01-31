package com.nossaclinica_api.repositories.custom;

import com.nossaclinica_api.converters.ConverteMovimentacao;
import com.nossaclinica_api.models.dtos.PagamentoConsolidadoDTO;
import com.nossaclinica_api.models.dtos.responses.movimentacoes.ResponseFormaDePagamento;
import com.nossaclinica_api.repositories.queries.MovimentacaoQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositoryMovimentacaoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public PagamentoConsolidadoDTO buscarPagamentoConsolidado(Long idAgenda, Long idMovimentacao) {
        var sql = "";
        Map<String, Object> params = new HashMap<>();
        if(idAgenda != null) {
            sql = MovimentacaoQueries.PAGAMENTO_CONSOLIDADO.formatted(MovimentacaoQueries.FILTRO_ID_AGENDA);
            params.put("idAgenda", idAgenda);
        }
        if(idMovimentacao != null) {
            sql = MovimentacaoQueries.PAGAMENTO_CONSOLIDADO.formatted(MovimentacaoQueries.FILTRO_ID_MOVIMENTACAO);
            params.put("idMovimentacao", idMovimentacao);
        }
        Query query = entityManager.createNativeQuery(sql);
        setParameters(query, params);
        return ConverteMovimentacao.paraPagamentoConsolidadoDTO(query.getSingleResult());
    }

    private void setParameters(Query query, Map<String, Object> params) {
        for(var param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }
    }

    public ResponseFormaDePagamento listarPagamentos(Long idAgenda) {
        var sql = MovimentacaoQueries.LISTAR_PAGAMENTOS;
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("idAgenda", idAgenda);
        List<Object[]> list = query.getResultList();
        return ConverteMovimentacao.paraResponseFormaDePagamento(list);
    }

    public boolean pagamentoJaRealizado(Long idAgendamento, String servico, String prestador) {
        Query query = entityManager.createNativeQuery(MovimentacaoQueries.PAGAMENTO_JA_REGISTRADO);
        query.setParameter("idAgendamento", idAgendamento);
        query.setParameter("servico", servico);
        query.setParameter("prestador",prestador);
        var singResult = query.getSingleResult();
        return Boolean.parseBoolean(singResult.toString());
    }
}
