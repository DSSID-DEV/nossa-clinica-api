package com.nossaclinica_api.models.dtos.responses.clientes;

import com.nossaclinica_api.models.dtos.EnderecoDTO;
import com.nossaclinica_api.models.dtos.UserDTO;
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
public class ResponseCliente implements Serializable {

    @Serial
    private static final long serialVersionUID = -3405104575652576074L;
    private Long idCliente;
    private String nome;
    private String cpf;
    private String documento;
    private String orgaoEmissor;
    private String telefone;
    private boolean whatsapp;
    private LocalDate nascidoEm;
    private String email;
    private EnderecoDTO endereco;
    private boolean ativo;
}
