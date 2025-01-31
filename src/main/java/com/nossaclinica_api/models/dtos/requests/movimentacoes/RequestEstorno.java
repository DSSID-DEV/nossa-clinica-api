package com.nossaclinica_api.models.dtos.requests.movimentacoes;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class RequestEstorno implements Serializable {
    @Serial
    private static final long serialVersionUID = -100024853158998867L;

    private String hashAutorizacao;

    private List<Long> idsFormaDePagamento;
}
