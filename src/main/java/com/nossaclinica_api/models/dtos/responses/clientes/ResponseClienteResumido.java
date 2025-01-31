package com.nossaclinica_api.models.dtos.responses.clientes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nossaclinica_api.models.dtos.EnderecoDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ResponseClienteResumido {

    @JsonIgnore
    public static final int ID_CLIENTE = 0;
    @JsonIgnore
    public static final int NOME = 1;
    @JsonIgnore
    public static final int CPF = 2;
    @JsonIgnore
    public static final int TELEFONE = 3;
    @JsonIgnore
    public static final int IS_WHATSAPP = 4;
    @JsonIgnore
    public static final int NASCIDO_EM = 5;
    @JsonIgnore
    public static final int ATIVO = 6;


    private Long idCliente;
    private String nome;
    private String cpf;
    private String telefone;
    private boolean whatsapp;
    private LocalDate nascidoEm;
    private boolean ativo;
}
