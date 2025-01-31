package com.nossaclinica_api.models.dtos.requests.exames;

import com.nossaclinica_api.models.dtos.requests.instrucao.RequestInstrucaoId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class RequestExame implements Serializable {

    @Serial
    private static final long serialVersionUID = -4700792073184101871L;

    private Long idExame;
    private String descricao;
    private String sigla;
    private String tipoExame;
    private BigDecimal custo;
    private BigDecimal preco;
    private String material;
    private String meioDeColeta;
    private String volume;
    private String prazo;
    private String interpretacao;
    private String doencasRelacionadas;
    private List<RequestInstrucaoId> instrucoes = new ArrayList<>();
}
