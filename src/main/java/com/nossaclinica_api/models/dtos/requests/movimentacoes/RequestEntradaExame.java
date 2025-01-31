package com.nossaclinica_api.models.dtos.requests.movimentacoes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestEntradaExame implements Serializable {
    @Serial
    private static final long serialVersionUID = 8697069267422259511L;

    private Long idEntradaExame;
    private String realizadoPor;
    private LocalDateTime realizadoEm;
    private List<String> idsExames = new ArrayList<>();
    private RequestMovimentacao movimentacao;
}
