package com.nossaclinica_api.models.dtos;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7640658727759681868L;

    private  Long idMedico;
    private String nome;
    private String crm;
    private String uf;
    private String avatar;
    private String telefone;
    private boolean whatsapp;
    private LocalDate nascidoEm;
    private String email;
    private String sexo;
    private boolean ativo;
    private UserDTO user;
}

