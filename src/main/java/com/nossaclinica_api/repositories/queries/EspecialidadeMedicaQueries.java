package com.nossaclinica_api.repositories.queries;

public class EspecialidadeMedicaQueries {
    public static final String LISTAR_ESPECIALIDADES_DO_ESPECIALISTA = """
            select especialidadesMedicas from EspecialidadeMedica especialidadesMedicas 
            where especialidadesMedicas.especialista.idEspecialista = :idEspecialista
            """;
    public static final String BUSCAR_ESPECIALIDADE_MEDICA_EXISTENTE = """
            select especialidadeMedica from EspecialidadeMedica especialidadeMedica 
            where especialidadeMedica.especialista.idEspecialista = :idEspecialista 
            and especialidadeMedica.especialidade.idEspecialidade = :idEspecialidade 
            and especialidadeMedica.rqe = :rqe 
            and especialidadeMedica.areaDeAtuacao = :areaDeAtuacao
            """;
    public static final String VERIFICAR_SE_HA_ESSA_ESPECIALIDADE_PARA_ESSE_ESPECIALISTA = """
            select count(especialidadeMedica) > 0 from EspecialidadeMedica especialidadeMedica 
            where especialidadeMedica.especialista.idEspecialista = :idEspecialista 
            and especialidadeMedica.especialidade.idEspecialidade = :idEspecialidade 
            and especialidadeMedica.rqe = :rqe 
            and especialidadeMedica.areaDeAtuacao = :areaDeAtuacao
            """;
}
