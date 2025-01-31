package com.nossaclinica_api.converters;

import com.nossaclinica_api.models.dtos.FormaDePagamentoDTO;
import com.nossaclinica_api.models.dtos.PagamentoConsolidadoDTO;
import com.nossaclinica_api.models.dtos.responses.movimentacoes.ResponseFormaDePagamento;
import com.nossaclinica_api.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConverteMovimentacao {
    public static PagamentoConsolidadoDTO paraPagamentoConsolidadoDTO(Object movimentacao) {
        if(movimentacao == null) {
            return null;
        }
        return PagamentoConsolidadoDTO.builder()
                .idAgenda(Utils.parseLogn((Object[]) movimentacao,PagamentoConsolidadoDTO.ID_AGENDA))
                .idMovimentacao(Utils.parseLogn((Object[]) movimentacao,PagamentoConsolidadoDTO.ID_MOVIMENTACAO))
                .paciente(Utils.parseString((Object[]) movimentacao, PagamentoConsolidadoDTO.PACIENTE))
                .especialista(Utils.parseString((Object[]) movimentacao, PagamentoConsolidadoDTO.ESPECIALISTA))
                .sexoEspecialista(Utils.parseString((Object[]) movimentacao, PagamentoConsolidadoDTO.SEXO_DO_ESPECIALISTA))
                .servico(Utils.parseString((Object[]) movimentacao, PagamentoConsolidadoDTO.SERVICO))
                .valor(Utils.parseBigDecimal((Object[]) movimentacao, PagamentoConsolidadoDTO.VALOR))
                .build();
    }

    public static ResponseFormaDePagamento paraResponseFormaDePagamento(List<Object[]> list) {
        Map<Long, ResponseFormaDePagamento> map = new HashMap<>();
        Long idMovimentacao = null;
        for(var movimentacao : list) {
            idMovimentacao = Utils.parseLogn(movimentacao, ResponseFormaDePagamento.ID_MOVIMENTACAO);
            if(!map.containsKey(idMovimentacao)) {
                var response = new ResponseFormaDePagamento(idMovimentacao,
                        Utils.parseString(movimentacao, ResponseFormaDePagamento.SERVICO),
                        Utils.parseString(movimentacao, ResponseFormaDePagamento.ESPECIALISTA));
                map.put(idMovimentacao, response);
            }
            var pagamento = FormaDePagamentoDTO.builder()
                    .idFormaDePagamento(Utils.parseLogn(movimentacao, ResponseFormaDePagamento.ID_FORMA_DE_PAGAMENTO))
                    .com(Utils.parseString(movimentacao, ResponseFormaDePagamento.PAGO_COM))
                    .valor(Utils.parseString(movimentacao, ResponseFormaDePagamento.VALOR))
                    .build();
            map.get(idMovimentacao).addPagamentos(pagamento);
        }
        return map.get(idMovimentacao);
    }
}
