package com.nossaclinica_api.models.dtos.requests;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestExameColposcopio {
    private Long idAgendamento;
    private Long idMedico;
    private Long idCliente;
    private String numeroAtendimento;
    private LocalDate realizadoEm;
    private LocalDate ultimaMestruacao;
    private LocalDate ultimoExame;
    private String ultimaGladula;
    private String outros;

    private List<Long> achados;
}
