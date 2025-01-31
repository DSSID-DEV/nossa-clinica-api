package com.nossaclinica_api.models.dtos.responses.agendamentos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ResponseAgendamentoParaEdicao {

    @JsonIgnore
    public static final int ID_AGENDAMENTO = 0;
    @JsonIgnore
    public static final int ID_ESPECIALISTA = 1;
    @JsonIgnore
    public static final int ID_SERVICO = 2;
    @JsonIgnore
    public static final int ID_CLIENTE = 3;
    @JsonIgnore
    public static final int ID_ESPECIALIDADE = 4;
    @JsonIgnore
    public static final int STATUS = 5;
    @JsonIgnore
    public static final int AGENDADO_PARA = 6;
    @JsonIgnore
    public static final int MARCADO_PARA = 7;
    @JsonIgnore
    public static final int ULTIMA_MESTRUACAO = 8;


    private Long idAgendamento;
    private Long idEspecialista;
    private Long idServico;
    private Long idCliente;
    private Long idEspecialidade;
    private String status;
    private LocalDate agendadoPara;
    private String marcadoPara;
    private LocalDate ultimaMestruacao;
}
