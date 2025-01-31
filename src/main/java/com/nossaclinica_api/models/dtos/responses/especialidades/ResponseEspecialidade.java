package com.nossaclinica_api.models.dtos.responses.especialidades;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ResponseEspecialidade implements Serializable {
    @Serial
    private static final long serialVersionUID = 2896273197086560113L;

    private Long idEspecialidade;
    private String descricao;
}
