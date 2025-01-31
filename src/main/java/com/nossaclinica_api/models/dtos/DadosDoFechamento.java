package com.nossaclinica_api.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class DadosDoFechamento {

    @JsonIgnore
    public static final int ID_RESPONSAVEL = 0;
    @JsonIgnore
    public static final int NOME_RESPONSAVEL = 1;
    @JsonIgnore
    public static final int SEXO_RESPONSAVEL = 2;
    @JsonIgnore
    public static final int DATA_FECHAMENTO = 3;

    private Long idResponsavel;
    private String nomeSocialResponsavel;
    private String sexoResponsavel;
    private boolean medico;
    private LocalDate dataFechamento = LocalDate.now();
    private List<BaseDeCalculo> entradas = new ArrayList<>();
    private Integer quantidadeDeServico = 0;
    private BigDecimal somaDoServico = BigDecimal.ZERO;
    private BigDecimal valorTotalCalculado = BigDecimal.ZERO;

    public void calcularValorTotal() {
        for(var entrada : entradas) {
            this.quantidadeDeServico += entrada.getQtdTipoServico();
            this.somaDoServico = this.somaDoServico.add(entrada.getValorSomado());
            this.valorTotalCalculado = this.valorTotalCalculado.add(entrada.getValorCalculado());
        }
    }


}
