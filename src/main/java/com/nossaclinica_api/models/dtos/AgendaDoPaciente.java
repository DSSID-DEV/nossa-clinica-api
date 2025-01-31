package com.nossaclinica_api.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nossaclinica_api.reports.utils.ConstantesValues;
import com.nossaclinica_api.utils.Utils;
import lombok.Builder;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Data
@Builder
public class AgendaDoPaciente {

    @JsonIgnore
    public static final int TIPO_DE_ATENDIMENTO = 0;
    @JsonIgnore
    public static final int STATUS = 1;
    @JsonIgnore
    public static final int SEXO = 2;
    @JsonIgnore
    public static final int MEDICO = 3;
    @JsonIgnore
    public static final int AGENDADO_PARA = 4;
    @JsonIgnore
    public static final int MARCADO_PARA = 5;
    @JsonIgnore
    public static final int PACIENTE = 6;
    @JsonIgnore
    public static final int TELEFONE = 7;
    @JsonIgnore
    public static final int NASCIDO_EM = 8;

    private String tipoDeAtendimento;
    private String status;
    private String sexo;
    private String medico;
    private LocalDate agendadoPara;
    private String marcado_para;
    private String paciente;
    private String telefone;
    private LocalDate nascidoEm;

    public String toString() {
        var mensagem = this.status.equals("Confirmado") ? ConstantesValues.AVISO_DE_CONSULTA_AGENDADA :
                ConstantesValues.AVISO_DE_CONSULTA_AGENDADA_SOLICITA_CONFIRMACAO;
        var dataAgenda = new SimpleDateFormat("dd/MM/yyyy").format(agendadoPara);
        return String
                .format(mensagem,
                        Utils.obterPrimeiroNome(paciente),
                        Utils.difinirTratamento(sexo, nascidoEm),
                        this.tipoDeAtendimento, Utils.definiPronomeDosProfissionais(sexo, true), this.medico,
                        dataAgenda, this.marcado_para);
    }
}
