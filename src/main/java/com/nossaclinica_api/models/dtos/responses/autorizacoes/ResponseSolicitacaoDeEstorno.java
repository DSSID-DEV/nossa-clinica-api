package com.nossaclinica_api.models.dtos.responses.autorizacoes;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseSolicitacaoDeEstorno {

    private String hashAutorizacao;
    private List<Long> idFormasDePagamento;
    private String mensagem;
}
