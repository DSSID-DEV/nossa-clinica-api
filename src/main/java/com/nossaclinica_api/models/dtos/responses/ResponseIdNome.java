package com.nossaclinica_api.models.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseIdNome {
    private Long idUser;
    private Long idExame;
    private Long idMedico;
    private Long idCliente;
    private Long idServico;
    private String nome;
    private String nomeSocial;
}
