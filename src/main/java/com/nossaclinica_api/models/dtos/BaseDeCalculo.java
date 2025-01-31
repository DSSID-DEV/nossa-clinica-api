package com.nossaclinica_api.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BaseDeCalculo {

    @JsonIgnore
    public static final int ID_ORIGEM = 0;
    @JsonIgnore
    public static final int TOTAL_TIPO_SERVICO = 1;
    @JsonIgnore
    public static final int SERVICO = 2;
    @JsonIgnore
    public static final int TOTAL_SOMA_TIPO_SERVICO = 3;
    @JsonIgnore
    public static final int PERCENTAGEM = 4;
    @JsonIgnore
    public static final int RECEITA_FIXADA = 5;
    @JsonIgnore
    public static final int CALCULO_DO_ATENDIMENTO = 6;

    private Long idOrigem;
    private Integer qtdTipoServico;
    private String servico;
    private BigDecimal valorSomado;
    private double percentagem;
    private BigDecimal receitaFixada;
    private BigDecimal valorCalculado;

}
