package com.nossaclinica_api.models.dtos.requests.autenticacao;

import lombok.Data;

@Data
public class RequestDadosDeAcessoNovaSenha {
    private String dadosDeAcesso;
    private String novaSenha;
}
