package com.nossaclinica_api.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Permisao {
    SUPORTE("SUPORTE"),
    MEDICO("MEDICO"),
    CLIENTE("CLIENTE"),
    RECEPCAO("RECEPCAO");

    private final String descricao;

}
