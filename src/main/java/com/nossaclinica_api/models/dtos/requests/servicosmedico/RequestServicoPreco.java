package com.nossaclinica_api.models.dtos.requests.servicosmedico;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestServicoPreco {
    private String descricao;
    private String percentagem;
    private String receita;
    private String preco;
}
