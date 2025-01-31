package com.nossaclinica_api.models.dtos.requests.autenticacao;


import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class RequestDadosDeAcesso implements Serializable {
    @Serial
    private static final long serialVersionUID = -2702596282986442389L;

    private String dadosDeAcesso;
    private String senha;
}
