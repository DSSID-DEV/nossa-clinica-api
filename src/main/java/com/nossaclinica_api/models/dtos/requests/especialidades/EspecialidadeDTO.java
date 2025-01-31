package com.nossaclinica_api.models.dtos.requests.especialidades;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class EspecialidadeDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 2896273197086560113L;

    private Long idEspecialidade;
    private String descricao;
}
