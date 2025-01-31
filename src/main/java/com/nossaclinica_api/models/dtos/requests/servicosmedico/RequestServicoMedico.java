package com.nossaclinica_api.models.dtos.requests.servicosmedico;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestServicoMedico {
    private Long idServico;
    private String descricao;
    private String receita;
    private String percentagem;
    private String preco;
}
