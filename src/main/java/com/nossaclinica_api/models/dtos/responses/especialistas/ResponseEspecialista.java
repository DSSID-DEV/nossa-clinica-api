package com.nossaclinica_api.models.dtos.responses.especialistas;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class ResponseEspecialista implements Serializable {

    @Serial
    private static final long serialVersionUID = 7606947207996990778L;

    private Long idEspecialista;
    private String nome;
    private String nomeSocial;
    private String conselho;
    private String registro;
    private String uf;
    private String avatar;
    private String telefone;
    private boolean isWhatsapp;
    private LocalDate nascidoEm;
    private String email;
    private String sexo;
    private boolean ativo;

}
