package com.nossaclinica_api.models.dtos.responses.instrucao;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ResponseInstrucao implements Serializable {
    @Serial
    private static final long serialVersionUID = 6479034507396854037L;

    private Long idInstrucao;
    private String tipoInstrucao;
    private String descricao;
}
