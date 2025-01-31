package com.nossaclinica_api.models.dtos.responses.especialidadesmedica;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseEspecialidadeMedica {
    private Long idEspecialidadeMedica;
    private Long idEspecialidade;
    private String descricao;
    private String rqe;
    private String areaDeAtuacao;
}
