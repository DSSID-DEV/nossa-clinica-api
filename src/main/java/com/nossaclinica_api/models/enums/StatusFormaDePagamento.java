package com.nossaclinica_api.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusFormaDePagamento {
    ESTORNO_SOLICITADO("ESTORNO SOLICITADO"),
    CONSOLIDADO("CONSOLIDADO"),
    ESTORNADO("ESTORNADO");

    private String descricao;
}
