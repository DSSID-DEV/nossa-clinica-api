package com.nossaclinica_api.models.dtos.responses.movimentacoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nossaclinica_api.models.dtos.FormaDePagamentoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFormaDePagamento {

    @JsonIgnore
    public static final int ID_MOVIMENTACAO = 0;
    @JsonIgnore
    public static final int SERVICO = 1;
    @JsonIgnore
    public static final int ESPECIALISTA = 2;

    //INDEX DA COLUNA DE FORMA DE PAGAMENTO
    @JsonIgnore
    public static final int ID_FORMA_DE_PAGAMENTO = 3;
    @JsonIgnore
    public static final int PAGO_COM = 4;
    @JsonIgnore
    public static final int VALOR = 5;


    private Long idMovimentacao;
    private String servico;
    private String especialista;
    private List<FormaDePagamentoDTO> pagamentos = new ArrayList<>();

    public ResponseFormaDePagamento(Long idMovimentacao, String servico, String especialista) {
        this.idMovimentacao = idMovimentacao;
        this.servico = servico;
        this.especialista = especialista;
    }

    public void addPagamentos(FormaDePagamentoDTO pagamento) {
       this.pagamentos.add(pagamento);
    }
}
