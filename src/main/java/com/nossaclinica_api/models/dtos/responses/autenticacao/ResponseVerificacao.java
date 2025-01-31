package com.nossaclinica_api.models.dtos.responses.autenticacao;


import com.nossaclinica_api.models.dtos.KeyValue;
import com.nossaclinica_api.utils.ConstantsValue;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseVerificacao {

    private Long idUser;
    private String descricao = ConstantsValue.DESCRICAO;
    private List<KeyValue> telefones = new ArrayList<>();

}
