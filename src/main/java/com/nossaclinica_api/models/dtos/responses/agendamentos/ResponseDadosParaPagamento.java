package com.nossaclinica_api.models.dtos.responses.agendamentos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ResponseDadosParaPagamento {

    @JsonIgnore
    public static final int ID_AGENDA = 0;
    @JsonIgnore
    public static final int ID_CLIENTE = 1;
    @JsonIgnore
    public static final int PACIENTE = 2;
    @JsonIgnore
    public static final int ID_ESPECIALISTA = 3;
    @JsonIgnore
    public static final int SEXO_DO_ESPECIALISTA = 4;
    @JsonIgnore
    public static final int ESPECIALISTA = 5;
    @JsonIgnore
    public static final int ESPECIALIDADE = 6;
    @JsonIgnore
    public static final int ID_SERVICO = 7;
    @JsonIgnore
    public static final int SERVICO = 8;
    @JsonIgnore
    public static final int PRECO = 9;

    private Long idAgenda;
    private Long idCliente;
    private String paciente;
    private Long idEspecialista;
    private String sexoDoEspecialista;
    private String especialista;
    private String especialidade;
    private Long idServico;
    private String servico;
    private BigDecimal preco;

}
