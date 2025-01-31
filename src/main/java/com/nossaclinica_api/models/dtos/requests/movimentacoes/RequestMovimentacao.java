package com.nossaclinica_api.models.dtos.requests.movimentacoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nossaclinica_api.models.dtos.FormaDePagamentoDTO;
import io.swagger.v3.core.util.Json;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class RequestMovimentacao implements Serializable {
    @Serial
    private static final long serialVersionUID = 6226959137390128451L;

    private Long idMovimentacao;

    private Long idAgendamento;

    private String servico;

    private String prestador;

    private boolean pago;

    private List<FormaDePagamentoDTO> formasDePagamento = new ArrayList<>();
}
