package com.nossaclinica_api.repositories.queries;

public class FormaDePagamentoQueries {
    public static final String LISTAR_POR_ID = """
            select formaDePagamento from FormaDePagamento formaDePagamento 
            where formaDePagamento.idFormaDePagamento in (:ids) and 
            formaDePagamento.status = 'ESTORNO SOLICITADO'
            """;
}
