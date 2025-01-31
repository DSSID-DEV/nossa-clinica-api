package com.nossaclinica_api.models.dtos.responses.autenticacao;

import lombok.Data;

@Data
public class RequestValidarTelefone {

    private Long idUser;
    private String telefone;
}
