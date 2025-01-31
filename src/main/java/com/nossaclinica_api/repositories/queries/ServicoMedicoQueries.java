package com.nossaclinica_api.repositories.queries;

public class ServicoMedicoQueries {
    public static final String RELACIONAR_SERVICOS_DO_MEDICO = """
            select servicosMedico from ServicosMedico servicosMedico 
            where servicosMedico.especialista.idEspecialista = :idEspecialista
            """;
    public static final String EXIST_SERVICO_MEDICO = """
            select count(servicosMedico) > 0 from ServicosMedico  servicosMedico 
            where servicosMedico.especialista.idEspecialista = :idEspecialista 
            and servicosMedico.servico.idServico = :idServico
            and servicosMedico.descricao = :descricao
            """;
}
