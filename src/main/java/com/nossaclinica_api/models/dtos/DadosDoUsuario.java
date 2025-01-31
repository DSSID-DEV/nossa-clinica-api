package com.nossaclinica_api.models.dtos;


import lombok.Data;

@Data
public class DadosDoUsuario {

    private String nome;
    private String email;
    private String telefone;
    private boolean whatsapp;
    private String permissao;
}
