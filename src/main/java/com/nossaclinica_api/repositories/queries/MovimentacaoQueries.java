package com.nossaclinica_api.repositories.queries;

public class MovimentacaoQueries {
    public static final String PAGAMENTO_JA_REGISTRADO = """
            select CASE WHEN COUNT(movimentacao.id_movimentacao) > 0 THEN TRUE
                  ELSE FALSE
                    END AS resultado from movimentacoes movimentacao
                where movimentacao.id_agendamento = :idAgendamento and movimentacao.servico = :servico and movimentacao.prestador = :prestador
            """;

    public static final String PAGAMENTO_CONSOLIDADO = """
            select distinct agenda.id_agendamento as idAgenda,
                   cliente.nome as paciente,
                   especialista.sexo as sexoDoEspecialista,
                   especialista.nome as profissional,
                   servico.descricao as servico,
                   movimentacao.id_movimentacao as idMovimentacao,
                   COALESCE(SUM(formaDePagamento.valor), 0) as valor
            from clientes cliente
                left join agendamentos agenda
                        on agenda.id_cliente = cliente.id_cliente
                left join especialistas especialista
                        on especialista.id_especialista = agenda.id_especialista
                left join servicos servico
                        on servico.id_servico = agenda.id_servico
                join movimentacoes movimentacao
                on movimentacao.id_agendamento = agenda.id_agendamento
                join formas_de_pagamento formaDePagamento
                on formaDePagamento.id_movimentacao = movimentacao.id_movimentacao
                where %s 
                group by agenda.id_agendamento, cliente.nome,
                especialista.sexo, especialista.nome, servico.descricao, 
                movimentacao.id_movimentacao
            """;
    public static final String FILTRO_ID_AGENDA = "agenda.id_agendamento = :idAgenda ";
    public static final String FILTRO_ID_MOVIMENTACAO = "movimentacao = :idMovimentacao";
    public static final String LISTAR_PAGAMENTOS = """
            select 
               movimentacao.id_movimentacao as idMovimentacao,
               movimentacao.servico as servico,
               movimentacao.prestador as profissional,
               formaDePagamento.id_forma_de_pagamento as idFormaDePagamento,
               formaDePagamento.pago_com as pagoCom,
               formaDePagamento.valor as valor
            from movimentacoes movimentacao
               join formas_de_pagamento formaDePagamento
               on formaDePagamento.id_movimentacao = movimentacao.id_movimentacao
               where movimentacao.id_agendamento = :idAgenda
            """;

}
