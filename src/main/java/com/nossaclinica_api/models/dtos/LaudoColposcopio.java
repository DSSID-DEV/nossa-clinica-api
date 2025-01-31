package com.nossaclinica_api.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class LaudoColposcopio {

    private String profissional;
    private String paciente;
    private LocalDate nascidoEm;
    private LocalDate ultimaMestruacao;
    private LocalDate ultimoExame;
    private String numeroAtendimento;
    private String ultimaGladula;
    private String outros;
    private int idade;
    private Map<String, List<DadosColposcopio>> laudo;

}
