package com.nossaclinica_api.repositories.queries;

public class AnaliseColposcopioQueries {
    public static final String LISTAR_ITENS_DE_ANALISE_COLPOSCOPIO = """
            select analise from AnaliseColposcopio analise join fetch analise.achados
            """;
    public static final String LISTAR_ACHADOS = """
            select analise.descricao analisado, achados.descricao as descricao,
                case
                    when achados.id_achado_colposcopio in (:achados) then true
                    else false
                end achado from achados_colposcipio achados left join
                analise_colposcipio analise on
                analise.id_analise_colposcopio = achados.id_analise_colposcopio
                left join agendamentos agenda on agenda.id_servico = analise.id_servico
                where analise.id_analise_colposcopio > 0
                and agenda.id_agendamento = :idAgendamento
                and agenda.pago is true
                order by analise.id_analise_colposcopio asc,
                achados.id_achado_colposcopio asc
            """;
}
