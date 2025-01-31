package com.nossaclinica_api.models.dtos.responses.colposcopia;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAnaliseColposcopio implements Serializable {
    @Serial
    private static final long serialVersionUID = 3126332350652552663L;

    private Long idAnaliseColposcopio;
    private String descricao;
    private List<ResponseAchadoColposcopio> achados = new ArrayList<>();
}
