package com.nossaclinica_api.repositories;

public class ParametroReportQueries {

    public static final String BUSCAR_PARAMETRO_PARA_TAMANHO_PAPEL = """
            select parametro from ParametroReport parametro 
            where parametro.papel = :papel
            """;
}
