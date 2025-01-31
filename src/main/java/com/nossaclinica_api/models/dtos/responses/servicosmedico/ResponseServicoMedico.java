package com.nossaclinica_api.models.dtos.responses.servicosmedico;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
public class ResponseServicoMedico implements Serializable {

    @Serial
    private static final long serialVersionUID = 4384123866013499947L;

    @JsonIgnore
    public static final int ID_SERVICO_MEDICO = 0;
    @JsonIgnore
    public static final int ID_ESPECIALISTA = 1;
    @JsonIgnore
    public static final int ID_SERVICO = 2;
    @JsonIgnore
    public static final int DESCRICAO_SERVICO = 3;
    @JsonIgnore
    public static final int DESCRICAO = 4;
    @JsonIgnore
    public static final int PRECO = 5;

    private Long idServicoMedico;
    private Long idEspecialista;
    private Long idServico;
    private String nome;
    private String descricaoServico;
    private String descricao;
    private BigDecimal preco;

}
