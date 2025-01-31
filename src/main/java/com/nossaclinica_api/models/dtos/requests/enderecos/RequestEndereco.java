package com.nossaclinica_api.models.dtos.requests.enderecos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestEndereco implements Serializable {
    @Serial
    private static final long serialVersionUID = -542456591230048832L;

    private Long idEndereco;
    private String cidade;
    private String uf;
    private String numero;
    private String bairro;
    private String tipoLogradouro;
    private String logradouro;
    private String cep;
    private String complemento;
}
