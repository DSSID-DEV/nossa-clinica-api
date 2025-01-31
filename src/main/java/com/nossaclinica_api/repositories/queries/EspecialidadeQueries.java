package com.nossaclinica_api.repositories.queries;

public class EspecialidadeQueries {


    public static final String BUSCAR_POR_NOME = """
            select count(especialidade) > 1 from Especialidade especialidade 
            where especialidade.descricao = :descricao
            """;
}
