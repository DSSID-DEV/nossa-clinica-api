package com.nossaclinica_api.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


@Data
@Builder
public class FormaDePagamentoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 6986896374967034628L;

    private Long idFormaDePagamento;
    private String com;
    private String valor;
}
