package com.nossaclinica_api.repositories.queries;

public class ExameQueries {
    public static final String EXISTS_EXAME = """
            select count(exame) > 0 from Exame exame where exame.material = :material 
            and exame.descricao = :descricao and exame.sigla = :sigla 
            and exame.meioDeColeta = :meioDeColeta
            """;

    public static final String BUSCAR_EXAMES = """
            select id_exame as idExame, descricao, sigla, tipo_exame as tipoExame, custo, 
            preco, material, meio_de_coleta as meioDeColeta, volume, prazo, interpretacao, 
            doencas_relacionadas as doencasRelacionadas from exames
                where id_exame > 0 
            """;
}
