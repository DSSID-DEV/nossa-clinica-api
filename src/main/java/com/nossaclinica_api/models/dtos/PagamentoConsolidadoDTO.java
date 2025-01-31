package com.nossaclinica_api.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PagamentoConsolidadoDTO {

    @JsonIgnore
    public static final int ID_AGENDA = 0;
    @JsonIgnore
    public static final int PACIENTE = 1;
    @JsonIgnore
    public static final int SEXO_DO_ESPECIALISTA = 2;
    @JsonIgnore
    public static final int ESPECIALISTA = 3;
    @JsonIgnore
    public static final int SERVICO = 4;
    @JsonIgnore
    public static final int ID_MOVIMENTACAO = 5;
    @JsonIgnore
    public static final int VALOR = 6;

    private Long idAgenda;
    private String paciente;
    private String sexoEspecialista;
    private String especialista;
    private String servico;
    private Long idMovimentacao;
    private BigDecimal valor;
}
