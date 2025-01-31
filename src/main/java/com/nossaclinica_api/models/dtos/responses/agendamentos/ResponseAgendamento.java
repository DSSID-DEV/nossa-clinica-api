package com.nossaclinica_api.models.dtos.responses.agendamentos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ResponseAgendamento implements Serializable {
    @Serial
    private static final long serialVersionUID = -7328029434650231073L;

    @JsonIgnore
    public static final int ID_AGENDAMENTO = 0;
    @JsonIgnore
    public static final int SERVICO = 1;
    @JsonIgnore
    public static final int DESCRICAO_SERVICO = 2;
    @JsonIgnore
    public static final int ESPECIALIDADE = 3;
    @JsonIgnore
    public static final int ESPECIALISTA = 4;
    @JsonIgnore
    public static final int STATUS = 5;
    @JsonIgnore
    public static final int AGENDADO_PARA = 6;
    @JsonIgnore
    public static final int PACIENTE = 7;
    @JsonIgnore
    public static final int MARCADO_PARA = 8;
    @JsonIgnore
    public static final int PRECO = 9;
    @JsonIgnore
    public static final int PAGO = 10;
    @JsonIgnore
    public static final int ULTIMA_MESTRUACAO = 11;

    private Long idAgendamento;
    private String servico;
    private String descricaoServico;
    private String especialista;
    private String especialidade;
    private String status;
    private LocalDate agendadoPara;
    private String paciente;
    private String marcadoPara;
    private BigDecimal preco;
    private boolean pago;
    private LocalDate ultimaMestruacao;

}
