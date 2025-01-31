package com.nossaclinica_api.models.dtos;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class InstrucaoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6451315731683516591L;

    private Long idInstrucao;
    private String tipoInstrucao;
    private String descricao;
    private ExameDTO exame;
}
