package com.nossaclinica_api.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nossaclinica_api.utils.Utils;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DadosParaProntuario {

    @JsonIgnore
    public static final int PACIENTE = 0;
    @JsonIgnore
    public static final int NASCIDO_EM = 1;
    @JsonIgnore
    public static final int ULTIMA_MESTRUACAO = 2;
    @JsonIgnore
    public static final int ULTIMO_ATENDIMENTO = 3;
    @JsonIgnore
    public static final int TIPO_LOGRADOURO = 4;
    @JsonIgnore
    public static final int LOGRADOURO = 5;
    @JsonIgnore
    public static final int NUMERO = 6;
    @JsonIgnore
    public static final int CONTATO = 7;
    @JsonIgnore
    public static final int IS_MEDICO = 8;
    @JsonIgnore
    public static final int SEXO_ESPECIALISTA = 9;
    @JsonIgnore
    public static final int ESPECIALISTA = 10;
    @JsonIgnore
    public static final int CONSELHO = 11;
    @JsonIgnore
    public static final int UF = 12;
    @JsonIgnore
    public static final int REGISTRO = 13;
    @JsonIgnore
    public static final int RQES = 14;
    @JsonIgnore
    public static final int NUMERO_DE_ATENDIMENTO = 15;


    private String paciente;
    private LocalDate nascidoEm;
    private LocalDate ultimaMestruacao;
    private LocalDate ultimoAtendimento;
    private String tipoLogradouro;
    private String logradouro;
    private String numero;
    private String contato;
    private boolean medico;
    private String sexoEspecialista;
    private String especialista;
    private String conselho;
    private String registro;
    private String uf;
    private String rqes;
    private String numeroDeAtendimento;

    public int calcularIdadeDoPaciente() {
        var hoje = LocalDate.now();
        var idade = nascidoEm.compareTo(hoje);
        if(Utils.compararDatas(hoje, nascidoEm)) {
            idade --;
        }
        return idade;
    }

}
