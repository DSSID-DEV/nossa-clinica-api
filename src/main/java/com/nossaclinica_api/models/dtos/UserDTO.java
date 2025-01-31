package com.nossaclinica_api.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 4320404588652177470L;

    private Long idUser;
    private String nome;
    private String email;
    private String senha;
    private LocalDate nascidoEm;
    private String telefone;
    private boolean whatsapp;
    private String permissao;
    private String avatar;
    private boolean ativo;

}
