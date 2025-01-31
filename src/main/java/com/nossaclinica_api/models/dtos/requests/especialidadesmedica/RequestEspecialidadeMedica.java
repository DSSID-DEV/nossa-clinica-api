package com.nossaclinica_api.models.dtos.requests.especialidadesmedica;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class RequestEspecialidadeMedica implements Serializable {
    @Serial
    private static final long serialVersionUID = -5188472538187922097L;

    private Long idEspecialidadeMedica;
    private Long IdEspecialidade;
    private String rqe;
    private String areaDeAtuacao;

}
