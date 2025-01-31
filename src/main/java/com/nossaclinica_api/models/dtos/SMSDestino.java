package com.nossaclinica_api.models.dtos;


import lombok.Data;

@Data
public class SMSDestino {
    private String destino;
    private String mensagem;
}
