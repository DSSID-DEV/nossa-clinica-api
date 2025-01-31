package com.nossaclinica_api.models.dtos.requests.clientes;

import com.nossaclinica_api.models.dtos.EnderecoDTO;
import com.nossaclinica_api.models.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCliente implements Serializable {

    @Serial
    private static final long serialVersionUID = -3405104575652576074L;
    private Long idCliente;
    @NotBlank(message = "O campo Nome é obrigatório!")
    private String nome;
    private String cpf;
    private String documento;
    private String orgaoEmissor;
    @NotBlank(message = "O campo Telefone é obrigatório!")
    private String telefone;
    private boolean whatsapp;
    @NotNull(message = "O campo Nascido em é obrigatório!")
    private LocalDate nascidoEm;
    private String email;
    private UserDTO user;
    private EnderecoDTO endereco;
    private boolean ativo;
}
