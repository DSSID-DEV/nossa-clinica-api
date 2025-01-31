package com.nossaclinica_api.repositories.queries;

public class EspecialistaQueries {


    public static final String VERIFICAR_SE_EXISTE_MEDICO_CADASTRADO = """
            select count(especialista) > 0 from Especialista especialista
                where especialista.nome like :nome
                and especialista.telefone = :telefone
                and especialista.nascidoEm = :nascidoEm
                and especialista.email = :email                        
            """;
    public static final String RELACIONAR_MEDICOS_ATIVOS = """
            select especialista from Especialista especialista
                where especialista.ativo = true                        
            """;
    public static final String BUSCAR_MEDICOS_ATIVOS_OU_INATIVOS_POR_PARAMENTROS = """
            select distinct especialista from Especialista especialista 
            left join EspecialidadeMedica especialidadeMedica 
            on especialista.idEspecialista = especialidadeMedica.especialista.idEspecialista 
            left join Especialidade especialidade 
            on especialidadeMedica.especialidade.idEspecialidade = especialidade.idEspecialidade
            where especialista.ativo = :ativo 
            """;

    public static final String CLAUSULAS_NOME_OR_CRM_OR_ESPECIALIDADE_OR_RQE = """
            and (
            lower(especialista.nome) like lower(concat('%', :dadosDaPesquisa, '%')) 
            or especialista.crm like concat('%', :dadosDaPesquisa, '%')
            or especialidadeMedica.rqe like concat('%', :dadosDaPesquisa, '%')
            or lower(especialidade.descricao) like lower(concat('%', :dadosDaPesquisa, '%'))
            ) order by especialista.nome
            """;

}
