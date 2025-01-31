package com.nossaclinica_api.models.dtos;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ExameDTO implements Serializable {
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
    private String iterpretacao;
    private String doencasRelacionadas;
}
