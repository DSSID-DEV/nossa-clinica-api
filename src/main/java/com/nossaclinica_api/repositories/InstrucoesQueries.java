package com.nossaclinica_api.repositories;

public class InstrucoesQueries {
    public static final String INSTRUCOES_DO_EXAME = """
            select instrucao from Instrucao instrucao where 
            instrucao.exame.idExame = :idExame
            """;
}
