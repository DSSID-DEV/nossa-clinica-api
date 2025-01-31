package com.nossaclinica_api.models.dtos.responses.exames;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nossaclinica_api.models.dtos.InstrucaoDTO;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ResponseExame implements Serializable {
    @Serial
    private static final long serialVersionUID = -4700792073184101871L;

    @JsonIgnore
    public static final int ID_EXAME = 0;
    @JsonIgnore
    public static final int DESCRICAO = 1;
    @JsonIgnore
    public static final int SIGLA = 2;
    @JsonIgnore
    public static final int TIPO_EXAME = 3;
    @JsonIgnore
    public static final int CUSTO = 4;
    @JsonIgnore
    public static final int PRECO = 5;
    @JsonIgnore
    public static final int MATERIAL = 6;
    @JsonIgnore
    public static final int MEDIO_DE_COLETA = 7;
    @JsonIgnore
    public static final int VOLUME = 8;
    @JsonIgnore
    public static final int PRAZO = 9;
    @JsonIgnore
    public static final int INTERPRETACAO = 10;
    @JsonIgnore
    public static final int DOENCAS_RELACIONADAS = 11;

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
    private List<InstrucaoDTO> instrucoes;
}
