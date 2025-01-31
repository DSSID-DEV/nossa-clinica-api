package com.nossaclinica_api.models.dtos.requests.servicos;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class RequestServico implements Serializable {
    @Serial
    private static final long serialVersionUID = 445901562467038519L;

    private Long idServico;
    private String nome;
    private String descricao;
}
