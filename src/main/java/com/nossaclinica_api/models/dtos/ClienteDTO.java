package com.nossaclinica_api.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3405104575652576074L;

    private Long idCliente;
    private String nome;
    private String cpf;
    private String documento;
    private String orgaoEmissor;
    private String telefone;
    private boolean isWhatsapp;
    private LocalDate nascidoEm;
    private String email;
    private UserDTO user;
    private EnderecoDTO endereco;
    private boolean ativo;
}
