package com.nossaclinica_api.models.dtos.requests.especialistas;

import com.nossaclinica_api.models.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestEspecialista implements Serializable {

    @Serial
    private static final long serialVersionUID = -7640658727759681868L;

    private  Long idEspecialista;
    @NotBlank(message = "O campo Nome é obrigatório!")
    private String nome;
    private String nomeSocial;
    @NotBlank(message = "O campo Conselho é obrigatório!")
    private String conselho;
    @NotBlank(message = "O campo Registro é obrigatório!")
    private String registro;
    @NotBlank(message = "O campo UF é obrigatório!")
    private String uf;
    private String avatar;
    @NotBlank(message = "O campo Telefone é obrigatório!")
    private String telefone;
    private boolean whatsapp;
    private LocalDate nascidoEm;
    private String email;
    @NotBlank(message = "O campo Sexo é obrigatório!")
    private String sexo;
    private boolean ativo;
    private UserDTO user;
}

