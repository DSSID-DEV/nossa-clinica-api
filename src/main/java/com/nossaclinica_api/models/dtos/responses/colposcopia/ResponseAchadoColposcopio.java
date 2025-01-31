package com.nossaclinica_api.models.dtos.responses.colposcopia;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAchadoColposcopio implements Serializable {

    private Long idAchadoColposcopio;
    private String descricao;
}
