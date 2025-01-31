package com.nossaclinica_api.models.dtos.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseId implements Serializable {
    @Serial
    private static final long serialVersionUID = -4053623511219967780L;

    private Long idUser;
    private Long idExame;
    private Long idEspecialista;
    private Long idCliente;
    private Long idServico;
    private Long idInstrucao;
    private Long idAgendamento;
    private Long idMovimentacao;
    private Long idServicoMedico;
    private Long idEspecialidade;
    private Long idEspecialidadeMedica;
}
