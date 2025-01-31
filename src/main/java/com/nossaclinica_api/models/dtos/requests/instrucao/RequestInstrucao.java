package com.nossaclinica_api.models.dtos.requests.instrucao;


import lombok.Data;

@Data
public class RequestInstrucao {

    private Long idInstrucao;
    private String tipoInstrucao;
    private String descricao;
    private Long idExame;
}
