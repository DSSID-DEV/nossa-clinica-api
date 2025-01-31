package com.nossaclinica_api.models.enums;

import com.nossaclinica_api.utils.ConstantsValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;


@Getter
@AllArgsConstructor
public enum PathsDosAvatas {
    ADMIN("ADMIN", ConstantsValue.PATH_AVATAR_ADMIN),
    MEDICO("MEDICO", ConstantsValue.PATH_AVATAR_MEDICO),
    CLIENTE("CLIENTE", ConstantsValue.PATH_AVATAR_CLIENTE),
    RECEPCAO("RECEPCAO", ConstantsValue.PATH_AVATAR_INTERNO),
    EXTERNO("EXTERNO", ConstantsValue.PATH_AVATAR_EXTERNO),
    SUPORTE("SUPORTE", ConstantsValue.PATH_AVATAR_SUPORTE);

    private final String permissao;
    private final String path;

}
