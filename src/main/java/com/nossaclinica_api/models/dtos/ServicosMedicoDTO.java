package com.nossaclinica_api.models.dtos;

import com.nossaclinica_api.models.dtos.requests.servicos.RequestServico;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ServicosMedicoDTO implements Serializable {

    private Long idServicoMedico;
    private MedicoDTO medico;
    private RequestServico servico;
    private BigDecimal preco;
}
