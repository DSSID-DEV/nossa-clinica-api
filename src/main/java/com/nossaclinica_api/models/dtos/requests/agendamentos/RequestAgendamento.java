package com.nossaclinica_api.models.dtos.requests.agendamentos;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class RequestAgendamento implements Serializable {
    @Serial
    private static final long serialVersionUID = -7328029434650231073L;

    private Long idAgendamento;
    private Long idEspecialista;
    private Long idServico;
    private Long idCliente;
    private Long idEspecialidade;
    private LocalDate agendadoPara;
    private String marcadoPara;
    private LocalDate ultimaMestruacao;
    private String status;
}
