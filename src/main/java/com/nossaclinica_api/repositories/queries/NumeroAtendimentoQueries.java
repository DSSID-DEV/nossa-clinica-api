package com.nossaclinica_api.repositories.queries;

public class NumeroAtendimentoQueries {
    public static final String PEGAR_ULTIMO_NUMERO_ATENDIMENTO = """
            select 
                case
                    when (count(numero_atendimento.id_numero_atendimento) > 0) 
                        then count(numero_atendimento.id_numero_atendimento) + 1 
                        else 1 end as ultimoAtendimento from numero_atendimento
                where tipo_atendimento = :tipoAtendimento
                and EXTRACT(YEAR FROM numero_atendimento.data_atendimento) = EXTRACT(YEAR FROM CURRENT_DATE)
                and (:idEspecialista is null or numero_atendimento.id_especialista = :idEspecialista)
                and (:idEspecialidade is null or numero_atendimento.id_especialidade = :idEspecialidade)
            """;

    public static final String BUSCAR_NUMERO_ATENDIMENTO = """
            select atendimento.numero_atendimento as numero 
                   from numero_atendimento as atendimento 
                   where atendimento.id_cliente = :idCliente 
                   and atendimento.id_especialista = :idEspecialista
                   and atendimento.data_atendimento = CURRENT_DATE
            """;
    public static final String DO_ATENDIMENTO_DO_PARAMENTRO = """
            select atendimento.*
                      from numero_atendimento as atendimento 
                      where atendimento.id_cliente = :idCliente 
                      and atendimento.id_especialista = :idEspecialista
                      and atendimento.id_especialidade = :idEspecialidade 
                      and atendimento.tipo_atendimento = :tipoAtendimento 
                      and atendimento.data_atendimento = :dataAtendimento""";
}
