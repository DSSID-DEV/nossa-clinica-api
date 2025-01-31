package com.nossaclinica_api.models.dtos.requests.autenticacao;


import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class RequestSenha implements Serializable {
    @Serial
    private static final long serialVersionUID = -4071694199315233111L;

    private String nova;
}
