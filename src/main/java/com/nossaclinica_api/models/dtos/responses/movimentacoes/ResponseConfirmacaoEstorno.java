package com.nossaclinica_api.models.dtos.responses.movimentacoes;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseConfirmacaoEstorno {
    private String mensagem;
    private List<String> estornados;
}
