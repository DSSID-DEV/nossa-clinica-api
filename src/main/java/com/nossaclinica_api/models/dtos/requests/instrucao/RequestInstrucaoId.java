package com.nossaclinica_api.models.dtos.requests.instrucao;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class RequestInstrucaoId implements Serializable {
    @Serial
    private static final long serialVersionUID = -4538246515969049380L;

    private Long idInstrucao;
}
