package com.nossaclinica_api.models.dtos.responses.autenticacao;

import lombok.Data;

@Data
public class ResponseUserAutenticado {
    private Long idUser;
    private String username;
    private String avatar;
    private String permissao;
    private String token;
}
